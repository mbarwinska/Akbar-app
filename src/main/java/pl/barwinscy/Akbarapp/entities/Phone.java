package pl.barwinscy.Akbarapp.entities;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;

@EqualsAndHashCode(exclude = {"note"})
@ToString
@Getter
@Setter
@Entity
public class Phone {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private Long schoolRSPO;

    private String phoneNumber;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "school_id", foreignKey = @ForeignKey(name = "FK_phone_school_id"))
    private School school;

    @Lob
    private String note;

    protected Phone() {
    }

    public Phone(String phoneNumber, Long rspo) {
        this.phoneNumber = phoneNumber;
        this.schoolRSPO = rspo;
    }

    public Phone(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }
}
