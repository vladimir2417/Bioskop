package Bioskop.repositories;

import Bioskop.models.Bioskop;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BioskopRepository extends JpaRepository<Bioskop, Long> {
    Bioskop findBioskopByNaziv(String naziv);
}
