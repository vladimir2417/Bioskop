package Bioskop.services;


import Bioskop.convertors.izFormeUKorisnik;
import Bioskop.models.KorisnikDto;
import Bioskop.repositories.KorisnikRepository;
import Bioskop.models.Korisnik;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


@Service
public class KorisnikServiceImpl implements KorisnikService {

    @Autowired
    KorisnikRepository korisnikRepository;

    private Bioskop.convertors.izFormeUKorisnik izFormeUKorisnik;

    @Autowired
    public KorisnikServiceImpl(KorisnikRepository korisnikRepository, izFormeUKorisnik izFormeUKorisnik) {
        this.korisnikRepository = korisnikRepository;
        this.izFormeUKorisnik = izFormeUKorisnik;
    }

    @Override
    public List<Korisnik> getKorisnik(){
       return this.korisnikRepository.findAll();
    }

    public Optional<Korisnik> findById(Long korisnikID){
       return this.korisnikRepository.findById(korisnikID);
   }

    @Override
    public Korisnik getById(Long korisnikID) {
        return korisnikRepository.findById(korisnikID).orElse(null);
    }

    @Override
    public void deleteById(Long korisnikID) {
        korisnikRepository.deleteById(korisnikID);
    }

    @Override
    public List<Korisnik> listAll() {
        List<Korisnik> korisnici = new ArrayList<>();
        korisnikRepository.findAll().forEach(korisnici::add);
        return korisnici;
    }

    @Override
    public Korisnik saveOrUpdate(Korisnik korisnik) {
        korisnikRepository.save(korisnik);
        return korisnik;
    }

    @Override
    public Korisnik saveReg(KorisnikDto korisnikDto) {
        Korisnik noviKorisnik = saveOrUpdate(izFormeUKorisnik.convert(korisnikDto));
        return noviKorisnik;
    }

}

