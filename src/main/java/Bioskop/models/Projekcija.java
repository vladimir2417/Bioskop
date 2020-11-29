package Bioskop.models;

import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Set;

@Entity
@Table(name="projekcija")

public class Projekcija {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO )
    private Long projekcijaID;

    @Temporal(TemporalType.DATE)
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date datum;

    @Temporal(TemporalType.TIME)
    @DateTimeFormat(pattern = "HH:mm")
    private Date vreme;

    @Column(name="grad", length = 20)
    private String grad;

    @Column(name="cena", length = 20)
    private Integer cena;

    @ManyToOne
    @JoinColumn(name="salaID")
    private Sala sala;

    @ManyToOne
    @JoinColumn(name="bioskopID")
    private Bioskop bioskop;

    @ManyToOne
    @JoinColumn(name="filmID")
    private Film film;

    @OneToMany(
            mappedBy = "projekcija",
            cascade = CascadeType.ALL,
            orphanRemoval = true
    )
    private List<Rezervacija> rezervacije = new ArrayList<>();

    @OneToMany(mappedBy = "projekcija", cascade = CascadeType.ALL)
    private Set<Sediste_Rezervacija> sediste_rezervacije;

    public Projekcija(){}

    public Projekcija(Long projekcijaID, Date datum, Date vreme, String grad, Integer cena, Sala sala, Bioskop bioskop, Film film, List<Rezervacija> rezervacije) {
        this.projekcijaID = projekcijaID;
        this.datum = datum;
        this.vreme = vreme;
        this.grad = grad;
        this.cena = cena;
        this.sala = sala;
        this.bioskop = bioskop;
        this.film = film;
        this.rezervacije = rezervacije;
    }

    public Long getProjekcijaID() {
        return projekcijaID;
    }

    public void setProjekcijaID(Long projekcijaID) {
        this.projekcijaID = projekcijaID;
    }

    public Date getDatum() {
        return datum;
    }

    public void setDatum(Date datum) {
        this.datum = datum;
    }

    public Date getVreme() {
        return vreme;
    }

    public void setVreme(Date vreme) {
        this.vreme = vreme;
    }

    public Integer getCena() {
        return cena;
    }

    public void setCena(Integer cena) {
        this.cena = cena;
    }

    public Sala getSala() {
        return sala;
    }

    public String getSalaNaziv() {
        return getSala().getNaziv();
    }

    public void setSala(Sala sala) {
        this.sala = sala;
    }

    public Bioskop getBioskop() {
        return bioskop;
    }

    public String getBioskopNaziv() {
        return getBioskop().getNaziv();
    }

    public String getGrad() {
        return getBioskop().getGrad().getNaziv();
    }

    public void setGrad(String grad) {
        this.grad = grad;
    }

    public String getFilmNaziv() {
        return getFilm().getNaziv();
    }

    public void setBioskop(Bioskop bioskop) {
        this.bioskop = bioskop;
    }

    public Film getFilm() {
        return film;
    }

    public void setFilm(Film film) {
        this.film = film;
    }

    public List<Rezervacija> getRezervacije() {
        return rezervacije;
    }

    public void setRezervacije(List<Rezervacija> rezervacije) {
        this.rezervacije = rezervacije;
    }
}
