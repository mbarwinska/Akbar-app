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
    private String publicStatus;


    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "employee", foreignKey = @ForeignKey(name = "FK_school_employee_id"))
    private Employee employee;

    @OneToOne(mappedBy = "school", cascade = CascadeType.ALL)
    private Schedule schedule;

    @OneToOne(mappedBy = "school",cascade = CascadeType.ALL)
    private Status status;

    @OneToMany(mappedBy = "school", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private Set<Phone> phones = new HashSet<>();

    @OneToOne(mappedBy = "school", cascade = CascadeType.ALL)
    private AdditionalInfo additionalInfo;

    protected School() {

    }

    @Builder
    public School(Long id, String type, String name, Address address, String email, String website, String publicStatus) {
        this.id = id;
        this.type = type;
        this.name = name;
        this.address = address;
        this.email = email;
        this.website = website;
        this.publicStatus = publicStatus;
    }

    public void setEmployee(Employee employee) {
        this.employee = employee;
        employee.setSchools(this);
    }

    public void setSchedule(Schedule schedule) {
        this.schedule = schedule;
        schedule.setSchool(this);
    }

    public void setPhones(Phone phone) {
        this.phones.add(phone);
        phone.setSchool(this);
    }

    public void setStatus(Status status) {
        this.status = status;
        status.setSchool(this);
    }

    public void setAdditionalInfo(AdditionalInfo additionalInfo) {
        this.additionalInfo = additionalInfo;
        additionalInfo.setSchool(this);
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
