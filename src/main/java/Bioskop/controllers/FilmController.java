package Bioskop.controllers;

import Bioskop.models.Film;
import Bioskop.models.FilmDto;
import Bioskop.models.Korisnik;
import Bioskop.models.KorisnikDto;
import Bioskop.repositories.FilmRespository;
import Bioskop.repositories.KorisnikRepository;
import Bioskop.services.FilmService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.multipart.MultipartFile;
import javax.validation.Valid;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Optional;


@Controller
public class FilmController {

    @Autowired
    private FilmRespository filmRespository;
    @Autowired
    private FilmService filmService;
    @Autowired
    private KorisnikRepository korisnikRepository;

    private Bioskop.convertors.IzFilmaUFormu izFilmaUFormu;

    @Autowired
    public void setFilmDto(Bioskop.convertors.IzFilmaUFormu izFilmaUFormu) {
        this.izFilmaUFormu = izFilmaUFormu;
    }

    @Autowired
    public void setFilmService(FilmService filmService) {
        this.filmService = filmService;
    }

    //SVI
    @RequestMapping("/filmovi")
    public String prikazilistuFilmova(Model model){
        model.addAttribute("filmovi", filmRespository.findAll(Sort.by(Sort.Direction.ASC, "naziv")));
        model.addAttribute("korisnikDto", new KorisnikDto());
        return "pocetna-filmovi";
    }

    //SVI
    @RequestMapping("/film/{filmID}")
    public String prikaziFilm(@PathVariable String filmID, Model model){
        Film film = filmService.getById(Long.valueOf(filmID));
        model.addAttribute("korisnikDto", new KorisnikDto());
        model.addAttribute("film", film);
        model.addAttribute("filmovi", filmRespository.findAll(Sort.by(Sort.Direction.ASC, "naziv")));
        model.addAttribute("filmoviNovi", filmRespository.findAllByGodina(2020));
        return "pocetna-film";
    }

    //KORISNIK
    @RequestMapping("/korisnik/filmovi")
    public String prikazilistuFilmovaKorisnik(Model model){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String usernameLoginovanog = authentication.getName();
        Optional<Korisnik> loginovanKorisnik= korisnikRepository.findKorisnikByUsername(usernameLoginovanog);
        Korisnik korisnik = loginovanKorisnik.get();
        model.addAttribute("korisnik", korisnik);
        model.addAttribute("filmovi", filmRespository.findAll(Sort.by(Sort.Direction.ASC, "naziv")));
        return "korisnik-filmovi";
    }

    //KORISNIK
    @RequestMapping("/korisnik/film/{filmID}")
    public String prikaziFilmKorisnik(@PathVariable String filmID, Model model){
        Film film = filmService.getById(Long.valueOf(filmID));
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String usernameLoginovanog = authentication.getName();
        Optional<Korisnik> loginovanKorisnik= korisnikRepository.findKorisnikByUsername(usernameLoginovanog);
        Korisnik korisnik = loginovanKorisnik.get();
        model.addAttribute("korisnik", korisnik);
        model.addAttribute("film", film);
        model.addAttribute("filmoviNovi", filmRespository.findAllByGodina(2020));
        return "korisnik-film";
    }

    //ADMIN
    @RequestMapping("/admin/filmovi/lista")
    public String listaFilmovaAdmin(Model model){
        model.addAttribute("filmovi", filmService.listAll());
        return "admin-filmovi-lista";
    }

    //ADMIN
    @RequestMapping("/admin/filmovi/novi")
    public String noviFilm(Model model){
        model.addAttribute("filmDto", new FilmDto());
        return "admin-filmovi-novi";
    }

    //ADMIN
    @RequestMapping(value = "/admin/filmovi/sacuvaj-izmene", method = RequestMethod.POST)
    public String sacuvajIzmeneFilmaAdmin(@Valid FilmDto filmDto, BindingResult bindingResult) throws IOException {
        String uploadDirectory = System.getProperty("user.dir") + "/src/main/uploads/static/slikeFilmova";
        MultipartFile slika = filmDto.getSlikaMulti();
        Path imageNameAndPath = Paths.get(uploadDirectory, slika.getOriginalFilename());
        String putanja = "../uploads/static/slikeFilmova/" + slika.getOriginalFilename();
        Files.write(imageNameAndPath, slika.getBytes());
        filmDto.setSlika(putanja);

        if(bindingResult.hasErrors()){
            return "admin-filmovi-novi";
        }

        filmService.saveFilm(filmDto);
        return "redirect:/admin/filmovi/lista/";
    }

    //ADMIN
    @RequestMapping(value = "/admin/filmovi/sacuvaj", method = RequestMethod.POST)
    public String sacuvajFilm(@Valid FilmDto filmDto, BindingResult bindingResult) throws IOException {
        String uploadDirectory = System.getProperty("user.dir") + "/src/main/uploads/static/slikeFilmova";
        MultipartFile slika = filmDto.getSlikaMulti();
        Path imageNameAndPath = Paths.get(uploadDirectory, slika.getOriginalFilename());
        String putanja = "../uploads/static/slikeFilmova/" + slika.getOriginalFilename();
        Files.write(imageNameAndPath, slika.getBytes());
        filmDto.setSlika(putanja);

        if(bindingResult.hasErrors()){
            return "admin-filmovi-novi";
        }

        filmService.saveFilm(filmDto);
        return "redirect:/admin/filmovi/lista";
    }

    //ADMIN
    @RequestMapping("/admin/filmovi/izmeni/{filmID}")
    public String izmeniFilmAdmin(@PathVariable String filmID, Model model){
        Film film = filmService.getById(Long.valueOf(filmID));
        FilmDto filmDto = izFilmaUFormu.convert(film);
        model.addAttribute("filmDto", filmDto);
        return "admin-filmovi-izmeni";
    }

    //ADMIN
    @RequestMapping("/admin/filmovi/obrisi/{filmID}")
    public String obrisiFilmAdmin(@PathVariable String filmID){
        filmService.deleteById(Long.valueOf(filmID));
        return "redirect:/admin/filmovi/lista";
    }

}
