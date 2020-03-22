package pl.barwinscy.Akbarapp.entities;

import lombok.*;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@ToString
@AllArgsConstructor
@Builder
@EqualsAndHashCode(exclude = {"phones"})
@Getter
@Entity
public class School {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
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

    public School(String type, String name, Address address, String email, String website, String status) {
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

    public static class SchoolBuilder {
        private SchoolBuilder id(Long id) {
            return this;
        }
    }
}
