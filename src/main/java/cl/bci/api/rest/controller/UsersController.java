package cl.bci.api.rest.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import cl.bci.api.rest.model.Phones;
import cl.bci.api.rest.model.Users;
import cl.bci.api.rest.model.dto.PhonesDto;
import cl.bci.api.rest.model.dto.UsersDto;
import cl.bci.api.rest.service.PhonesService;
import cl.bci.api.rest.service.UsersService;

@RestController
public class UsersController {

    @Autowired
    private UsersService usersService;

    @Autowired
    private PhonesService phonesService;
  
    
    /*
     * Sección dedicada al mantenedor de teléfonos
     */
    @GetMapping("/phones")
    public ResponseEntity < List < Phones >> getAllPhones() {
        return ResponseEntity.ok().body(phonesService.getAllPhones());
    }

    @GetMapping("/phones/{id}")
    public ResponseEntity < Phones > getPhonesById(@PathVariable long id) {
        return ResponseEntity.ok().body(phonesService.getPhoneById(id));
    }

    @PostMapping("/phones")
    public ResponseEntity < Phones > createPhones(@RequestBody PhonesDto phone) {
        return ResponseEntity.ok().body(this.phonesService.createPhone(phone));
    }

    @PutMapping("/phones/{id}")
    public ResponseEntity < Phones > updatePhones(@PathVariable long id, @RequestBody PhonesDto phone) {
        return ResponseEntity.ok().body(this.phonesService.updatePhone(phone, id));
    }
    
    @DeleteMapping("/phones/{id}")
    public HttpStatus deletePhones(@PathVariable long id) {
        this.phonesService.deletePhone(id);
        return HttpStatus.OK;
    }
    
    /*
     * Sección dedicada al mantenedor de usuarios
     */
    
    @GetMapping("/users")
    public ResponseEntity < List < Users >> getAllUsers() {
        return ResponseEntity.ok().body(usersService.getAllUsers());
    }

    @GetMapping("/users/{id}")
    public ResponseEntity < Users > getUserById(@PathVariable long id) {
        return ResponseEntity.ok().body(usersService.getUserById(id));
    }

    @PostMapping("/users")
    public ResponseEntity < Users > createUser(@RequestBody UsersDto user) {
        return ResponseEntity.ok().body(this.usersService.createUser(user));
    }

    @PutMapping("/users/{id}")
    public ResponseEntity < Users > updateUser(@PathVariable long id, @RequestBody UsersDto user) {
        return ResponseEntity.ok().body(this.usersService.updateUser(user, id));
    }
    
    @DeleteMapping("/users/{id}")
    public HttpStatus deleteUser(@PathVariable long id) {
        this.usersService.deleteUser(id);
        return HttpStatus.OK;
    }
}
