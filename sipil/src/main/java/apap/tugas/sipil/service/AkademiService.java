package apap.tugas.sipil.service;

import apap.tugas.sipil.model.AkademiModel;

import java.util.List;

public interface AkademiService {
    AkademiModel getAkademiById(Long id);

    List<AkademiModel> getAllAkademi();
}
