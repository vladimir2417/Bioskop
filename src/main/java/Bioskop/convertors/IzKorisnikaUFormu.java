package Bioskop.convertors;

import Bioskop.models.Korisnik;
import Bioskop.models.KorisnikDto;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.type.TypeFactory;
import com.fasterxml.jackson.databind.util.Converter;
import org.springframework.stereotype.Component;

@Component
public class IzKorisnikaUFormu implements Converter<Korisnik, KorisnikDto> {
    @Override
    public KorisnikDto convert(Korisnik korisnik) {
        KorisnikDto korisnikDto = new KorisnikDto();
        korisnikDto.setKorisnikID(korisnik.getKorisnikID());
        korisnikDto.setIme(korisnik.getIme());
        korisnikDto.setPrezime(korisnik.getPrezime());
        korisnikDto.setEmail(korisnik.getEmail());
        korisnikDto.setUsername(korisnik.getUsername());
        korisnikDto.setPassword(korisnik.getPassword());
        korisnikDto.setBrPoena(korisnik.getBrPoena());
        korisnikDto.setUloge(korisnik.getUloge());
        return korisnikDto;
    }

    @Override
    public JavaType getInputType(TypeFactory typeFactory) {
        return null;
    }

    @Override
    public JavaType getOutputType(TypeFactory typeFactory) {
        return null;
    }
}
