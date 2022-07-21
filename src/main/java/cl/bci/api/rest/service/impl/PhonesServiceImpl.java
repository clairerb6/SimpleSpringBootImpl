package cl.bci.api.rest.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cl.bci.api.rest.exception.ResourceNotFoundException;
import cl.bci.api.rest.model.Phones;
import cl.bci.api.rest.model.dto.PhonesDto;
import cl.bci.api.rest.repository.PhonesRepository;
import cl.bci.api.rest.service.PhonesService;

@Service
@Transactional
public class PhonesServiceImpl implements PhonesService {
	
	@Autowired
	private PhonesRepository phonesRepository;

	@Override
	public Phones createPhone(PhonesDto dto) {
		Phones phone = new Phones();
		phone.setCountrycode(dto.getCountrycode());
		phone.setCitycode(dto.getCitycode());
		phone.setNumber(dto.getNumber());
		return phonesRepository.save(phone);
	}

	@Override
	public Phones updatePhone(PhonesDto dto, long id) {
        Optional < Phones > phonesDb = this.phonesRepository.findById(id);

        if (phonesDb.isPresent()) {
        	Phones phoneUpdate = phonesDb.get();
        	phoneUpdate.setId(dto.getId());
        	phoneUpdate.setNumber(dto.getNumber());
        	phoneUpdate.setCitycode(dto.getCitycode());
        	phoneUpdate.setCountrycode(dto.getCountrycode());
        	phonesRepository.save(phoneUpdate);
            return phoneUpdate;
        } else {
            throw new ResourceNotFoundException("Record not found with id : " + id);
        }
	}

	@Override
	public List<Phones> getAllPhones() {
        return this.phonesRepository.findAll();
	}

	@Override
	public Phones getPhoneById(long phoneId) {
        Optional < Phones > phoneDb = this.phonesRepository.findById(phoneId);

        if (phoneDb.isPresent()) {
            return phoneDb.get();
        } else {
            throw new ResourceNotFoundException("Record not found with id : " + phoneId);
        }
	}

	@Override
	public void deletePhone(long id) {
        Optional < Phones > phoneDb = this.phonesRepository.findById(id);

        if (phoneDb.isPresent()) {
            this.phonesRepository.delete(phoneDb.get());
        } else {
            throw new ResourceNotFoundException("Record not found with id : " + id);
        }
	}

}
