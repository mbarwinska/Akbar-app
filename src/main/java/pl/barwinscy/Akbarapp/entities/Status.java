package pl.barwinscy.Akbarapp.entities;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@EqualsAndHashCode
@Getter
@Entity
//add column name
public class Status {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Setter
    private boolean ours;
    @Setter
    private boolean contracted;
    @Setter
    private Integer calendarsLeftNumber;

    protected Status(){

    }

    public Status(boolean ours, boolean notOurs, boolean contracted, boolean notContracted, Integer calendarsLeftNumber) {
        this.ours = ours;
        this.contracted = contracted;
        this.calendarsLeftNumber = calendarsLeftNumber;
    }
}
