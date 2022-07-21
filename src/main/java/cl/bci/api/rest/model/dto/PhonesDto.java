package cl.bci.api.rest.model.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class PhonesDto {
    private long id;
    private long number;
    private int citycode;
    private int countrycode;
}
