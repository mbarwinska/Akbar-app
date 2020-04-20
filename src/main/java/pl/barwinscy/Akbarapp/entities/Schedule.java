package pl.barwinscy.Akbarapp.entities;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDate;

@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Getter
@Entity
public class Schedule {
    @Id
    @Setter
    @GeneratedValue(strategy = GenerationType.AUTO)
    @EqualsAndHashCode.Include
    private Long id;

    @Setter
    private LocalDate contactDate;
    @Setter
    private LocalDate photographingDate;
    @Setter
    private LocalDate payoffDate;
    @Setter
    private LocalDate otherDate;

    @Setter
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
