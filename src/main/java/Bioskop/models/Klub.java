package Bioskop.models;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name="klub")
public class Klub {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO )
    private Long klubID;

    @Column(name="naziv", length = 50)
    private String naziv;

    @Column(name="opis", length = 50)
    private String opis;

    @ManyToMany(cascade = { CascadeType.ALL })
    @JoinTable(
            name = "klub_korisnik",
            joinColumns = { @JoinColumn(name = "klubID") },
            inverseJoinColumns = { @JoinColumn(name = "korisnikID") }
    )
    Set<Korisnik> korisnici = new HashSet<>();

    public Klub(){}

    public Klub(Long klubID, String naziv, String opis, Set<Korisnik> korisnici) {
        this.klubID = klubID;
        this.naziv = naziv;
        this.opis = opis;
        this.korisnici = korisnici;
    }

    public Long getKlubID() {
        return klubID;
    }

    public void setKlubID(Long klubID) {
        this.klubID = klubID;
    }

    public String getNaziv() {
        return naziv;
    }

    public void setNaziv(String naziv) {
        this.naziv = naziv;
    }

    public String getOpis() {
        return opis;
    }

    public void setOpis(String opis) {
        this.opis = opis;
    }

    public Set<Korisnik> getKorisnici() {
        return korisnici;
    }

    public void setKorisnici(Set<Korisnik> korisnici) {
        this.korisnici = korisnici;
    }
}
