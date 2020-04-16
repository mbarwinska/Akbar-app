package pl.barwinscy.Akbarapp.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PhoneDTO {

    private Long id;
    private String number;
    private String note;
    private boolean toDelete;

    public PhoneDTO(){}

    public PhoneDTO(Long id, String number, String note) {
        this.id = id;
        this.number = number;
        this.note = note;
    }
}
