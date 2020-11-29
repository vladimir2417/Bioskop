package Bioskop.models;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name="sala")
public class Sala {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO )
    private Long salaID;

    @Column(name="naziv", length = 50)
    private String naziv;

    @ManyToOne
    @JoinColumn(name="bioskopID")
    private Bioskop bioskop;

    @OneToMany(mappedBy="sala", cascade = CascadeType.ALL)
    private Set<Projekcija> projekcije;

    @OneToMany(mappedBy="sala", cascade = CascadeType.ALL)
    private Set<Sediste> sedista;

    public Sala(){}

    public Sala(Long salaID, String naziv, Bioskop bioskop, Set<Projekcija> projekcije, Set<Sediste> sedista) {
        this.salaID = salaID;
        this.naziv = naziv;
        this.bioskop = bioskop;
        this.projekcije = projekcije;
        this.sedista = sedista;
    }

    public Long getSalaID() {
        return salaID;
    }

    public void setSalaID(Long salaID) {
        this.salaID = salaID;
    }

    public String getNaziv() {
        return naziv;
    }

    public void setNaziv(String naziv) {
        this.naziv = naziv;
    }

    public Bioskop getBioskop() {
        return bioskop;
    }

    public void setBioskop(Bioskop bioskop) {
        this.bioskop = bioskop;
    }

    public Set<Projekcija> getProjekcije() {
        return projekcije;
    }

    public void setProjekcije(Set<Projekcija> projekcije) {
        this.projekcije = projekcije;
    }

    public Set<Sediste> getSedista() {
        return sedista;
    }

    public void setSedista(Set<Sediste> sedista) {
        this.sedista = sedista;
    }
}
