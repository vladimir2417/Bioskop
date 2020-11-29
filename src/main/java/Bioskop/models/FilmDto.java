package Bioskop.models;

import org.springframework.web.multipart.MultipartFile;

public class FilmDto {

    private Long filmID;

    private String naziv;

    private Integer godina;

    private String opis;

    private String trajanje;

    private String imdb;

    private String youtube;

    private String slika;

    private MultipartFile slikaMulti;

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

    public MultipartFile getSlikaMulti() {
        return slikaMulti;
    }

    public void setSlikaMulti(MultipartFile slikaMulti) {
        this.slikaMulti = slikaMulti;
    }
}
