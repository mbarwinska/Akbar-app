package pl.barwinscy.Akbarapp.entities;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

//@EqualsAndHashCode
@Getter
@Entity
public class Employee {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    //private Long employeeId
    @Setter
    private String firstName;
    @Setter
    private String lastName;

    @OneToMany(mappedBy = "employee", fetch = FetchType.LAZY)
    private Set<School> schools = new HashSet<>();

    protected Employee(){

    }

    public Employee(String firstName, String lastName) {
        this.firstName = firstName;
        this.lastName = lastName;
    }

    public void setSchools(School school) {
        this.schools.add(school);
    }
}
