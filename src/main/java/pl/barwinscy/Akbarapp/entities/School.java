package pl.barwinscy.Akbarapp.entities;

import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Getter
@Setter
@Entity
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class School {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EqualsAndHashCode.Include
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

    @ManyToOne(fetch = FetchType.LAZY, cascade = {CascadeType.MERGE, CascadeType.PERSIST})
    @JoinColumn(name = "salesman", foreignKey = @ForeignKey(name = "FK_school_salesman_id"))
    private Salesman salesman;

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

    public void setSalesman(Salesman salesman) {
        if (this.salesman != null) {
            this.salesman.getSchools().remove(this);
        }
        this.salesman = salesman;
        salesman.setSchools(this);
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
}
