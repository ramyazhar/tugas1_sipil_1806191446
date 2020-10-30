package apap.tugas.sipil.controller;


import apap.tugas.sipil.model.*;
import apap.tugas.sipil.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Date;
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

    @Autowired
    private MaskapaiService maskapaiService;

    @Autowired
    private AkademiService akademiService;

    @GetMapping("/penerbangan/tambah")
    public String addPenerbanganFormPage(Model model){
        model.addAttribute("penerbangan", new PenerbanganModel());
        return "form-add-penerbangan";
    }

    @GetMapping("/penerbangan/detil/{id}")
    public String findPenerbanganByidPenerbangan(
            @PathVariable Long id,
            Model model){

        PilotPenerbanganModel pilotPenerbangan = new PilotPenerbanganModel();
        PenerbanganModel penerbanganModelini = penerbanganService.getPenerbanganByID(id).get();
        pilotPenerbangan.setPenerbangan(penerbanganModelini);
        List<PilotModel> listPilot = pilotService.getPilotList();
        PenerbanganModel penerbangan = penerbanganService.getPenerbanganByID(id).get();
        List<PilotPenerbanganModel> listPilotPenerbangan = penerbangan.getListPilotPenerbangan();
        model.addAttribute("penerbangan",penerbangan);
        model.addAttribute("pilotPenerbangan", pilotPenerbangan);
        model.addAttribute("listPilot",listPilot);
        model.addAttribute("listPilotPenerbangan", listPilotPenerbangan);
        return "view-by-idpenerbangan";
    }
    @PostMapping("/penerbangan/{idPenerbangan}/pilot/tambah")
    public String addPilotPenerbangan(
            @PathVariable Long idPenerbangan,
            @ModelAttribute PilotPenerbanganModel pilotpenerbangan,
            Model model) {
        PenerbanganModel penerbangan = penerbanganService.getPenerbanganByID(idPenerbangan).get();
        pilotpenerbangan.setPenerbangan(penerbangan);
        PilotModel pilotini = pilotpenerbangan.getPilot();
        String namaPilot = pilotini.getNamaPilot();
        String kodePenerbangan = penerbangan.getKode();
        pilotPenerbanganService.addPilotPenerbangan(pilotpenerbangan);

        model.addAttribute("namaPilot",namaPilot);
        model.addAttribute("kodePenerbangan",kodePenerbangan);
        return "add-pilotpenerbangan";


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
    @PostMapping(path = "/penerbangan/hapus")
    public String removePenerbangan(
           @RequestParam(required = false) Long penerbanganid,
            Model model){
        System.out.println(penerbanganid);
        List<PilotPenerbanganModel> listPilotPenerbangan = penerbanganService.getPenerbanganByID(penerbanganid).get().getListPilotPenerbangan();
        PenerbanganModel penerbangan = penerbanganService.getPenerbanganByID(penerbanganid).get();
        if(listPilotPenerbangan.size() != 0 ){

            model.addAttribute("penerbangan",penerbangan);
            return "remove-penerbangan-gagal";

        }else {
            model.addAttribute("penerbangan",penerbangan);
            penerbanganService.removePenerbangan(penerbangan);
            return "remove-penerbangan-berhasil";
        }

    }





}
