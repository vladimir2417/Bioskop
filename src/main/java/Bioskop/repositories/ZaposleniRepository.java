package Bioskop.repositories;

import Bioskop.models.TipKorisnika;
import Bioskop.models.Zaposleni;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ZaposleniRepository extends JpaRepository<Zaposleni, Integer> {
    Optional<Zaposleni> findZaposleniByUsername(String username);
    List<Zaposleni> findAllByUloge(TipKorisnika uloge);
}
