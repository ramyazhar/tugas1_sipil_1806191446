package apap.tugas.sipil.service;

import apap.tugas.sipil.model.PenerbanganModel;
import apap.tugas.sipil.model.PilotModel;
import apap.tugas.sipil.repository.PenerbanganDB;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Optional;

@Service
@Transactional
public class PenerbanganServiceImpl implements PenerbanganService{

    @Autowired
    PenerbanganDB penerbanganDB;

    @Override
    public void addPenerbangan(PenerbanganModel penerbangan){
        penerbanganDB.save(penerbangan);
    }
    @Override
    public Optional<PenerbanganModel> getPenerbanganByID(Long id){
      return penerbanganDB.findById(id);
    }

    @Override
    public  PenerbanganModel updatePenerbangan(PenerbanganModel penerbangan){

        PenerbanganModel targetPenerbangan = penerbanganDB.findById(penerbangan.getId()).get();
        try {
            targetPenerbangan.setKode(penerbangan.getKode());
            targetPenerbangan.setKota_asal(penerbangan.getKota_asal());
            targetPenerbangan.setKota_tujuan(penerbangan.getKota_tujuan());
            targetPenerbangan.setWaktu(penerbangan.getWaktu());
            penerbanganDB.save(penerbangan);
            return targetPenerbangan;

        } catch (NullPointerException nullException) {
            return null;
        }
    }
}
