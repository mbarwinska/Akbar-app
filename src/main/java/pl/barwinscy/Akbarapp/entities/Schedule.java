package pl.barwinscy.Akbarapp.entities;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDate;

@EqualsAndHashCode
@Getter
@Entity
public class Schedule {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Setter
    private LocalDate contactDate;
    @Setter
    private LocalDate photographingDate;
    @Setter
    private LocalDate payoffDate;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "school_id", foreignKey = @ForeignKey(name = "FK_schedule_school_id"))
    private School school;

    protected Schedule() {

    }

    public Schedule(LocalDate contactDate, LocalDate photographingDate, LocalDate payoffDate) {
        this.contactDate = contactDate;
        this.photographingDate = photographingDate;
        this.payoffDate = payoffDate;
    }
}
