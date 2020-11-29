package Bioskop.convertors;

import Bioskop.models.Film;
import Bioskop.models.FilmDto;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.type.TypeFactory;
import com.fasterxml.jackson.databind.util.Converter;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

@Component
public class izFormeUFilm implements Converter<FilmDto, Film> {

    @Override
    public Film convert(FilmDto filmDto) {
        Film film = new Film();
        if (filmDto.getFilmID() != null  && !StringUtils.isEmpty(filmDto.getFilmID())) {
            film.setFilmID(new Long(filmDto.getFilmID()));
        }

        film.setNaziv(filmDto.getNaziv());
        film.setGodina(filmDto.getGodina());
        film.setOpis(filmDto.getOpis());
        film.setTrajanje(filmDto.getTrajanje());
        film.setImdb(filmDto.getImdb());
        film.setYoutube(filmDto.getYoutube());
        film.setSlika(filmDto.getSlika());

        return film;
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
