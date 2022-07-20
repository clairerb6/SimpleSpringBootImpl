package cl.bci.api.rest.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cl.bci.api.rest.exception.ResourceNotFoundException;
import cl.bci.api.rest.model.Phones;
import cl.bci.api.rest.repository.PhonesRepository;
import cl.bci.api.rest.service.PhonesService;

@Service
@Transactional
public class PhonesServiceImpl implements PhonesService {
	
	@Autowired
	private PhonesRepository phonesRepository;

	@Override
	public Phones createPhone(Phones phone) {
		return phonesRepository.save(phone);
	}

	@Override
	public Phones updatePhone(Phones phone) {
        Optional < Phones > phonesDb = this.phonesRepository.findById(phone.getId());

        if (phonesDb.isPresent()) {
        	Phones phoneUpdate = phonesDb.get();
        	phoneUpdate.setId(phone.getId());
        	phoneUpdate.setNumber(phone.getNumber());
        	phoneUpdate.setCitycode(phone.getCitycode());
        	phoneUpdate.setCountrycode(phone.getCountrycode());
        	phonesRepository.save(phoneUpdate);
            return phoneUpdate;
        } else {
            throw new ResourceNotFoundException("Record not found with id : " + phone.getId());
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
