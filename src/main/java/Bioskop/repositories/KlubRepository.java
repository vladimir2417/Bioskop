package Bioskop.repositories;

import Bioskop.models.Klub;
import Bioskop.models.Korisnik;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface KlubRepository extends JpaRepository<Klub, Long> {
    List<Klub> findAllByKorisnici(Korisnik korisnici);
}
