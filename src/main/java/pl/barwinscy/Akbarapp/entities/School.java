package pl.barwinscy.Akbarapp.entities;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

//equals and hash code excluded
@Getter
@Entity
public class School {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Setter
    private String name;

 /*   @Embeddable
    private Address address;*/

    @Setter
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "employee", foreignKey = @ForeignKey(name = "FK_school_employee_id"))
    private Employee employee;

    @Setter
    @Lob
    private String notes;

    @Setter
    @OneToOne(mappedBy = "school")
    private Schedule schedule;

    @OneToMany(mappedBy = "school", fetch = FetchType.LAZY)
    private Set<Phone> phones = new HashSet<>();

    @Setter
    @OneToOne(mappedBy = "school",cascade = CascadeType.ALL)
    private AdditionalInfo additionalInfo;

    protected School (){

    }

    public School(String name, Employee employee, String notes) {
        this.name = name;
        this.employee = employee;
        this.notes = notes;
    }

    public void setPhones(Phone phone) {
        this.phones.add(phone);
    }
}
