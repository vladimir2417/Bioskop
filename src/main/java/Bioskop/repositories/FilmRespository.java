package Bioskop.repositories;


import Bioskop.models.Film;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FilmRespository extends JpaRepository<Film, Long> {
    List<Film> findAllByGodina(Integer godina);
    Film findFilmByNaziv(String naziv);
}
