package Bioskop.convertors;

import Bioskop.models.Zaposleni;
import Bioskop.models.ZaposleniDto;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.type.TypeFactory;
import com.fasterxml.jackson.databind.util.Converter;
import org.springframework.stereotype.Component;

@Component
public class IzZaposlenogUFormu implements Converter<Zaposleni, ZaposleniDto> {
    @Override
    public ZaposleniDto convert(Zaposleni zaposleni) {
        ZaposleniDto zaposleniDto = new ZaposleniDto();
        zaposleniDto.setZaposleniID(zaposleni.getZaposleniID());
        zaposleniDto.setIme(zaposleni.getIme());
        zaposleniDto.setPrezime(zaposleni.getPrezime());
        zaposleniDto.setEmail(zaposleni.getEmail());
        zaposleniDto.setUsername(zaposleni.getUsername());
        zaposleniDto.setPassword(zaposleni.getPassword());
        zaposleniDto.setUloge(zaposleni.getUloge());
        return zaposleniDto;
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
