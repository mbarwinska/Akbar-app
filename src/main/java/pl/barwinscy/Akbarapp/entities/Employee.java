package pl.barwinscy.Akbarapp.entities;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

//@EqualsAndHashCode
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Getter
@Entity
public class Employee {

    @Id
    @EqualsAndHashCode.Include
    private Long id;
    @Setter
    private String firstName;
    @Setter
    private String lastName;

    @OneToMany(mappedBy = "employee", fetch = FetchType.LAZY)
    private Set<School> schools = new HashSet<>();

    protected Employee(){

    }

    public Employee(Long id, String firstName, String lastName) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
    }

    public void setSchools(School school) {
        this.schools.add(school);
    }
}
