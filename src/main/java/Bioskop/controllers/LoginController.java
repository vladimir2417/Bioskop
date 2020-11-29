package Bioskop.controllers;

import Bioskop.models.Korisnik;
import Bioskop.repositories.FilmRespository;
import Bioskop.repositories.KorisnikRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Optional;

@Controller
public class LoginController {

    @Autowired
    private FilmRespository filmRespository;
    @Autowired
    private KorisnikRepository korisnikRepository;

    @RequestMapping("/")
    public String redirect(){
        return "redirect:/pocetna";
    }

    @GetMapping("/admin/pocetna")
    public String adminDeo(){
        return "admin-pocetna";
    }

    @GetMapping("/menadzer/pocetna")
    public String menadzerDeo(){
        return "menadzer-pocetna";
    }

    @RequestMapping("/korisnik/pocetna")
    public String indexKorisnik(Model model){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String usernameLoginovanog = authentication.getName();
        Optional<Korisnik> loginovanKorisnik= korisnikRepository.findKorisnikByUsername(usernameLoginovanog);
        Korisnik korisnik = loginovanKorisnik.get();
        model.addAttribute("korisnik", korisnik);
        model.addAttribute("filmovi", filmRespository.findAll());
        model.addAttribute("filmoviNovi", filmRespository.findAllByGodina(2020));
        return "korisnik-pocetna";
    }

    @GetMapping("/login")
    public String login(){
        return "redirect:/pocetna";
    }

    @RequestMapping(value="/logout", method = RequestMethod.GET)
    public String logoutPage (HttpServletRequest request, HttpServletResponse response) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (auth != null){
            new SecurityContextLogoutHandler().logout(request, response, auth);
        }
        return "pocetna";
    }
}
