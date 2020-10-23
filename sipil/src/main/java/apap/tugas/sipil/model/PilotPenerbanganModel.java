package apap.tugas.sipil.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;
import org.hibernate.validator.constraints.UniqueElements;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.Date;
import java.util.List;

@Entity
@Table(name="pilotpenerbangan")
public class PilotPenerbanganModel implements Serializable{
    @Id
    @Size(max=20)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @DateTimeFormat
    @Column(name="tanggal_penugasan", nullable = false)
    private Date tanggal_penugasan;

    @ManyToOne(fetch = FetchType.EAGER, optional = false)
    @JoinColumn(name = "pilotId", referencedColumnName = "id", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JsonIgnore
    private PilotModel pilot;

    @ManyToOne(fetch = FetchType.EAGER, optional = false)
    @JoinColumn(name = "penerbanganId", referencedColumnName = "id", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JsonIgnore
    private PenerbanganModel penerbangan;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Date getTanggal_penugasan() {
        return tanggal_penugasan;
    }

    public void setTanggal_penugasan(Date tanggal_penugasan) {
        this.tanggal_penugasan = tanggal_penugasan;
    }

    public PilotModel getPilot() {
        return pilot;
    }

    public void setPilot(PilotModel pilot) {
        this.pilot = pilot;
    }

    public PenerbanganModel getPenerbangan() {
        return penerbangan;
    }

    public void setPenerbangan(PenerbanganModel penerbangan) {
        this.penerbangan = penerbangan;
    }
}