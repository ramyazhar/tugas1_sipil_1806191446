package apap.tugas.sipil.model;

        import com.fasterxml.jackson.annotation.JsonIgnore;
        import org.hibernate.annotations.GenericGenerator;
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
@Table(name="pilot")
public class PilotModel implements Serializable{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Size(max=255)
    @Column(name="namaPilot", nullable = false)
    private String namaPilot;

    @NotNull
    @Size(max=13)
    @Column(name="nip", nullable = false, unique = true)
    private String nip;

    @NotNull
    @Size(max=255)
    @Column(name="nik", nullable = false)
    private String nik;

    @NotNull
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    @Column(name="tanggal_lahir", nullable = false)
    private Date tanggal_lahir;

    @NotNull
    @Size(max=255)
    @Column(name="tempat_lahir", nullable = false)
    private String tempat_lahir;

    @NotNull
    @Column(name="jenis_kelamin", nullable = false)
    private Integer jenis_kelamin;

    @ManyToOne(fetch = FetchType.EAGER, optional = false)
    @JoinColumn(name = "akademiId", referencedColumnName = "id", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JsonIgnore
    private AkademiModel akademi;

    @ManyToOne(fetch = FetchType.EAGER, optional = false)
    @JoinColumn(name = "maskapaiId", referencedColumnName = "id", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JsonIgnore
    private MaskapaiModel maskapai;

    @OneToMany(mappedBy = "pilot", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<PilotPenerbanganModel> listPilotPenerbangan;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNamaPilot() {
        return namaPilot;
    }

    public void setNamaPilot(String namaPilot) {
        this.namaPilot = namaPilot;
    }

    public String getNip() {
        return nip;
    }

    public void setNip(String nip) {
        this.nip = nip;
    }

    public String getNik() {
        return nik;
    }

    public void setNik(String nik) {
        this.nik = nik;
    }

    public Date getTanggal_lahir() {
        return tanggal_lahir;
    }

    public void setTanggal_lahir(Date tanggal_lahir) {
        this.tanggal_lahir = tanggal_lahir;
    }

    public String getTempat_lahir() {
        return tempat_lahir;
    }

    public void setTempat_lahir(String tempat_lahir) {
        this.tempat_lahir = tempat_lahir;
    }

    public Integer getJenis_kelamin() {
        return jenis_kelamin;
    }

    public void setJenis_kelamin(Integer jenis_kelamin) {
        this.jenis_kelamin = jenis_kelamin;
    }

    public AkademiModel getAkademi() {
        return akademi;
    }

    public void setAkademi(AkademiModel akademi) {
        this.akademi = akademi;
    }

    public MaskapaiModel getMaskapai() {
        return maskapai;
    }

    public void setMaskapai(MaskapaiModel maskapai) {
        this.maskapai = maskapai;
    }

    public List<PilotPenerbanganModel> getListPilotPenerbangan() {
        return listPilotPenerbangan;
    }

    public void setListPilotPenerbangan(List<PilotPenerbanganModel> listPilotPenerbangan) {
        this.listPilotPenerbangan = listPilotPenerbangan;
    }
}