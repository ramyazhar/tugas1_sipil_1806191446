package apap.tugas.sipil.service;

import apap.tugas.sipil.model.PilotModel;
import apap.tugas.sipil.repository.PilotDB;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.LocalDate;
import java.util.*;
import java.text.SimpleDateFormat;

@Service
@Transactional
public class PilotServiceImpl implements PilotService{

    @Autowired
    PilotDB pilotDB;

    @Override
    public void addPilot(PilotModel pilot){
        pilot.setNip(getNIP(pilot));
        pilotDB.save(pilot);

    }
    @Override
    public List<PilotModel> getPilotList(){
        return pilotDB.findAll();
    }

    @Override
    public PilotModel getPilotByNipPilot(String nip){
        return pilotDB.findByNip(nip);
    }

    @Override
    public PilotModel getPilotByIdPilot(Long id){
        return pilotDB.findById(id).get();
    }

    @Override
    public PilotModel updatePilot(PilotModel pilot){
        PilotModel targetPilot = pilotDB.findById(pilot.getId()).get();
        try {
            targetPilot.setNamaPilot(pilot.getNamaPilot());
            targetPilot.setNik(pilot.getNik());
            targetPilot.setTempat_lahir(pilot.getTempat_lahir());
            targetPilot.setTanggal_lahir(pilot.getTanggal_lahir());
            targetPilot.setJenis_kelamin(pilot.getJenis_kelamin());
            targetPilot.setAkademi(pilot.getAkademi());
            targetPilot.setMaskapai(pilot.getMaskapai());
            targetPilot.setNip(targetPilot.getNip());
            pilotDB.save(targetPilot);
            return targetPilot;
        } catch (NullPointerException nullException) {
            return null;
        }
    }

    @Override
    public String getNIP(PilotModel pilot) {
        Integer kelamin = pilot.getJenis_kelamin();
        String tempatlahir = pilot.getTempat_lahir();
        String namapilot = pilot.getNamaPilot();
        Date tanggallahir = pilot.getTanggal_lahir();
        SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");
        String format = formatter.format(tanggallahir);
        Double formattaun = Math.floor((Double.parseDouble(format.substring(6)))/10);
        int taunnip = formattaun.intValue();
        String random = RandomString.getAlphaNumericString(2);

        String nip;

        nip = kelamin + tempatlahir.toUpperCase().substring(0,2) + namapilot.toUpperCase().charAt(namapilot.length()-1)+
                format.substring(0,2) + format.substring(3,5) + taunnip + random;
        return nip;
    }

    @Override
    public List<PilotModel> cariPilot(Long idMaskapai, Long idAkademi){
        List<PilotModel> hasilCariPilot = new ArrayList<>();
        List<PilotModel> allPilot = getPilotList();
        for (int i = 0; i < allPilot.size(); i++) {
            if (allPilot.get(i).getMaskapai().getId().equals(idMaskapai)){
                if (allPilot.get(i).getAkademi().getId().equals(idAkademi)){
                    hasilCariPilot.add(allPilot.get(i));
                }
            }
        }
        return hasilCariPilot;
    }

    public List<PilotModel> cariPilotSekolah(Long idAkademi){
        System.out.println("masuk sini");
        Long idsimpenan = idAkademi;
        List<PilotModel> hasilCariPilot = new ArrayList<>();
        List<PilotModel> allPilot = getPilotList();
        for (int i = 0; i < allPilot.size(); i++) {
                if (allPilot.get(i).getAkademi().getId().equals(idsimpenan)){
                    hasilCariPilot.add(allPilot.get(i));
                    System.out.println("di add");
                }
        }
        return hasilCariPilot;
    }
    public List<PilotModel> cariPilotMaskapai(Long idMaskapai){
        List<PilotModel> hasilCariPilot = new ArrayList<>();
        List<PilotModel> allPilot = getPilotList();
        for (int i = 0; i < allPilot.size(); i++) {
            if (allPilot.get(i).getMaskapai().getId().equals(idMaskapai)){
                hasilCariPilot.add(allPilot.get(i));
            }

        }
        return hasilCariPilot;
    }




    public static class RandomString {

        // function to generate a random string of length n
        static String getAlphaNumericString(int n)
        {

            // chose a Character random from this String
            String AlphaNumericString = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";

            // create StringBuffer size of AlphaNumericString
            StringBuilder sb = new StringBuilder(n);

            for (int i = 0; i < n; i++) {

                // generate a random number between
                // 0 to AlphaNumericString variable length
                int index
                        = (int)(AlphaNumericString.length()
                        * Math.random());

                // add Character one by one in end of sb
                sb.append(AlphaNumericString
                        .charAt(index));
            }

            return sb.toString();
        }}

    }
