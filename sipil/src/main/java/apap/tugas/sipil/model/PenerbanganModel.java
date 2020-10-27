package apap.tugas.sipil.model;

import org.hibernate.validator.constraints.UniqueElements;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.Date;
import java.util.List;

@Entity
@Table(name="penerbangan")
public class PenerbanganModel implements Serializable{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Size(max=16)
    @Column(name="kode", nullable = false, unique = true)
    private String kode;

    @NotNull
    @Size(max=255)
    @Column(name="kota_asal", nullable = false)
    private String kota_asal;

    @NotNull
    @Size(max=255)
    @Column(name="kota_tujuan", nullable = false)
    private String kota_tujuan;

    @NotNull
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    @Column(name="waktu", nullable = false)
    private Date waktu;

    @OneToMany(mappedBy = "penerbangan", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<PilotPenerbanganModel> listPilotPenerbangan;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getKode() {
        return kode;
    }

    public void setKode(String kode) {
        this.kode = kode;
    }

    public String getKota_asal() {
        return kota_asal;
    }

    public void setKota_asal(String kota_asal) {
        this.kota_asal = kota_asal;
    }

    public String getKota_tujuan() {
        return kota_tujuan;
    }

    public void setKota_tujuan(String kota_tujuan) {
        this.kota_tujuan = kota_tujuan;
    }

    public Date getWaktu() {
        return waktu;
    }

    public void setWaktu(Date waktu) {
        this.waktu = waktu;
    }

    public List<PilotPenerbanganModel> getListPilotPenerbangan() {
        return listPilotPenerbangan;
    }

    public void setListPilotPenerbangan(List<PilotPenerbanganModel> listPilotPenerbangan) {
        this.listPilotPenerbangan = listPilotPenerbangan;
    }
}