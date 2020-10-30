package apap.tugas.sipil.service;

import apap.tugas.sipil.model.PenerbanganModel;
import apap.tugas.sipil.model.PilotModel;
import apap.tugas.sipil.model.PilotPenerbanganModel;
import apap.tugas.sipil.repository.PilotPenerbanganDB;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.sql.Date;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

@Service
@Transactional
public class PilotPenerbanganServiceImpl implements  PilotPenerbanganService{

    @Autowired
    PilotPenerbanganDB pilotPenerbanganDB;

    @Override
    public void addPilotPenerbangan(PilotPenerbanganModel pilotPenerbangan){
        LocalDate now = LocalDate.now();
        pilotPenerbangan.setTanggal_penugasan(convertToDateViaSqlDate(now));
        pilotPenerbanganDB.save(pilotPenerbangan);
    }
    @Override
    public List<PilotPenerbanganModel> getPilotPenerbanganList(){
        return pilotPenerbanganDB.findAll();
    }

    @Override
    public List<PilotPenerbanganModel> pilotBulanIni(List<PilotPenerbanganModel> listPilotPenerbangan) {
        List<PilotPenerbanganModel> hasilbulanIni= new ArrayList<>();
        LocalDate thisDate = LocalDate.now();
        Integer bulanini = thisDate.getMonthValue();

        for (PilotPenerbanganModel pilot : listPilotPenerbangan) {
            SimpleDateFormat formater = new SimpleDateFormat("MM");
            Integer bulanPilot = Integer.valueOf(formater.format(pilot.getPenerbangan().getWaktu()));
            boolean bulanpenentu = bulanini == bulanPilot;

            if (bulanpenentu == true ){
                hasilbulanIni.add(pilot);
            }
        }
        return hasilbulanIni;
    }
    
    @Override
    public List<PilotModel> getListPilot(List<PilotPenerbanganModel> listPilotPenerbangan){
        List<PilotModel> hasil  = new ArrayList<>();
        for (int i = 0; i < listPilotPenerbangan.size(); i++) {
            hasil.add(listPilotPenerbangan.get(i).getPilot());
        }
        return hasil;
    }



    public Date convertToDateViaSqlDate(LocalDate dateToConvert) {
        return java.sql.Date.valueOf(dateToConvert);
    }
}
