package ba.sum.fpmoz.automobilipuj.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import ba.sum.fpmoz.automobilipuj.models.automobil;
import ba.sum.fpmoz.automobilipuj.services.AutomobilService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;

import java.util.List;

@Controller
public class ViewController {

    @Autowired
    private AutomobilService automobilService;

    @GetMapping("/login_page")
    public String login() {
        return "loginJava";  // Name of the HTML file under src/main/resources/templates
    }

    @GetMapping("/register")
    public String register() {
        return "register";  // Name of the HTML file under src/main/resources/templates
    }

    @GetMapping("/home")
    public String home(Model model) {
        List<automobil> automobili = automobilService.findAllautomobili();
        model.addAttribute("automobili", automobili);
        return "home";
    }

    @GetMapping("/dodaj")
    public String dodaj() {
        return "dodajautomobil";
    }

    @GetMapping("/uredi")
    public String uredi() {
        return "urediautomobil";
    }
}

