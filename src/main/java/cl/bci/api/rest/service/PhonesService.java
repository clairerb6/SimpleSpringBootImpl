package cl.bci.api.rest.service;

import java.util.List;

import cl.bci.api.rest.model.Phones;
import cl.bci.api.rest.model.dto.PhonesDto;

public interface PhonesService {
	Phones createPhone(PhonesDto phone);

	Phones updatePhone(PhonesDto phone, long id);

    List < Phones > getAllPhones();

    Phones getPhoneById(long phoneId);

    void deletePhone(long id);
}
