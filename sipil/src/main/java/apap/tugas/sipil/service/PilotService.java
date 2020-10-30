package apap.tugas.sipil.service;

import apap.tugas.sipil.model.PilotModel;

import java.util.List;

public interface PilotService {

    void addPilot(PilotModel pilot);

    String getNIP(PilotModel pilot);

    List<PilotModel> getPilotList();

    PilotModel getPilotByNipPilot(String nip);
//
    PilotModel getPilotByIdPilot(Long idPilot);

    PilotModel updatePilot(PilotModel pilot);

    List<PilotModel> cariPilot(Long idMaskapai, Long idAkademi);

    List<PilotModel> cariPilotMaskapai(Long idmaskapai);

    List<PilotModel> cariPilotSekolah(Long idSekolah);

//
//    void removePilot(PilotModel pilot);


}
