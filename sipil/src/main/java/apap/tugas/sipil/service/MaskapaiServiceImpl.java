package apap.tugas.sipil.service;

import apap.tugas.sipil.model.MaskapaiModel;
import apap.tugas.sipil.model.PilotModel;
import apap.tugas.sipil.repository.MaskapaiDB;
import apap.tugas.sipil.repository.PilotDB;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

@Service
@Transactional
public class MaskapaiServiceImpl implements  MaskapaiService{

    @Autowired
    MaskapaiDB maskapaiDB;

    @Autowired
    PilotDB pilotDb;

    @Override
    public MaskapaiModel getMaskapaiByKode(String kode){
        try{
            return  maskapaiDB.findByKode(kode);
        }catch (NoSuchElementException e){
            return null;
        }

    }

    @Override
    public Long getIdMaskapaibyKode(String kode){
        return maskapaiDB.findByKode(kode).getId();
    }

    @Override
    public  List<MaskapaiModel> getAllMaskapai(){return maskapaiDB.findAll();}


}
