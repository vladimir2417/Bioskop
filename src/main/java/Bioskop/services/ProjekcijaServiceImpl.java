package Bioskop.services;


import Bioskop.models.Projekcija;
import Bioskop.repositories.ProjekcijaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ProjekcijaServiceImpl implements ProjekcijaService {

    @Autowired
    private ProjekcijaRepository projekcijaRepository;

        @Override
        public Object listAll() {
            List<Projekcija> projekcije = new ArrayList<>();
            projekcijaRepository.findAll().forEach(projekcije::add);
            return projekcije;
    }
}
