package Bioskop.models;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name="paketi_hrane")
public class PaketiHrane {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO )
    private Long paketID;

    @Column(name="naziv", length = 50)
    private String naziv;

    @Column(name="hrana", length = 50)
    private String hrana;

    @Column(name="pice", length = 50)
    private String pice;

    @Column(name="cena", length = 20)
    private Integer cena;

    @OneToMany(mappedBy = "paketiHrane",
            cascade = CascadeType.ALL)
    private Set<Rezervacija> rezervacija;

    public PaketiHrane(){}

    public PaketiHrane(Long paketID, String naziv, String hrana, String pice, Integer cena, Set<Rezervacija> rezervacija) {
        this.paketID = paketID;
        this.naziv = naziv;
        this.hrana = hrana;
        this.pice = pice;
        this.cena = cena;
        this.rezervacija = rezervacija;
    }

    public Long getPaketID() {
        return paketID;
    }

    public void setPaketID(Long paketID) {
        this.paketID = paketID;
    }

    public String getNaziv() {
        return naziv;
    }

    public void setNaziv(String naziv) {
        this.naziv = naziv;
    }

    public String getHrana() {
        return hrana;
    }

    public void setHrana(String hrana) {
        this.hrana = hrana;
    }

    public String getPice() {
        return pice;
    }

    public void setPice(String pice) {
        this.pice = pice;
    }

    public Integer getCena() {
        return cena;
    }

    public void setCena(Integer cena) {
        this.cena = cena;
    }

    public Set<Rezervacija> getRezervacija() {
        return rezervacija;
    }

    public void setRezervacija(Set<Rezervacija> rezervacija) {
        this.rezervacija = rezervacija;
    }
}
