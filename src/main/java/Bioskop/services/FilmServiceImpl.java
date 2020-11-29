package Bioskop.services;


import Bioskop.models.Film;
import Bioskop.models.FilmDto;
import Bioskop.repositories.FilmRespository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


@Service
public class FilmServiceImpl implements FilmService {

    @Autowired
    private Bioskop.convertors.izFormeUFilm izFormeUFilm;

    @Autowired
    private FilmRespository filmRespository;

    @Override
    public List<Film> getFilm() {
        return this.filmRespository.findAll();
    }

    @Override
    public Optional<Film> findById(Long filmID) {
        return this.filmRespository.findById(filmID);
    }

    @Override
    public void deleteById(Long filmID) {
        this.filmRespository.deleteById(filmID);
    }

    @Override
    public Film getById(Long filmID) {
        return filmRespository.findById(filmID).orElse(null);
    }

    @Override
    public Film saveOrUpdate(Film film) {
        filmRespository.save(film);
        return film;
    }

    @Override
    public Film saveFilm(FilmDto filmDto) {
        Film noviFilm = saveOrUpdate(izFormeUFilm.convert(filmDto));
        return noviFilm;
    }

    @Override
    public Object listAll() {
        List<Film> filmovi = new ArrayList<>();
        filmRespository.findAll().forEach(filmovi::add);
        return filmovi;
    }

}
