package Bioskop.repositories;

import Bioskop.models.Bioskop;
import Bioskop.models.Film;
import Bioskop.models.Projekcija;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public interface ProjekcijaRepository extends JpaRepository<Projekcija, Long> {
    List<Projekcija> findAllByDatumAndBioskopAndGrad(Date datum, Bioskop bioskop, String grad);
    List<Projekcija> findAllByFilm(Film film);
}
