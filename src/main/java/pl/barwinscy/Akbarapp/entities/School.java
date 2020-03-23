package pl.barwinscy.Akbarapp.entities;

import lombok.*;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@ToString
@Getter
@Entity
public class School {

    @Id
    private Long id;

    //make Enum form that
    @Setter
    private String type;

    @Setter
    private String name;

    @Setter
    @Embedded
    private Address address;

    @Setter
    private String email;

    @Setter
    private String website;

    //make Enum from that too?
    @Setter
    private String status;

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
    @OneToOne(mappedBy = "school", cascade = CascadeType.ALL)
    private AdditionalInfo additionalInfo;

    protected School() {

    }

    @Builder
    public School(Long id, String type, String name, Address address, String email, String website, String status) {
        this.id = id;
        this.type = type;
        this.name = name;
        this.address = address;
        this.email = email;
        this.website = website;
        this.status = status;
    }

    public void setPhones(Phone phone) {
        this.phones.add(phone);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        School school = (School) o;
        return Objects.equals(id, school.id) &&
                Objects.equals(type, school.type);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, type);
    }
}
