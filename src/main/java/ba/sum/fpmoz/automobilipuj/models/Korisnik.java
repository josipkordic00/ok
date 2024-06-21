package ba.sum.fpmoz.automobilipuj.models;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Setter
@Getter
@Entity
@Table(name = "korisnik")
public class Korisnik {
    // Getters and Setters
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "korisnik_id")
    private Integer id;

    @Column(nullable = false, unique = true)
    private String email;

    @JsonProperty("korisnickoIme")  // Make sure this aligns with your JSON input
    @Column(nullable = false)
    private String korisnickoIme;

    // other fields and methods

    public String getUsername() {
        return korisnickoIme;
    }

    public void setUsername(String korisnickoIme) {
        this.korisnickoIme = korisnickoIme;
    }

    @JsonProperty("lozinka")
    @Column(nullable = false)
    private String lozinka;

    @JsonProperty("ime")
    @Column(nullable = false)
    private String ime;

    @JsonProperty("prezime")
    @Column(nullable = false)
    private String prezime;


    public String getPassword() {
        return lozinka;
    }

}
