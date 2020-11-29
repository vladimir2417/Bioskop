package Bioskop.convertors;

import Bioskop.models.Film;
import Bioskop.models.FilmDto;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.type.TypeFactory;
import com.fasterxml.jackson.databind.util.Converter;
import org.springframework.stereotype.Component;

@Component
public class IzFilmaUFormu implements Converter<Film, FilmDto> {

    @Override
    public FilmDto convert(Film film) {
        FilmDto filmDto = new FilmDto();
        filmDto.setFilmID(film.getFilmID());
        filmDto.setNaziv(film.getNaziv());
        filmDto.setGodina(film.getGodina());
        filmDto.setOpis(film.getOpis());
        filmDto.setTrajanje(film.getTrajanje());
        filmDto.setImdb(film.getImdb());
        filmDto.setYoutube(film.getYoutube());
        filmDto.setSlika(film.getSlika());
        return filmDto;
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

