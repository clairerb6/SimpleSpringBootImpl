package cl.bci.api.rest.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import cl.bci.api.rest.model.Users;

public interface UsersRepository extends JpaRepository<Users, Long>  {

}
