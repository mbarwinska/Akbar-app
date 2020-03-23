package pl.barwinscy.Akbarapp.entities;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Getter
@Entity
public class Status {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @EqualsAndHashCode.Include
    private Long id;

    @Setter
    private boolean ours;
    @Setter
    private boolean contracted;
    @Setter
    private Integer calendarsLeftNumber;

    @Setter
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "school_id", foreignKey = @ForeignKey(name = "FK_status_school_id"))
    private School school;

    protected Status(){

    }

    public Status(boolean ours, boolean contracted, Integer calendarsLeftNumber) {
        this.ours = ours;
        this.contracted = contracted;
        this.calendarsLeftNumber = calendarsLeftNumber;
    }
}
