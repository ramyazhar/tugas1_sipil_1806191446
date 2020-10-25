package apap.tugas.sipil.service;

import apap.tugas.sipil.model.PilotModel;

import java.util.List;

public interface PilotService {

    void addPilot(PilotModel pilot);

    String getNIP(PilotModel pilot);

    List<PilotModel> getPilotList();

    PilotModel getPilotByNipPilot(String nip);
//
//    PilotModel getPilotByIdPilot(Long idPilot);
//
//    void removePilot(PilotModel pilot);


}
