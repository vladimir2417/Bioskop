package Bioskop.repositories;

import Bioskop.models.Projekcija;
import Bioskop.models.Sediste_Rezervacija;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface Sediste_RezervacijaRepository extends JpaRepository<Sediste_Rezervacija, Long> {
    List<Sediste_Rezervacija> findAllByProjekcija(Projekcija projekcija);
}
