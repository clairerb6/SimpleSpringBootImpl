package cl.bci.api.rest.service.impl;

import java.util.ArrayList;
import java.util.Base64;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cl.bci.api.rest.exception.DuplicatedResourceException;
import cl.bci.api.rest.exception.ResourceNotFoundException;
import cl.bci.api.rest.model.Phones;
import cl.bci.api.rest.model.Users;
import cl.bci.api.rest.model.dto.PhonesDto;
import cl.bci.api.rest.model.dto.UsersDto;
import cl.bci.api.rest.repository.UsersRepository;
import cl.bci.api.rest.service.PhonesService;
import cl.bci.api.rest.service.UsersService;
import cl.bci.api.rest.utils.General;

@Service
@Transactional
public class UsersServiceImpl implements UsersService {
	
	@Autowired
	private UsersRepository usersRepository;
	
	@Autowired
	private PhonesService phonesService;
	
	@Override
	public Users createUser(UsersDto dto) {
		if(!General.passwordPattern.matcher(dto.getPassword()).matches()) {
			throw new DuplicatedResourceException("The password does not meet the requirements");
		}
		if(!Objects.isNull(this.getUserByEmail(dto))) {
			throw new DuplicatedResourceException("There's another user with email : " + dto.getEmail().toLowerCase());
		}
		if(!General.emailPattern.matcher(dto.getEmail()).matches()) {
			throw new DuplicatedResourceException("Email does not meet the required criteria : " + dto.getEmail().toLowerCase());
		}
		Users user = new Users();
		user.setName(dto.getName());
		user.setEmail(dto.getEmail());
		user.setPassword(dto.getPassword());
		
		Date d = Calendar.getInstance().getTime();
		user.setCreated(d);
		user.setModified(d);
		user.setToken(Base64.getEncoder().encodeToString(dto.getPassword().getBytes()));
		user.setActive(true);
		List<Phones> insertedPhones = this.validatePhonesForInsert(dto);
		user.setPhones(insertedPhones);
		return usersRepository.save(user);
	}

	@Override
	public Users updateUser(UsersDto user, long id) {
        Optional < Users > usersDb = this.usersRepository.findById(id);

        if (usersDb.isPresent()) {
        	Users userUpdate = usersDb.get();
    		Date d = Calendar.getInstance().getTime();
    		userUpdate.setModified(d);
        	userUpdate.setId(user.getId());
        	userUpdate.setName(user.getName());
        	if(!userUpdate.getEmail().equalsIgnoreCase(user.getEmail())) {
        		if(!General.emailPattern.matcher(user.getEmail()).matches()) {
        			throw new DuplicatedResourceException("Email does not meet the required criteria : " + user.getEmail().toLowerCase());
        		}
        		if(Objects.isNull(this.getUserByEmail(user))) {
        			userUpdate.setEmail(user.getEmail());
        		} else {			
                    throw new DuplicatedResourceException("There's another user with email : " + user.getEmail().toLowerCase());
        		}
        	}
    		userUpdate.setPassword(userUpdate.getPassword());
        	if(!Objects.isNull(user.getPassword()) && !user.getPassword().equalsIgnoreCase(userUpdate.getPassword())) {
        		if(!General.passwordPattern.matcher(user.getPassword()).matches()) {
        			throw new DuplicatedResourceException("The password does not meet the requirements");
        		}
        		userUpdate.setPassword(user.getPassword());
        	}
        	userUpdate.setToken(Base64.getEncoder().encodeToString(userUpdate.getPassword().getBytes()));
        	userUpdate.setPhones(this.validatePhonesForInsert(user));
        	userUpdate.setActive(user.isActive());
        	usersRepository.save(userUpdate);
            return userUpdate;
        } else {
            throw new ResourceNotFoundException("Record not found with id : " + user.getId());
        }
	}

	@Override
	public List<Users> getAllUsers() {
        return this.usersRepository.findAll();
	}

	@Override
	public Users getUserById(long userId) {
        Optional < Users > userDb = this.usersRepository.findById(userId);

        if (userDb.isPresent()) {
            return userDb.get();
        } else {
            throw new ResourceNotFoundException("Record not found with id : " + userId);
        }
	}

	@Override
	public void deleteUser(long id) {
        Optional < Users > userDb = this.usersRepository.findById(id);

        if (userDb.isPresent()) {
            this.usersRepository.delete(userDb.get());
        } else {
            throw new ResourceNotFoundException("Record not found with id : " + id);
        }
	}
	
	private List<Phones> validatePhonesForInsert(UsersDto dto) {
		List<PhonesDto> phones = dto.getPhones();
		List<Phones> insertedPhones = new ArrayList<Phones>();
		for (PhonesDto phone : phones) {
			if(Objects.isNull(phone.getId()) || phone.getId()==0) {
				insertedPhones.add(this.phonesService.createPhone(phone));
			} else {
				insertedPhones.add(this.phonesService.updatePhone(phone, phone.getId()));
			}
		}
		return insertedPhones;
	}

	@Override
	public Users getUserByEmail(UsersDto user) {
		Users userForSearch = new Users();
		userForSearch.setEmail(user.getEmail());
		Optional<Users> userDb = this.usersRepository.findOne(
				Example.of(userForSearch, 
						ExampleMatcher
							.matching()
							.withIgnoreNullValues()
							.withIgnoreCase()
							.withIgnorePaths("id","name","password","phones","created","modified","last_login","token","active")
						)
				);
		if(userDb.isPresent()) {
			return userDb.get();
		} else {
			return null;
		}
	}

}
