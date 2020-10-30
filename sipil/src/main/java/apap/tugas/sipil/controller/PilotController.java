package apap.tugas.sipil.controller;

import apap.tugas.sipil.model.AkademiModel;
import apap.tugas.sipil.model.MaskapaiModel;
import apap.tugas.sipil.model.PilotModel;
import apap.tugas.sipil.model.PilotPenerbanganModel;
import apap.tugas.sipil.service.AkademiService;
import apap.tugas.sipil.service.MaskapaiService;
import apap.tugas.sipil.service.PilotPenerbanganService;
import apap.tugas.sipil.service.PilotService;
import apap.tugas.sipil.service.PilotServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Controller
public class PilotController {
    @Qualifier("pilotServiceImpl")
    @Autowired
    private PilotService pilotService;

    @Autowired
    private MaskapaiService maskapaiService;

    @Autowired
    private AkademiService akademiService;

    @Autowired
    private PilotPenerbanganService pilotPenerbanganService;

    @GetMapping("/")
    private String home() {
        return "home";
    }

    @GetMapping("/pilot")
    public String viewAllpilot(Model model){
        List<PilotModel> pilot = pilotService.getPilotList();
        model.addAttribute("listPilot", pilot);
        return "allpilot";
    }

    @GetMapping("/pilot/tambah")
    public String addPilotFormPage(Model model){
        model.addAttribute("pilot", new PilotModel());
        return "form-add-pilot";
    }
    //        List<PilotPenerbanganModel> listPilotPenerbanganModel = new ArrayList<>();
//        pilot.setListPilotPenerbangan(listPilotPenerbanganModel);
    @PostMapping("/pilot/tambah")
    public String addPilotSubmit(
            @ModelAttribute PilotModel pilot,
            Model model) {
        pilot.setNip(pilotService.getNIP(pilot));
        pilotService.addPilot(pilot);
        model.addAttribute("nipPilot", pilotService.getNIP(pilot));
        return "add-pilot";
    }

    @GetMapping("/pilot/{nipPilot}")
    public String findPilotByNipPilot(
            @PathVariable String nipPilot,
            Model model){
        PilotModel pilot = pilotService.getPilotByNipPilot(nipPilot);
        model.addAttribute("pilot",pilot);
        return "view-by-nippilot";
    }

    @RequestMapping(value="pilot/ubah/{nipPilot}", method=RequestMethod.GET)
    public String changePilotFormPage(@PathVariable String nipPilot, Model model) {
        PilotModel pilot = pilotService.getPilotByNipPilot(nipPilot);
        model.addAttribute("pilot", pilot);
//        if (pilot == null) {
//            return "/";
//        }
        return "form-update-pilot";
    }
    @PostMapping("/pilot/ubah")
    public String ubahPilotFormSubmit(
            @ModelAttribute PilotModel pilot,
            Model model
    ){
        System.out.println(pilot);
        PilotModel pilotUpdated = pilotService.updatePilot(pilot);
        model.addAttribute("pilot", pilotUpdated);
        return "update-pilot";
    }

    @RequestMapping(value = "/cari/pilot", method = RequestMethod.GET)
    public String cariPilotKosong(Model model) {

        List<AkademiModel> listAkademi = akademiService.getAllAkademi();
        model.addAttribute("listAkademi", listAkademi);

        List <MaskapaiModel> listMaskapai = maskapaiService.getAllMaskapai();
        model.addAttribute("listMaskapai", listMaskapai);

        return "cari-pilot";
    }

    @RequestMapping(value="/cari/pilot", method=RequestMethod.GET, params= {"kodeMaskapai","idSekolah"})
    public String cariPilot(
            @RequestParam(required = false, value = "kodeMaskapai") String kodeMaskapai,
            @RequestParam(required = false,value = "idSekolah") Long idSekolah,
            Model model){
        System.out.println(kodeMaskapai);

        System.out.println(idSekolah);
        List<MaskapaiModel> listMaskapai = maskapaiService.getAllMaskapai();
        List<AkademiModel> listAkademi = akademiService.getAllAkademi();
        if (kodeMaskapai != null && idSekolah != null) {
            System.out.println("masuk 22nya");
            Long idmaskapai = maskapaiService.getIdMaskapaibyKode(kodeMaskapai);
            List<PilotModel> listPilot = pilotService.cariPilot(idmaskapai, idSekolah);
            model.addAttribute("listMaskapai", listMaskapai);
            model.addAttribute("listAkademi", listAkademi);
            model.addAttribute("listPilot", listPilot);
            return "cari-pilot";
        }if (kodeMaskapai != null && idSekolah ==null){
            System.out.println("masuk kodemaskapai");
            Long idmaskapai2 = maskapaiService.getIdMaskapaibyKode(kodeMaskapai);
            List<PilotModel> listPilot = pilotService.cariPilotMaskapai(idmaskapai2);
            model.addAttribute("listMaskapai", listMaskapai);
            model.addAttribute("listAkademi", listAkademi);
            model.addAttribute("listPilot", listPilot);
            return "cari-pilot";
        }if (idSekolah !=null && kodeMaskapai == null){
            System.out.println("masuk idsekolahdoang");
            List<PilotModel> listPilot = pilotService.cariPilotSekolah(idSekolah);
            model.addAttribute("listMaskapai", listMaskapai);
            model.addAttribute("listAkademi", listAkademi);
            model.addAttribute("listPilot", listPilot);
            return "cari-pilot";
        }else{
            System.out.println("masuk sini");
            model.addAttribute("listMaskapai", listMaskapai);
            model.addAttribute("listAkademi", listAkademi);
            return "cari-pilot";
        }

    }

    @RequestMapping(value = "/cari/pilot/penerbangan-terbanyak", method = RequestMethod.GET)
    public String cariPilotTerbanyak(Model model) {

        List <MaskapaiModel> listMaskapai = maskapaiService.getAllMaskapai();
        model.addAttribute("listMaskapai", listMaskapai);

        return "cari-pilot-terbanyak";
    }

    @RequestMapping(value = "/cari/pilot/bulan-ini", method = RequestMethod.GET)
    public String cariPilotBulanIni(Model model) {

//        List <MaskapaiModel> listMaskapai = maskapaiService.getAllMaskapai();
        List<PilotPenerbanganModel> listPilotPenerbangan = pilotPenerbanganService.getPilotPenerbanganList();
        List<PilotPenerbanganModel> listPilothu = pilotPenerbanganService.pilotBulanIni(listPilotPenerbangan);
        List<PilotModel> listPilot = pilotPenerbanganService.getListPilot(listPilothu);
        model.addAttribute("listPilot", listPilot);

        return "cari-pilotbulanini";
    }

//    @RequestMapping(path = "/cari/pilot/bulan-ini", method = RequestMethod.GET)
//    public String ExpiredMed(Model model){
//        GudangModel gudang = gudangService.getGudangbyId(idGudang).get();
//
//        List<GudangObatModel> listGudangObat = gudang.getListGudangobat();
//
//        listGudangObat = gudangObatService.sortbyDate(listGudangObat);
//
//        model.addAttribute("gudangObatList", listGudangObat);
//
//        return "pilot-bulan-ini";
//    }









}
