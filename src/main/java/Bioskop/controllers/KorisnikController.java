package Bioskop.controllers;

import Bioskop.convertors.IzKorisnikaUFormu;
import Bioskop.email.MailService;
import Bioskop.models.Korisnik;
import Bioskop.models.KorisnikDto;
import Bioskop.models.TipKorisnika;
import Bioskop.models.Zaposleni;
import Bioskop.repositories.*;
import Bioskop.services.KorisnikService;
import Bioskop.services.TipKorisnikaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.validation.Valid;
import java.util.Optional;


@Controller
public class KorisnikController {

    @Autowired
    private KorisnikService korisnikService;
    @Autowired
    private KorisnikRepository korisnikRepository;
    @Autowired
    private ZaposleniRepository zaposleniRepository;
    @Autowired
    private TipKorisnikaService tipKorisnikaService;
    @Autowired
    private FilmRespository filmRespository;
    @Autowired
    private GradRepository gradRepository;
    @Autowired
    private BioskopRepository bioskopRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private MailService mailService;
    @Autowired
    private PaketiHraneRepository paketiHraneRepository;

    private IzKorisnikaUFormu izKorisnikaUFormu;

    @Autowired
    public void setKorisnikURegisterDto(IzKorisnikaUFormu izKorisnikaUFormu) {
        this.izKorisnikaUFormu = izKorisnikaUFormu;
    }

    @Autowired
    public void setKorisnikService(KorisnikService korisnikService) {
        this.korisnikService = korisnikService;
    }

    //SVI
    @RequestMapping("/pocetna")
    public String noviKorisnik(Model model){
        model.addAttribute("filmovi", filmRespository.findAll());
        model.addAttribute("filmoviNovi", filmRespository.findAllByGodina(2020));
        model.addAttribute("korisnikDto", new KorisnikDto());
        return "pocetna";
    }

    //SVI
    @RequestMapping(value = "/korisnik-sacuvaj", method = RequestMethod.POST)
    public String sacuvajKorisnika(@Valid KorisnikDto korisnikDto, BindingResult bindingResult){
        if(bindingResult.hasErrors()){
            return "pocetna";
        }
        Optional<TipKorisnika> uloge = tipKorisnikaService.findById(1);
        if(uloge.isPresent()){
            Optional<Korisnik> probaKorisnik = korisnikRepository.findKorisnikByUsername(korisnikDto.getUsername());
            Optional<Korisnik> probaKorisnik2 = korisnikRepository.findKorisnikByEmail(korisnikDto.getEmail());
            Optional<Zaposleni> probaZaposleni = zaposleniRepository.findZaposleniByUsername(korisnikDto.getUsername());
            if(!probaKorisnik.isPresent()){
                if(!probaKorisnik2.isPresent()) {
                    if(!probaZaposleni.isPresent()) {
                        korisnikDto.setUloge(uloge.get());
                        korisnikDto.setPassword(passwordEncoder.encode(korisnikDto.getPassword()));
                        korisnikDto.setBrPoena(100);
                        korisnikService.saveReg(korisnikDto);
                        String from = "bioskopbolero@gmail.com";
                        String recipient = korisnikDto.getEmail();
                        String subject = "Uspesna registracija!";
                        String message = "Hvala sto ste se registrovali na sajt bioskopa Bolero. Vas username je: "+ korisnikDto.getUsername();
                        mailService.prepareAndSend(from, recipient, subject, message);
                    }
                    else {
                        return "redirect:/pocetna";
                    }
                }
                else {
                    return "redirect:/pocetna";
                }
            }
            else {
                return "redirect:/pocetna";
            }
        }
        return "redirect:/pocetna";
    }

    //KORISNIK
    @GetMapping("/korisnik/repertoar-pretraga")
    public String repertoarPretraga(Model model){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String usernameLoginovanog = authentication.getName();
        Optional<Korisnik> loginovanKorisnik= korisnikRepository.findKorisnikByUsername(usernameLoginovanog);
        Korisnik korisnik = loginovanKorisnik.get();
        model.addAttribute("korisnik", korisnik);

        model.addAttribute("filmovi", filmRespository.findAll());
        model.addAttribute("gradovi", gradRepository.findAll());
        model.addAttribute("bioskopi", bioskopRepository.findAll());
        return "korisnik-repertoar-pretraga";
    }

    //KORISNIK
    @RequestMapping("/korisnik/paketi-hrane")
    public String prikaziPaketeHrane(Model model) {

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String usernameLoginovanog = authentication.getName();
        Optional<Korisnik> loginovanKorisnik= korisnikRepository.findKorisnikByUsername(usernameLoginovanog);
        Korisnik korisnik = loginovanKorisnik.get();
        model.addAttribute("paketi", paketiHraneRepository.findAll());
        model.addAttribute("korisnik", korisnik);
        return "korisnik-paketi-hrane";
    }

    //ADMIN
    @RequestMapping("/admin/korisnici/lista")
    public String listaKorisnika(Model model){
        model.addAttribute("korisnici", korisnikService.listAll());
        return "admin-korisnici-lista";
    }

    //ADMIN
    @RequestMapping("/admin/korisnici/obrisi/{korisnikID}")
    public String obrisi(@PathVariable String korisnikID){
        korisnikService.deleteById(Long.valueOf(korisnikID));
        return "redirect:/admin/korisnici/lista";
    }

}

