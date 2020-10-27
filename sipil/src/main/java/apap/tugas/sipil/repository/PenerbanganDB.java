package apap.tugas.sipil.repository;
import apap.tugas.sipil.model.PenerbanganModel;
import apap.tugas.sipil.model.PilotModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PenerbanganDB extends JpaRepository<PenerbanganModel,Long> {

    Optional<PenerbanganModel> findById(Long id);
}
