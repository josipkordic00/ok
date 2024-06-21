package ba.sum.fpmoz.automobilipuj.services;

import ba.sum.fpmoz.automobilipuj.models.Korisnik;
import ba.sum.fpmoz.automobilipuj.repositories.KorisnikRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class KorisnikService {

    private final KorisnikRepository korisnikRepository;


        @Autowired
        private PasswordEncoder passwordEncoder;

        public Korisnik registerKorisnik(Korisnik korisnik) {
            korisnik.setLozinka(passwordEncoder.encode(korisnik.getLozinka()));  // Encode password
            return korisnikRepository.save(korisnik);
        }

    @Autowired
    public KorisnikService(KorisnikRepository korisnikRepository) {
        this.korisnikRepository = korisnikRepository;
    }

    public Korisnik saveKorisnik(Korisnik korisnik) {
        return korisnikRepository.save(korisnik);
    }

    public Optional<Korisnik> getKorisnikByEmail(String email) {
        return korisnikRepository.findByEmail(email);
    }

    public void deleteKorisnikById(Integer id) {
        korisnikRepository.deleteById(id);
    }

    public Optional<Korisnik> getKorisnikById(Integer id) {
        return korisnikRepository.findById(id);
    }

    public boolean findByEmail(String email) {
        Optional<Korisnik> user = korisnikRepository.findByEmail(email);
        return user.isPresent();
    }



    // Additional methods to handle other business logic and repository calls
}


