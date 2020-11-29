package Bioskop.models;

import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.validator.constraints.NotBlank;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@DynamicUpdate
@Table(name="korisnici")
public class Korisnik {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO )
    private Long korisnikID;

    @NotBlank(message = "Unesite ime")
    @Column(name="ime", length = 50)
    private String ime;

    @NotBlank(message = "Unesite prezime")
    @Column(name="prezime", length = 50)
    private String prezime;

    @NotBlank(message = "Unesite email")
    @Column(name="email", length = 50)
    private String email;

    @NotBlank(message = "Unesite username")
    @Column(name="username", length = 50)
    private String username;

    @Column(name="password")
    private String password;

    @Column(name="brpoena")
    private Integer brPoena;

    @ManyToOne
    @JoinColumn(name="ulogeID")
    private TipKorisnika uloge;


    @OneToMany(
            mappedBy = "korisnik",
            cascade = CascadeType.ALL
    )
    private List<Rezervacija> rezervacije = new ArrayList<>();


    @ManyToMany(mappedBy = "korisnici")
    private Set<Klub> klubovi = new HashSet<>();

    public Korisnik() {
    }

    public Korisnik(Long korisnikID, String ime, String prezime, String email, String username, String password, Integer brPoena, TipKorisnika uloge, List<Rezervacija> rezervacije, Set<Klub> klubovi) {
        this.korisnikID = korisnikID;
        this.ime = ime;
        this.prezime = prezime;
        this.email = email;
        this.username = username;
        this.password = password;
        this.brPoena = brPoena;
        this.uloge = uloge;
        this.rezervacije = rezervacije;
        this.klubovi = klubovi;
    }

    public Long getKorisnikID() {
        return korisnikID;
    }

    public void setKorisnikID(Long korisnikID) {
        this.korisnikID = korisnikID;
    }

    public String getIme() {
        return ime;
    }

    public void setIme(String ime) {
        this.ime = ime;
    }

    public String getPrezime() {
        return prezime;
    }

    public void setPrezime(String prezime) {
        this.prezime = prezime;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getNazivUloge(){return uloge.getNazivUloge();}

    public TipKorisnika getUloge() {
        return uloge;
    }

    public void setUloge(TipKorisnika uloge) {
        this.uloge = uloge;
    }

    public List<Rezervacija> getRezervacije() {
        return rezervacije;
    }

    public void setRezervacije(List<Rezervacija> rezervacije) {
        this.rezervacije = rezervacije;
    }

    public Set<Klub> getKlubovi() {
        return klubovi;
    }

    public void setKlubovi(Set<Klub> klubovi) {
        this.klubovi = klubovi;
    }

    public Integer getBrPoena() {
        return brPoena;
    }

    public void setBrPoena(Integer brPoena) {
        this.brPoena = brPoena;
    }
}
