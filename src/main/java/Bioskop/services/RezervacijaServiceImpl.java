package Bioskop.services;


import Bioskop.models.Rezervacija;
import Bioskop.repositories.RezervacijaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class RezervacijaServiceImpl implements RezervacijaService {

    @Autowired
    private RezervacijaRepository rezervacijaRepository;

    @Override
    public Object listAll() {
        List<Rezervacija> rezervacije = new ArrayList<>();
        rezervacijaRepository.findAll().forEach(rezervacije::add);
        return rezervacije;
    }

}
