package pl.barwinscy.Akbarapp.entities;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
//equals and hash code
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Getter
@Entity
public class AdditionalInfo {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @EqualsAndHashCode.Include
    private Long id;

    @Setter
    @Lob
    private String note1;

    @Setter
    @Lob
    private String note2;

    @Setter
    @Lob
    private String note3;

    @Setter
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "schoolId",foreignKey = @ForeignKey(name = "FK_additional_info_school_id"))
    private School school;

    protected AdditionalInfo(){

    }

    public AdditionalInfo(String note1, String note2, String note3) {
        this.note1 = note1;
        this.note2 = note2;
        this.note3 = note3;
    }
}
