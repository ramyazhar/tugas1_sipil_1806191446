package apap.tugas.sipil.service;

import apap.tugas.sipil.model.PilotModel;
import apap.tugas.sipil.model.PilotPenerbanganModel;

import java.util.List;

public interface PilotPenerbanganService {
    void addPilotPenerbangan(PilotPenerbanganModel pilotPenerbangan);


    List<PilotPenerbanganModel> getPilotPenerbanganList();

    List<PilotPenerbanganModel> pilotBulanIni(List<PilotPenerbanganModel> listPilotPenerbangan);

    List<PilotModel> getListPilot(List<PilotPenerbanganModel> listPilotPenerbangan);
}
