package Bioskop.services;

import Bioskop.models.Klub;
import Bioskop.repositories.KlubRepository;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;

public class KlubServiceImpl implements KlubService {

    @Autowired
    private KlubRepository klubRepository;
    @Override
    public Object listAll() {
        List<Klub> klubovi = new ArrayList<>();
        klubRepository.findAll().forEach(klubovi::add);
        return klubovi;
    }
}
