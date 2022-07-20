package cl.bci.api.rest.service;

import java.util.List;

import cl.bci.api.rest.model.Phones;

public interface PhonesService {
	Phones createPhone(Phones phone);

	Phones updatePhone(Phones phone);

    List < Phones > getAllPhones();

    Phones getPhoneById(long phoneId);

    void deletePhone(long id);
}
