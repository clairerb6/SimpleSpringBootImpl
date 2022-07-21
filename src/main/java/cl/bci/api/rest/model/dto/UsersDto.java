package cl.bci.api.rest.model.dto;

import java.util.Date;
import java.util.List;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class UsersDto {
    private long id;
    private String name;
    private String email;
    private String password;
    private List<PhonesDto> phones;
    private Date created;
    private Date modified;
    private Date lastLogin;
    private String token;
    private boolean active;
}
