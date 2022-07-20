package cl.bci.api.rest.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cl.bci.api.rest.exception.ResourceNotFoundException;
import cl.bci.api.rest.model.Users;
import cl.bci.api.rest.repository.PhonesRepository;
import cl.bci.api.rest.repository.UsersRepository;
import cl.bci.api.rest.service.UsersService;

@Service
@Transactional
public class UsersServiceImpl implements UsersService {
	
	@Autowired
	private UsersRepository usersRepository;
	
	@Autowired
	private PhonesRepository phonesRepository;
	
	@Override
	public Users createUser(Users user) {
		return usersRepository.save(user);
	}

	@Override
	public Users updateUser(Users user) {
        Optional < Users > usersDb = this.usersRepository.findById(user.getId());

        if (usersDb.isPresent()) {
        	Users userUpdate = usersDb.get();
        	userUpdate.setId(user.getId());
        	userUpdate.setName(user.getName());
        	userUpdate.setEmail(user.getEmail());
        	userUpdate.setPassword(user.getPassword());
        	userUpdate.setPhones(user.getPhones());
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

}
