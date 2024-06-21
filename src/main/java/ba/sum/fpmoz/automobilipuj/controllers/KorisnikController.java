package ba.sum.fpmoz.automobilipuj.controllers;

import ba.sum.fpmoz.automobilipuj.models.Korisnik;
import ba.sum.fpmoz.automobilipuj.services.KorisnikService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/api/korisnici")
public class KorisnikController {

    @Autowired
    private KorisnikService korisnikService;

    private AuthenticationManager authenticationManager;

    public class SimpleController {

        @GetMapping("/test")
        public String test() {
            return "Server is up and running!";
        }
    }

    @PostMapping(value = "/register", consumes = "application/json")
    public ResponseEntity<?> registerUser(@RequestBody Korisnik korisnik) {
        System.out.println("Received korisnik: " + korisnik);
        System.out.println("Email: " + korisnik.getEmail());
        System.out.println("Username (korisnickoIme): " + korisnik.getKorisnickoIme());
        System.out.println("Password (lozinka): " + korisnik.getLozinka());
        System.out.println("Ime: " + korisnik.getIme());
        System.out.println("Prezime: " + korisnik.getPrezime());

        if (korisnik.getKorisnickoIme() == null) {
            return ResponseEntity.badRequest().body("Username cannot be null");
        }
        Korisnik newKorisnik = korisnikService.registerKorisnik(korisnik);
        return ResponseEntity.ok(newKorisnik);
    }

    @PostMapping(value = "/register", consumes = "application/x-www-form-urlencoded")
    public ResponseEntity<?> registerUserForm(@ModelAttribute Korisnik korisnik) {
        System.out.println("Received korisnik: " + korisnik);
        System.out.println("Email: " + korisnik.getEmail());
        System.out.println("Username (korisnickoIme): " + korisnik.getKorisnickoIme());
        System.out.println("Password (lozinka): " + korisnik.getLozinka());
        System.out.println("Ime: " + korisnik.getIme());
        System.out.println("Prezime: " + korisnik.getPrezime());

        if (korisnik.getKorisnickoIme() == null) {
            return ResponseEntity.badRequest().body("Username cannot be null");
        }
        Korisnik newKorisnik = korisnikService.registerKorisnik(korisnik);
        return ResponseEntity.ok(newKorisnik);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Optional<Korisnik>> getUserById(@PathVariable Integer id) {
        Optional<Korisnik> korisnik = korisnikService.getKorisnikById(id);
        return ResponseEntity.ok(korisnik);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable Integer id) {
        korisnikService.deleteKorisnikById(id);
        return ResponseEntity.ok().build();
    }
    @PostMapping("/login")
    public ResponseEntity<?> loginUser(@RequestParam String email, @RequestParam String password) {
        try {
            Authentication authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(email, password)
            );
            SecurityContextHolder.getContext().setAuthentication(authentication);
            Korisnik korisnik = (Korisnik) authentication.getPrincipal();
            return ResponseEntity.ok("User logged in successfully!");
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Invalid credentials");
        }
    }


    @GetMapping("/profile")
    public String userProfile(Model model) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String email = auth.getName();  // Get logged in username
        Optional<Korisnik> korisnik = korisnikService.getKorisnikByEmail(email);
        korisnik.ifPresent(value -> model.addAttribute("korisnik", value));
        return "profile";  // Points to src/main/resources/templates/profile.html
    }
}
