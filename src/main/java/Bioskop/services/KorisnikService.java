package Bioskop.services;


import Bioskop.models.Korisnik;
import Bioskop.models.KorisnikDto;

import java.util.List;
import java.util.Optional;

public interface KorisnikService {
        List<Korisnik> getKorisnik();
        Korisnik getById(Long korisnikID);
        void deleteById(Long korisnikID);
        Korisnik saveOrUpdate(Korisnik korisnik);
        Korisnik saveReg(KorisnikDto korisnikDto);
        Optional<Korisnik> findById(Long korisnikID);
        List<Korisnik> listAll();
}
