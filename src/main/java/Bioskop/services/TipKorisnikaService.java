package Bioskop.services;


import Bioskop.models.TipKorisnika;

import java.util.Optional;

public interface TipKorisnikaService {
    Optional<TipKorisnika> findById(Integer ulogeID);
}
