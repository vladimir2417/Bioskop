package Bioskop.controllers;

import Bioskop.email.MailService;
import Bioskop.models.*;
import Bioskop.repositories.*;
import Bioskop.services.KorisnikService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Controller
public class RezervacijeController {

    @Autowired
    private ProjekcijaRepository projekcijaRepository;
    @Autowired
    private RezervacijaRepository rezervacijaRepository;
    @Autowired
    private SedisteRepository sedisteRepository;
    @Autowired
    private KorisnikRepository korisnikRepository;
    @Autowired
    private PaketiHraneRepository paketiHraneRepository;
    @Autowired
    private Sediste_RezervacijaRepository sediste_rezervacijaRepository;
    @Autowired
    private KorisnikService korisnikService;
    @Autowired
    private MailService mailService;

    //KORISNIK
    @RequestMapping("/korisnik/rezervacija/{projekcijaID}")
    public String rezervisiKartu(@PathVariable String projekcijaID, Model model){

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String usernameLoginovanog = authentication.getName();
        Optional<Korisnik> loginovanKorisnik= korisnikRepository.findKorisnikByUsername(usernameLoginovanog);
        Korisnik korisnik = loginovanKorisnik.get();
        model.addAttribute("korisnik", korisnik);

        Optional<Projekcija> projekcija = projekcijaRepository.findById(Long.valueOf(projekcijaID));
        if(projekcija.isPresent()){
            List<Sediste> sedista = sedisteRepository.findAllBySala(projekcija.get().getSala());
            List<Sediste_Rezervacija> sediste_rezervacije = sediste_rezervacijaRepository.findAllByProjekcija(projekcija.get());

            ArrayList<Sediste> zauzetaSedista = new ArrayList<>();
            for(int i=0;i<sediste_rezervacije.size();i++) {
                zauzetaSedista.add(sediste_rezervacije.get(i).getSediste());
            }
            sedista.removeAll(zauzetaSedista);

            if(korisnik.getBrPoena()>=1000){
                projekcija.get().setCena((int) (projekcija.get().getCena()-(projekcija.get().getCena()*0.1)));
                model.addAttribute("popust", "*Cena sa popustom.");
            }
            model.addAttribute("projekcija", projekcija);
            model.addAttribute("sedista", sedista);
            model.addAttribute("rezervacija", new Rezervacija());
            model.addAttribute("paketi", paketiHraneRepository.findAll());
        }
        else {
            model.addAttribute("greska", "Nema projekcija za unete parametre!");
        }
        return "korisnik-rezervacija";
    }

    //KORISNIK
    @RequestMapping(value = "/korisnik/rezervacija/sacuvaj", method = RequestMethod.POST)
    public String sacuvajRezervaciju(@Valid Rezervacija rezervacijaDto, @RequestParam(value="projekcija", required = false) String projekcijaID, @RequestParam(value="paketID", required = false) String paketID, BindingResult bindingResult, RedirectAttributes redirAttrs, Model model) throws IOException {
        Rezervacija rezervacija = new Rezervacija();

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String usernameLoginovanog = authentication.getName();
        Optional<Korisnik> loginovanKorisnik= korisnikRepository.findKorisnikByUsername(usernameLoginovanog);
        rezervacija.setKorisnik(loginovanKorisnik.get());

        Optional<Projekcija> projekcija = projekcijaRepository.findById(Long.valueOf(projekcijaID));
        rezervacija.setProjekcija(projekcija.get());

        rezervacija.setSediste(rezervacijaDto.getSediste());

        Optional<PaketiHrane> paketiHrane = paketiHraneRepository.findById(Long.valueOf(paketID));
        if(Integer.valueOf(paketID)!=0) {
        rezervacija.setPaketiHrane(paketiHrane.get());

            if(loginovanKorisnik.get().getBrPoena()>=1000){
                rezervacija.setCena((int) (projekcija.get().getCena()-(projekcija.get().getCena()*0.1)+paketiHrane.get().getCena()));
                model.addAttribute("popust", "*Cena sa popustom.");
            }
            else{
                rezervacija.setCena(projekcija.get().getCena()+paketiHrane.get().getCena());
            }
            model.addAttribute("izabranPaket", rezervacija.getPaketiHrane().getNaziv());

        }
        else {
                rezervacija.setPaketiHrane(null);
            if(loginovanKorisnik.get().getBrPoena()>=1000){
                rezervacija.setCena((int) (projekcija.get().getCena()-(projekcija.get().getCena()*0.1)));
                model.addAttribute("popust", "*Cena sa popustom.");
            }
            else{
                rezervacija.setCena(projekcija.get().getCena());
            }
            model.addAttribute("izabranPaket", "Bez hrane");

        }

        rezervacijaRepository.save(rezervacija);

        Sediste_Rezervacija sediste_rezervacija = new Sediste_Rezervacija();
        sediste_rezervacija.setProjekcija(projekcija.get());
        sediste_rezervacija.setRezervacija(rezervacija);
        Optional<Sediste> sediste = sedisteRepository.findById(rezervacijaDto.getSediste());
        sediste_rezervacija.setSediste(sediste.get());
        sediste_rezervacijaRepository.save(sediste_rezervacija);

        loginovanKorisnik.get().setBrPoena(loginovanKorisnik.get().getBrPoena()+100);
        korisnikRepository.save(loginovanKorisnik.get());

        String from = "bioskopbolero@gmail.com";
        String recipient = loginovanKorisnik.get().getEmail();
        String subject = "Uspesna rezervacija karte!";

        String message = "Podaci o projekciji:                                                                                       " +
                " Film: " + projekcija.get().getFilmNaziv() + ", Bioskop: " + projekcija.get().getBioskopNaziv() +
                ", Sala: " + projekcija.get().getSalaNaziv() + ", Datum: " + projekcija.get().getDatum() + ", Vreme: " + projekcija.get().getVreme()
                + ", Cena: " + projekcija.get().getCena() + "NAPOMENA: Kartu mozete preuzeti 30 min pre projekcije! Hvala na poverenju, bioskop Bolero.";
        /*mailService.prepareAndSend(from, recipient, subject, message);*/

        model.addAttribute("projekcija", projekcija);
        model.addAttribute("rezervacija", rezervacija);
        model.addAttribute("sediste", rezervacija.getSediste());

        if(bindingResult.hasErrors()){
            return "korisnik-rezervacija";
        }
        Korisnik korisnik = loginovanKorisnik.get();
        model.addAttribute("korisnik", korisnik);
        return "korisnik-rezervacija-uspesna";
    }
}
