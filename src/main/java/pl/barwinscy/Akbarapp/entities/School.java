package pl.barwinscy.Akbarapp.entities;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.util.*;

@ToString
@Getter
@Setter
@Entity
public class School {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long rspo;

    private String type;

    private String name;

    @Embedded
    private Address address;

    private String email;

    private String website;

    private String publicStatus;

    @ManyToOne(fetch = FetchType.LAZY, cascade = {CascadeType.MERGE, CascadeType.PERSIST})
    @JoinColumn(name = "employee", foreignKey = @ForeignKey(name = "FK_school_employee_id"))
    private Employee employee;

    @OneToOne(mappedBy = "school", cascade = CascadeType.ALL)
    private Schedule schedule;

    @OneToOne(mappedBy = "school", cascade = CascadeType.ALL)
    private Status status;

    @OneToMany(mappedBy = "school", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<Phone> phones = new ArrayList<>();

    @OneToOne(mappedBy = "school", cascade = CascadeType.ALL)
    private AdditionalInfo additionalInfo;

    protected School() {
    }

    @Builder
    public School(Long rspo, String type, String name, Address address, String email, String website, String publicStatus) {
        this.rspo = rspo;
        this.type = type;
        this.name = name;
        this.address = address;
        this.email = email;
        this.website = website;
        this.publicStatus = publicStatus;
    }

    public void setEmployee(Employee employee) {
        if (this.employee != null) {
            this.employee.getSchools().remove(this);
        }
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
