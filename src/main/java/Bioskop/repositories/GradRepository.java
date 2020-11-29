package Bioskop.repositories;

import Bioskop.models.Grad;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GradRepository extends JpaRepository<Grad, Long> {
    Grad findByNaziv(String naziv);
}
