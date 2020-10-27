package apap.tugas.sipil.repository;
import apap.tugas.sipil.model.PenerbanganModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PenerbanganDB extends JpaRepository<PenerbanganModel, Long> {
}
