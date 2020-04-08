package pl.barwinscy.Akbarapp.entities;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;

@EqualsAndHashCode(exclude = {"note"})
@ToString
@Getter
@Entity
public class Phone {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Setter
    private Long schoolRSPO;

    @Setter
    private String phoneNumber;

    @Setter
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "school_id", foreignKey = @ForeignKey(name = "FK_phone_school_id"))
    private School school;

    @Setter
    @Lob
    private String note;

    protected Phone() {

    }

    public Phone(String phoneNumber, Long rspo) {
        this.phoneNumber = phoneNumber;
        this.schoolRSPO = rspo;
    }

    public Phone(String phoneNumber, Long rspo, String note) {
        this.phoneNumber = phoneNumber;
        this.schoolRSPO = rspo;
        this.note = note;
    }

    public Phone(String phoneNumber) {

        this.phoneNumber = phoneNumber;
    }


}
