package Bioskop.services;


import Bioskop.convertors.IzFormeUZaposlenog;
import Bioskop.repositories.ZaposleniRepository;
import Bioskop.models.Zaposleni;
import Bioskop.models.ZaposleniDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ZaposleniServiceImpl implements ZaposleniService {

    @Autowired
    private IzFormeUZaposlenog izFormeuZaposlenog;

    @Autowired
    private ZaposleniRepository zaposleniRepository;

    @Override
    public List<Zaposleni> getZaposleni() {
        return this.zaposleniRepository.findAll();
    }

    @Override
    public Optional<Zaposleni> findById(Integer zaposleniID) {
        return this.zaposleniRepository.findById(zaposleniID);
    }

    @Override
    public void deleteById(Integer zaposleniID) {
        this.zaposleniRepository.deleteById(zaposleniID);

    }
    @Override
    public Zaposleni getById(Integer zaposleniID) {
        return zaposleniRepository.findById(zaposleniID).orElse(null);
    }

    @Override
    public Zaposleni saveOrUpdate(Zaposleni zaposleni) {
        zaposleniRepository.save(zaposleni);
        return zaposleni;
    }

    @Override
    public Zaposleni update(Zaposleni zaposleni) {
        return null;
    }

    @Override
    public Zaposleni saveUst(ZaposleniDto zaposleniDto) {
        Zaposleni novaZaposleni = saveOrUpdate(izFormeuZaposlenog.convert(zaposleniDto));
        return novaZaposleni;
    }

}
