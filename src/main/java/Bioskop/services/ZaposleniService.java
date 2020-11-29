package Bioskop.services;


import Bioskop.models.Zaposleni;
import Bioskop.models.ZaposleniDto;

import java.util.List;
import java.util.Optional;

public interface ZaposleniService {
    List<Zaposleni> getZaposleni();
    Optional<Zaposleni> findById(Integer zaposleniID);
    Zaposleni saveOrUpdate(Zaposleni zaposleni);
    Zaposleni update(Zaposleni zaposleni);
    void deleteById(Integer zaposleniID);
    Zaposleni getById(Integer zaposleniID);
    Zaposleni saveUst(ZaposleniDto zaposleniDto);
}
