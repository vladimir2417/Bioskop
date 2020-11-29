package Bioskop.models;

import javax.persistence.*;

@Entity
@Table(name="sediste_rezervacija")
public class Sediste_Rezervacija {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long sediste_rezervacijaID;

    @ManyToOne
    @JoinColumn(name="sedisteID")
    private Sediste sediste;

    @ManyToOne
    @JoinColumn(name="rezervacijaID")
    private Rezervacija rezervacija;

    @ManyToOne
    @JoinColumn(name="projekcijaID")
    private Projekcija projekcija;

    public Sediste_Rezervacija(){}

    public Sediste_Rezervacija(Long sediste_rezervacijaID, Sediste sediste, Rezervacija rezervacija, Projekcija projekcija) {
        this.sediste_rezervacijaID = sediste_rezervacijaID;
        this.sediste = sediste;
        this.rezervacija = rezervacija;
        this.projekcija = projekcija;
    }

    public Long getSediste_rezervacijaID() {
        return sediste_rezervacijaID;
    }

    public void setSediste_rezervacijaID(Long sediste_rezervacijaID) {
        this.sediste_rezervacijaID = sediste_rezervacijaID;
    }

    public Sediste getSediste() {
        return sediste;
    }

    public void setSediste(Sediste sediste) {
        this.sediste = sediste;
    }

    public Rezervacija getRezervacija() {
        return rezervacija;
    }

    public void setRezervacija(Rezervacija rezervacija) {
        this.rezervacija = rezervacija;
    }

    public Projekcija getProjekcija() {
        return projekcija;
    }

    public void setProjekcija(Projekcija projekcija) {
        this.projekcija = projekcija;
    }
}
