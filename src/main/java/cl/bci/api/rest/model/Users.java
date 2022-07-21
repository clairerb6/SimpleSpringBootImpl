package cl.bci.api.rest.model;

import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.Email;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@Table(name = "users")
public class Users {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    
    @Column(name = "name")
    private String name;
    
    @Email
    @Column(name = "email")
    private String email;
    
    @Column(name = "password")
    private String password;

    @JoinColumn(name = "phones")
    @OneToMany
    private List<Phones> phones;
    
    @JsonIgnore
    @Column(name = "created")
    private Date created;
    
    @Column(name = "modified")
    private Date modified;
    
    @Column(name = "last_login")
    private Date lastLogin;
    
    @Column(name = "token")
    private String token;
    
    @Column(name = "active")
    private boolean active;
}
