package Bioskop.models;

import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;
import java.util.Set;

@Entity
@DynamicUpdate
@Table(name="sediste")
public class Sediste {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long sedisteID;

    @Column(name="br_sedista", length = 50)
    private String br_sedista;

    @Column(name="vrsta_sedista", length = 50)
    private String vrsta_sedista;

    @ManyToOne
    @JoinColumn(name="salaID")
    private Sala sala;

    @OneToMany(mappedBy = "sediste")
    private Set<Sediste_Rezervacija> sediste_rezervacije;

    public Sediste(){}

    public Sediste(Long sedisteID, String br_sedista, String vrsta_sedista, Sala sala) {
        this.sedisteID = sedisteID;
        this.br_sedista = br_sedista;
        this.vrsta_sedista = vrsta_sedista;
        this.sala = sala;
    }

    public Long getSedisteID() {
        return sedisteID;
    }

    public void setSedisteID(Long sedisteID) {
        this.sedisteID = sedisteID;
    }

    public String getBr_sedista() {
        return br_sedista;
    }

    public void setBr_sedista(String br_sedista) {
        this.br_sedista = br_sedista;
    }

    public String getVrsta_sedista() {
        return vrsta_sedista;
    }

    public void setVrsta_sedista(String vrsta_sedista) {
        this.vrsta_sedista = vrsta_sedista;
    }

    public Sala getSala() {
        return sala;
    }

    public void setSala(Sala sala) {
        this.sala = sala;
    }
}
