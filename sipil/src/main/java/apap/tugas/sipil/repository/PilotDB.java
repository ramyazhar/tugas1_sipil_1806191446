package apap.tugas.sipil.repository;
import apap.tugas.sipil.model.PilotModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface PilotDB extends JpaRepository<PilotModel,Long> {
    PilotModel findByNip(String nip);
    Optional<PilotModel> findById(Long id);
//    List<PilotModel> findAllByAkademi_Id(Long id);
    List<PilotModel> findAllByAkademi_Id(Long id);
    List<PilotModel> findAllByMaskapaiId(Long id);

}
