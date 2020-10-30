package apap.tugas.sipil.service;
import apap.tugas.sipil.model.AkademiModel;
import apap.tugas.sipil.repository.AkademiDB;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.NoSuchElementException;

@Service
@Transactional
public class AkademiServiceImpl implements AkademiService{

    @Autowired
    AkademiDB akademiDB;

    @Override
    public AkademiModel getAkademiById(Long id){
        return akademiDB.findById(id).get();
    }

    @Override
    public List<AkademiModel> getAllAkademi(){return akademiDB.findAll();}
}
