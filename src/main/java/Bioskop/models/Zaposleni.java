package Bioskop.models;

import javax.persistence.*;

@Entity
@Table(name = "zaposleni")
public class Zaposleni {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO )
    private Integer zaposleniID;


    @Column(name="ime", length = 50)
    private String ime;

    @Column(name="prezime", length = 50)
    private String prezime;

    @Column(name="email", length = 50)
    private String email;

    @Column(name="username", length = 50)
    private String username;

    @Column(name="password")
    private String password;

    @ManyToOne
    @JoinColumn(name="ulogeID")
    private TipKorisnika uloge;


    public Zaposleni() {
    }

    public Zaposleni(Integer zaposleniID, String ime, String prezime, String email, String username, String password, TipKorisnika uloge) {
        this.zaposleniID = zaposleniID;
        this.ime = ime;
        this.prezime = prezime;
        this.email = email;
        this.username = username;
        this.password = password;
        this.uloge = uloge;
    }

    public Integer getZaposleniID() {
        return zaposleniID;
    }

    public void setZaposleniID(Integer zaposleniID) {
        this.zaposleniID = zaposleniID;
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
}
