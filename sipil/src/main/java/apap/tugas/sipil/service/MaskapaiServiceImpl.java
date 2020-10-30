package apap.tugas.sipil.service;

import apap.tugas.sipil.model.MaskapaiModel;
import apap.tugas.sipil.repository.MaskapaiDB;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.NoSuchElementException;

@Service
@Transactional
public class MaskapaiServiceImpl implements  MaskapaiService{

    @Autowired
    MaskapaiDB maskapaiDB;

    @Override
    public MaskapaiModel getMaskapaiByKode(String kode){
        return  maskapaiDB.findByKode(kode);
    }

    @Override
    public Long getIdMaskapaibyKode(String kode){
        return maskapaiDB.findByKode(kode).getId();
    }

    @Override
    public  List<MaskapaiModel> getAllMaskapai(){return maskapaiDB.findAll();}
}
