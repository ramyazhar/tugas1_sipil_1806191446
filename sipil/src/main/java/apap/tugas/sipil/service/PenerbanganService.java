package apap.tugas.sipil.service;

import apap.tugas.sipil.model.PenerbanganModel;

import java.util.Optional;

public interface PenerbanganService {
    void addPenerbangan(PenerbanganModel penerbangan);

    Optional<PenerbanganModel> getPenerbanganByID(Long id);

    PenerbanganModel updatePenerbangan(PenerbanganModel penerbangan);
}
