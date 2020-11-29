package Bioskop.models;


import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name="bioskop")
public class Bioskop {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO )
    private Long bioskopID;

    @Column(name="naziv", length = 50)
    private String naziv;

    @Column(name="adresa", length = 50)
    private String adresa;

    @ManyToOne
    @JoinColumn(name="gradID")
    private Grad grad;

    @OneToMany(mappedBy="bioskop", cascade = CascadeType.ALL)
    private Set<Sala> sale;

    @OneToMany(mappedBy="bioskop", cascade = CascadeType.ALL)
    private Set<Projekcija> projekcije;

    public Bioskop(){}

    public Bioskop(Long bioskopID, String naziv, String adresa, Grad grad, Set<Sala> sale, Set<Projekcija> projekcije) {
        this.bioskopID = bioskopID;
        this.naziv = naziv;
        this.adresa = adresa;
        this.grad = grad;
        this.sale = sale;
        this.projekcije = projekcije;
    }

    public Long getBioskopID() {
        return bioskopID;
    }

    public void setBioskopID(Long bioskopID) {
        this.bioskopID = bioskopID;
    }

    public String getNaziv() {
        return naziv;
    }

    public void setNaziv(String naziv) {
        this.naziv = naziv;
    }

    public String getAdresa() {
        return adresa;
    }

    public void setAdresa(String adresa) {
        this.adresa = adresa;
    }

    public Grad getGrad() {
        return grad;
    }

    public void setGrad(Grad grad) {
        this.grad = grad;
    }

    public Set<Projekcija> getProjekcije() {
        return projekcije;
    }

    public void setProjekcije(Set<Projekcija> projekcije) {
        this.projekcije = projekcije;
    }

    public Set<Sala> getSale() {
        return sale;
    }

    public void setSale(Set<Sala> sale) {
        this.sale = sale;
    }

}


