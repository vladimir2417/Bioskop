package Bioskop.models;


import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name="film")
public class Film {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO )
    private Long filmID;

    @Column(name="naziv", length = 50)
    private String naziv;

    @Column(name="godina", length = 50)
    private Integer godina;

    @Column(name="opis", length = 255)
    private String opis;

    @Column(name="trajanje")
    private String trajanje;

    @Column(name="imdb")
    private String imdb;

    @Column(name="youtube")
    private String youtube;

    @Column(name="slika", length = 255)
    private String slika;

    @OneToMany(mappedBy="film", cascade = CascadeType.ALL)
    private Set<Projekcija> projekcije;

    public Film(){}

    public Film(Long filmID, String naziv, Integer godina, String opis, String trajanje, String imdb, String youtube, String slika, Set<Projekcija> projekcije) {
        this.filmID = filmID;
        this.naziv = naziv;
        this.godina = godina;
        this.opis = opis;
        this.trajanje = trajanje;
        this.imdb = imdb;
        this.youtube = youtube;
        this.slika = slika;
        this.projekcije = projekcije;
    }

    public Long getFilmID() {
        return filmID;
    }

    public void setFilmID(Long filmID) {
        this.filmID = filmID;
    }

    public String getNaziv() {
        return naziv;
    }

    public void setNaziv(String naziv) {
        this.naziv = naziv;
    }

    public Integer getGodina() {
        return godina;
    }

    public void setGodina(Integer godina) {
        this.godina = godina;
    }

    public String getOpis() {
        return opis;
    }

    public void setOpis(String opis) {
        this.opis = opis;
    }

    public String getTrajanje() {
        return trajanje;
    }

    public void setTrajanje(String trajanje) {
        this.trajanje = trajanje;
    }

    public String getImdb() {
        return imdb;
    }

    public void setImdb(String imdb) {
        this.imdb = imdb;
    }

    public String getYoutube() {
        return youtube;
    }

    public void setYoutube(String youtube) {
        this.youtube = youtube;
    }

    public String getSlika() {
        return slika;
    }

    public void setSlika(String slika) {
        this.slika = slika;
    }

    public Set<Projekcija> getProjekcije() {
        return projekcije;
    }

    public void setProjekcije(Set<Projekcija> projekcije) {
        this.projekcije = projekcije;
    }
}
