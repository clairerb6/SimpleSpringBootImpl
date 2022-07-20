package cl.bci.api.rest.service;

import java.util.List;

import cl.bci.api.rest.model.Users;

public interface UsersService {
	Users createUser(Users user);

	Users updateUser(Users user);

    List < Users > getAllUsers();

    Users getUserById(long userId);
    
    Users getUserByEmail(Users user);

    void deleteUser(long id);
}
