package ba.sum.fpmoz.automobilipuj.controllers;
import ba.sum.fpmoz.automobilipuj.models.automobil;
import ba.sum.fpmoz.automobilipuj.services.AutomobilService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/automobili")
public class AutomobilController {

    @Autowired
    private AutomobilService automobilService;

    @GetMapping("/dodaj")
    public String showAddautomobilForm(Model model) {
        model.addAttribute("automobil", new automobil());
        return "dodajAutomobil";
    }

    @PostMapping
    public String addautomobil(@Valid @ModelAttribute automobil automobil) {
        automobilService.saveautomobil(automobil);
        return "redirect:/home";
    }

    @GetMapping("/{id}")
    public ResponseEntity<automobil> getautomobilById(@PathVariable Integer id) {
        return automobilService.getautomobilById(id)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @GetMapping("/user/{userId}")
    public ResponseEntity<List<automobil>> getautomobiliByUserId(@PathVariable Integer userId) {
        List<automobil> automobili = automobilService.findAllautomobili();
        if (automobili.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(automobili);
    }

    @GetMapping
    public String getAllautomobili(Model model) {
        List<automobil> automobili = automobilService.findAllautomobili();
        model.addAttribute("automobili", automobili);
        return "home";
    }

    @GetMapping("/uredi/{id}")
    public String showEditautomobilForm(@PathVariable Integer id, Model model) {
        automobil automobil = automobilService.getautomobilById(id)
                .orElseThrow(() -> new RuntimeException("automobil not found with id " + id));
        model.addAttribute("automobil", automobil);
        return "urediAutomobil";
    }

    @PostMapping("/uredi/{id}")
    public String updateautomobil(@PathVariable Integer id, @Valid @ModelAttribute automobil updatedautomobil) {
        automobilService.updateautomobil(id, updatedautomobil);
        return "redirect:/home";
    }

    @GetMapping("/izbrisi/{id}")
    public String deleteautomobil(@PathVariable Integer id) {
        automobilService.deleteautomobil(id);
        return "redirect:/home";
    }
}



