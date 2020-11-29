package Bioskop.controllers;

import Bioskop.models.Klub;
import Bioskop.models.Korisnik;
import Bioskop.repositories.KlubRepository;
import Bioskop.repositories.KorisnikRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;
import java.util.Optional;

@Controller
public class KluboviController {

    @Autowired
    private KlubRepository klubRepository;
    @Autowired
    private KorisnikRepository korisnikRepository;

    //KORISNIK
    @RequestMapping("/korisnik/klubovi")
    public String prikaziKlubove(Model model) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String usernameLoginovanog = authentication.getName();
        Optional<Korisnik> loginovanKorisnik= korisnikRepository.findKorisnikByUsername(usernameLoginovanog);
        Korisnik korisnik = loginovanKorisnik.get();

        List<Klub> klubovi = klubRepository.findAll();
        List<Klub> kluboviRezervisani = klubRepository.findAllByKorisnici(korisnik);
        klubovi.removeAll(kluboviRezervisani);

        model.addAttribute("korisnik", korisnik);
        model.addAttribute("klubovi", klubovi);
        return "korisnik-klubovi";
    }

    //KORISNIK
    @RequestMapping("/korisnik/klubovi/clan")
    public String prikaziKluboveMoje(Model model) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String usernameLoginovanog = authentication.getName();
        Optional<Korisnik> loginovanKorisnik= korisnikRepository.findKorisnikByUsername(usernameLoginovanog);
        Korisnik korisnik = loginovanKorisnik.get();

        List<Klub> kluboviRezervisani = klubRepository.findAllByKorisnici(korisnik);

        model.addAttribute("korisnik", korisnik);
        model.addAttribute("klubovi", kluboviRezervisani);
        return "korisnik-klubovi-clan";
    }

    //KORISNIK
    @RequestMapping(value = "/korisnik/klubovi/sacuvaj/{klubID}", method = RequestMethod.POST)
    public String sacuvajKlub(@PathVariable String klubID, Model model) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String usernameLoginovanog = authentication.getName();
        Optional<Korisnik> loginovanKorisnik= korisnikRepository.findKorisnikByUsername(usernameLoginovanog);
        Korisnik korisnik = loginovanKorisnik.get();

        Klub klub = klubRepository.getOne(Long.valueOf(klubID));
        klub.getKorisnici().add(korisnik);
        klubRepository.save(klub);

        model.addAttribute("korisnik", korisnik);
        model.addAttribute("klubovi", klubRepository.findAll());
        return "redirect:/korisnik/klubovi/clan";
    }

    //KORISNIK
    @RequestMapping(value = "/korisnik/klubovi/obrisi/{klubID}", method = RequestMethod.POST)
    public String obrisiKlub(@PathVariable String klubID, Model model) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String usernameLoginovanog = authentication.getName();
        Optional<Korisnik> loginovanKorisnik= korisnikRepository.findKorisnikByUsername(usernameLoginovanog);
        Korisnik korisnik = loginovanKorisnik.get();

        Klub klub = klubRepository.getOne(Long.valueOf(klubID));
        klub.getKorisnici().remove(korisnik);
        klubRepository.save(klub);

        model.addAttribute("korisnik", korisnik);
        model.addAttribute("klubovi", klubRepository.findAll());
        return "redirect:/korisnik/klubovi/clan";
    }
}
