package ba.sum.fpmoz.automobilipuj.models;

import jakarta.persistence.*;

@Entity
@Table(name = "automobil")
public class automobil {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int automobilId;

    @Column(nullable = false)
    private String marka;

    @Column(nullable = false)
    private String model;

    @Column(nullable = false)
    private String godina;

    @Column(nullable = false)
    private String boja;

    @Column(nullable = false)
    private int snaga;



    // Getters and setters


    public int getAutomobilId() {
        return automobilId;
    }

    public void setAutomobilId(int automobilId) {
        this.automobilId = automobilId;
    }

    public String getMarka() {
        return marka;
    }

    public void setMarka(String marka) {
        this.marka = marka;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public String getGodina() {
        return godina;
    }

    public void setGodina(String godina) {
        this.godina = godina;
    }

    public String getBoja() {
        return boja;
    }

    public void setBoja(String boja) {
        this.boja = boja;
    }

    public int getSnaga() {
        return snaga;
    }

    public void setSnaga(int snaga) {
        this.snaga = snaga;
    }

}