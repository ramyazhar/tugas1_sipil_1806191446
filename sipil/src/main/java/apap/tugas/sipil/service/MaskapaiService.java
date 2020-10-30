package apap.tugas.sipil.service;

import apap.tugas.sipil.model.MaskapaiModel;
import apap.tugas.sipil.model.PilotModel;

import java.util.List;

public interface MaskapaiService {
    MaskapaiModel getMaskapaiByKode(String kode);

    Long getIdMaskapaibyKode(String kode);

    List<MaskapaiModel> getAllMaskapai();
//    List<PilotModel> getListPilotByKodePenerbangan(String kode);
}
