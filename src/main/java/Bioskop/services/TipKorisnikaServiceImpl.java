package Bioskop.services;


import Bioskop.repositories.TipKorisnikaRepository;
import Bioskop.models.TipKorisnika;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;


@Service
public class TipKorisnikaServiceImpl implements TipKorisnikaService {

    @Autowired
    private TipKorisnikaRepository tipKorisnikaRepository;

    @Override
    public Optional<TipKorisnika> findById(Integer ulogeID) {
        return this.tipKorisnikaRepository.findById(ulogeID);
    }
}
