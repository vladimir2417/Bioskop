package Bioskop.models;


import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name="grad")
public class Grad {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO )
    private Long gradID;

    @Column(name="naziv", length = 50)
    private String naziv;

    @OneToMany(mappedBy="grad", cascade = CascadeType.ALL)
    private Set<Bioskop> bioskopi;

    public Grad(){}

    public Grad(Long gradID, String naziv, Set<Bioskop> bioskopi) {
        this.gradID = gradID;
        this.naziv = naziv;
        this.bioskopi = bioskopi;
    }

    public Long getGradID() {
        return gradID;
    }

    public void setGradID(Long gradID) {
        this.gradID = gradID;
    }

    public String getNaziv() {
        return naziv;
    }

    public void setNaziv(String naziv) {
        this.naziv = naziv;
    }

    public Set<Bioskop> getBioskopi() {
        return bioskopi;
    }

    public void setBioskopi(Set<Bioskop> bioskopi) {
        this.bioskopi = bioskopi;
    }
}
