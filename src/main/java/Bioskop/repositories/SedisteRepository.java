package Bioskop.repositories;

import Bioskop.models.Sediste;
import Bioskop.models.Sala;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SedisteRepository extends JpaRepository<Sediste, Long> {
    List<Sediste> findAllBySala(Sala sala);
    /*List<Sediste> findAllBySediste_rezervacijeNot();*/
}
