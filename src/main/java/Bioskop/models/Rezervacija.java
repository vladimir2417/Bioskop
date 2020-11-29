package Bioskop.models;


import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name="rezervacija")
public class Rezervacija {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO )
    private Long rezervacijaID;

    @Column(name="sediste", length = 50)
    private Long sediste;

    @Column(name="cena", length = 50)
    private Integer cena;

    @ManyToOne
    @JoinColumn(name="projekcijaID")
    private Projekcija projekcija;

    @ManyToOne
    @JoinColumn(name="korisnikID")
    private Korisnik korisnik;

    @OneToMany(mappedBy = "rezervacija",
            cascade = CascadeType.ALL,
            orphanRemoval = true)
    private Set<Sediste_Rezervacija> sediste_rezervacije;

    @ManyToOne
    @JoinColumn(name="paketID")
    private PaketiHrane paketiHrane;

    public Rezervacija(){}

    public Rezervacija(Long rezervacijaID, Long sediste, Integer cena, Projekcija projekcija, Korisnik korisnik, Set<Sediste_Rezervacija> sediste_rezervacije, PaketiHrane paketiHrane) {
        this.rezervacijaID = rezervacijaID;
        this.sediste = sediste;
        this.cena = cena;
        this.projekcija = projekcija;
        this.korisnik = korisnik;
        this.sediste_rezervacije = sediste_rezervacije;
        this.paketiHrane = paketiHrane;
    }

    public Long getRezervacijaID() {
        return rezervacijaID;
    }

    public void setRezervacijaID(Long rezervacijaID) {
        this.rezervacijaID = rezervacijaID;
    }

    public Long getSediste() {
        return sediste;
    }

    public void setSediste(Long sediste) {
        this.sediste = sediste;
    }

    public Integer getCena() {
        return cena;
    }

    public void setCena(Integer cena) {
        this.cena = cena;
    }

    public Projekcija getProjekcija() {
        return projekcija;
    }

    public void setProjekcija(Projekcija projekcija) {
        this.projekcija = projekcija;
    }

    public Korisnik getKorisnik() {
        return korisnik;
    }

    public void setKorisnik(Korisnik korisnik) {
        this.korisnik = korisnik;
    }

    public Set<Sediste_Rezervacija> getSediste_rezervacije() {
        return sediste_rezervacije;
    }

    public void setSediste_rezervacije(Set<Sediste_Rezervacija> sediste_rezervacije) {
        this.sediste_rezervacije = sediste_rezervacije;
    }

    public PaketiHrane getPaketiHrane() {
        return paketiHrane;
    }

    public void setPaketiHrane(PaketiHrane paketiHrane) {
        this.paketiHrane = paketiHrane;
    }
}
