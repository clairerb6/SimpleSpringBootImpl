package cl.bci.api.rest.service;

import java.util.List;

import cl.bci.api.rest.model.Users;
import cl.bci.api.rest.model.dto.UsersDto;

public interface UsersService {
	Users createUser(UsersDto user);

	Users updateUser(UsersDto user, long id);

    List < Users > getAllUsers();

    Users getUserById(long userId);
    
    Users getUserByEmail(UsersDto user);

    void deleteUser(long id);
}
