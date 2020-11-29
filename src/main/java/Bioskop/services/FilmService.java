package Bioskop.services;

import Bioskop.models.Film;
import Bioskop.models.FilmDto;

import java.util.List;
import java.util.Optional;

public interface FilmService {
    List<Film> getFilm();
    Optional<Film> findById(Long filmID);
    Film saveOrUpdate(Film film);
    void deleteById(Long filmID);
    Film getById(Long filmID);
    Film saveFilm(FilmDto filmDto);
    Object listAll();
}
