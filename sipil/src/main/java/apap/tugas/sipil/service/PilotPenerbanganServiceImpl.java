package apap.tugas.sipil.service;

import apap.tugas.sipil.model.PenerbanganModel;
import apap.tugas.sipil.model.PilotPenerbanganModel;
import apap.tugas.sipil.repository.PilotPenerbanganDB;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.sql.Date;
import java.time.LocalDate;
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
    public Date convertToDateViaSqlDate(LocalDate dateToConvert) {
        return java.sql.Date.valueOf(dateToConvert);
    }
}
