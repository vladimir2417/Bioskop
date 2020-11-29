package Bioskop.convertors;

import Bioskop.models.Zaposleni;
import Bioskop.models.ZaposleniDto;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.type.TypeFactory;
import com.fasterxml.jackson.databind.util.Converter;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

@Component
public class IzFormeUZaposlenog implements Converter<ZaposleniDto, Zaposleni> {

    @Override
    public Zaposleni convert(ZaposleniDto zaposleniDto) {
        Zaposleni zaposleni = new Zaposleni();
        if (zaposleniDto.getZaposleniID() != null  && !StringUtils.isEmpty(zaposleniDto.getZaposleniID())) {
            zaposleni.setZaposleniID(new Integer(zaposleniDto.getZaposleniID()));
        }
        zaposleni.setZaposleniID(zaposleniDto.getZaposleniID());
        zaposleni.setIme(zaposleniDto.getIme());
        zaposleni.setPrezime(zaposleniDto.getPrezime());
        zaposleni.setEmail(zaposleniDto.getEmail());
        zaposleni.setUsername(zaposleniDto.getUsername());
        zaposleni.setPassword(zaposleniDto.getPassword());
        zaposleni.setUloge(zaposleniDto.getUloge());
        return zaposleni;
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
