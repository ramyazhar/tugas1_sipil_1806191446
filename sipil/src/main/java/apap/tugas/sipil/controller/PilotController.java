package apap.tugas.sipil.controller;

import apap.tugas.sipil.model.PilotModel;
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
        System.out.println("ini nipnya");
        System.out.println(pilot.getNip());
        model.addAttribute("pilot",pilot);
        return "view-by-nippilot";
    }

    @RequestMapping(value="pilot/ubah/{nipPilot}", method=RequestMethod.GET)
    public String changePilotFormPage(@PathVariable String nipPilot, Model model) {
        PilotModel pilot = pilotService.getPilotByNipPilot(nipPilot);
        model.addAttribute("pilot", pilot);
        if (pilot == null) {
            return "/";
        }
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



}
