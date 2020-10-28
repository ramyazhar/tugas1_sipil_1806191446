package apap.tugas.sipil.controller;


import apap.tugas.sipil.model.PenerbanganModel;
import apap.tugas.sipil.model.PilotModel;
import apap.tugas.sipil.model.PilotPenerbanganModel;
import apap.tugas.sipil.service.PenerbanganService;
import apap.tugas.sipil.service.PilotPenerbanganService;
import apap.tugas.sipil.service.PilotService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Controller
public class PenerbanganController {
    @Qualifier("penerbanganServiceImpl")

    @Autowired
    private PenerbanganService penerbanganService;

    @Autowired
    private PilotPenerbanganService pilotPenerbanganService;

    @Autowired
    private PilotService pilotService;

    @GetMapping("/penerbangan/tambah")
    public String addPenerbanganFormPage(Model model){
        model.addAttribute("penerbangan", new PenerbanganModel());
        return "form-add-penerbangan";
    }

    @GetMapping("/penerbangan/detil/{id}")
    public String findPenerbanganByidPenerbangan(
            @PathVariable Long id,
            Model model){
        PenerbanganModel penerbangan = penerbanganService.getPenerbanganByID(id).get();
        model.addAttribute("penerbangan",penerbangan);
        return "view-by-idpenerbangan";
    }

    @GetMapping("/penerbangan")
    public String viewAllPenerbangan(Model model){
        List<PenerbanganModel> listPenerbangan = penerbanganService.getListPenerbangan();
        model.addAttribute("listPenerbangan", listPenerbangan);
        return "allpenerbangan";
    }

    @PostMapping("/penerbangan/tambah")
    public String addPenerbanganSubmit(
            @ModelAttribute PenerbanganModel penerbangan,
            Model model) {
//        PilotModel pilot = new PilotModel();
//        PilotPenerbanganModel pilotPenerbangan = new PilotPenerbanganModel();
//        List<PilotPenerbanganModel> listPilotPenerbanganModel = new ArrayList<>();
//
//        pilotPenerbangan.setPilot(pilot);
//        pilotPenerbangan.setPenerbangan(penerbangan);
//        listPilotPenerbanganModel.add(pilotPenerbangan);
//        penerbangan.setListPilotPenerbangan(listPilotPenerbanganModel);
//        pilotPenerbanganService.addPilotPenerbangan(pilotPenerbangan);

        penerbanganService.addPenerbangan(penerbangan);

        model.addAttribute("penerbangan", penerbangan);
        return "add-penerbangan";
    }

    @RequestMapping(value="penerbangan/ubah/{idPenerbangan}", method= RequestMethod.GET)
    public String changePilotFormPage(@PathVariable Long idPenerbangan, Model model) {
        Optional<PenerbanganModel> penerbangan = penerbanganService.getPenerbanganByID(idPenerbangan);
        model.addAttribute("penerbangan", penerbangan);
        return "form-update-penerbangan";
    }

    @PostMapping("/penerbangan/ubah")
    public String ubahPenerbanganFormSubmit(
            @ModelAttribute PenerbanganModel penerbangan,
            Model model
    ){
        PenerbanganModel penerbanganUpdated = penerbanganService.updatePenerbangan(penerbangan);
        model.addAttribute("penerbangan", penerbanganUpdated);
        return "update-penerbangan";
    }

    @PostMapping("/penerbangan/{idPenerbangan}/pilot/tambah")
    public String addPilotPenerbangan(
            @PathVariable Long idPenerbangan,
            @ModelAttribute PenerbanganModel penerbangan, PilotModel pilot,
            Model model) {
//        PilotModel pilotIni = new PilotModel();
//        PenerbanganModel penerbanganIni = new PenerbanganModel();
//        PilotPenerbanganModel pilotPenerbanganIni = new PilotPenerbanganModel();
//        ArrayList<PilotPenerbanganModel> listPilotPenerbanganIni = new ArrayList<>();
        PilotPenerbanganModel pilotPenerbangan = new PilotPenerbanganModel();
        List<PilotPenerbanganModel> listPilotPenerbanganModel = new ArrayList<>();

        pilotPenerbangan.setPilot(pilot);
        pilotPenerbangan.setPenerbangan(penerbangan);
        listPilotPenerbanganModel.add(pilotPenerbangan);
        pilot.setListPilotPenerbangan(listPilotPenerbanganModel);
        penerbangan.setListPilotPenerbangan(listPilotPenerbanganModel);
        pilotPenerbanganService.addPilotPenerbangan(pilotPenerbangan);

        List<PilotModel> listPilot = pilotService.getPilotList();


        Optional<PenerbanganModel> targetPenerbangan = penerbanganService.getPenerbanganByID(idPenerbangan);
        model.addAttribute("penerbangan", penerbangan);
        model.addAttribute("listPilot", listPilot);
        model.addAttribute("listPilotPenerbangan", listPilotPenerbanganModel);
        return "add-pilotpenerbangan";
    }
    @PostMapping("/penerbangan/{idPenerbangan}/pilot/tambah/{idPilot}")
    public String TambahPilotPenerbanganSubmit(
            @ModelAttribute PenerbanganModel penerbangan,PilotModel pilot,
            Model model
    ){
        PenerbanganModel penerbanganUpdated = penerbanganService.updatePenerbangan(penerbangan);
        model.addAttribute("penerbangan", penerbanganUpdated);
        return "update-penerbangan";
    }



}
