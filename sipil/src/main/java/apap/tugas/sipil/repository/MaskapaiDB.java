package apap.tugas.sipil.repository;
import apap.tugas.sipil.model.MaskapaiModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MaskapaiDB extends JpaRepository<MaskapaiModel,Long> {
    MaskapaiModel findByKode(String kode);

}
