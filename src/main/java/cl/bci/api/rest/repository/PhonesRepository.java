package cl.bci.api.rest.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import cl.bci.api.rest.model.Phones;

public interface PhonesRepository extends JpaRepository<Phones, Long> {

}
