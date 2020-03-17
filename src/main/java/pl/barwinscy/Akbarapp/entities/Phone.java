package pl.barwinscy.Akbarapp.entities;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
//equals and hash code
@Getter
@Entity
public class Phone {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Setter
    private String phoneNumber;

    @Setter
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "school_id", foreignKey = @ForeignKey(name = "FK_phone_school_id"))
    private School school;

    @Setter
    @Lob
    private String note;

    protected Phone (){

    }

    public Phone(String phoneNumber, School school) {
        this.phoneNumber = phoneNumber;
        this.school = school;
    }
}
