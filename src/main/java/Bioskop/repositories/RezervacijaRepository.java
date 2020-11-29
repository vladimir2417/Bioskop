package Bioskop.repositories;

import Bioskop.models.Projekcija;
import Bioskop.models.Rezervacija;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;

@Repository
public interface RezervacijaRepository extends JpaRepository<Rezervacija, Long> {
    @Transactional
    void deleteAllByProjekcija(Projekcija projekcija);

}
