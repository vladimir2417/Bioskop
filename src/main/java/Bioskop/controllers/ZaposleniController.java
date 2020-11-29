package Bioskop.controllers;

import Bioskop.convertors.IzZaposlenogUFormu;
import Bioskop.repositories.ZaposleniRepository;
import Bioskop.models.Zaposleni;
import Bioskop.models.ZaposleniDto;
import Bioskop.models.TipKorisnika;
import Bioskop.services.FilmService;
import Bioskop.services.ProjekcijaService;
import Bioskop.services.TipKorisnikaService;
import Bioskop.services.ZaposleniService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.validation.Valid;
import java.util.Optional;


@Controller
public class ZaposleniController {

    @Autowired
    private ZaposleniService zaposleniService;
    @Autowired
    private ZaposleniRepository zaposleniRepository;
    @Autowired
    private TipKorisnikaService tipKorisnikaService;
    @Autowired
    private FilmService filmService;
    @Autowired
    private ProjekcijaService projekcijaService;
    @Autowired
    private PasswordEncoder passwordEncoder;

    private IzZaposlenogUFormu izZaposlenogUFormu;

    @Autowired
    public void setZaposleniDto(IzZaposlenogUFormu izZaposlenogUFormu) {
        this.izZaposlenogUFormu = izZaposlenogUFormu;
    }

    @Autowired
    public void setZaposleniService(ZaposleniService zaposleniService) {
        this.zaposleniService = zaposleniService;
    }

    //ADMIN
    @RequestMapping({"/admin/menadzeri/lista"})
    public String listaMenadzera(Model model){
        Optional<TipKorisnika> uloge = tipKorisnikaService.findById(3);
            model.addAttribute("zaposleni", zaposleniRepository.findAllByUloge(uloge.get()));
            return "admin-menadzeri-lista";
    }

    //ADMIN
    @RequestMapping("/admin/menadzeri/izmeni/{zaposleniID}")
    public String izmeniMenadzera(@PathVariable String zaposleniID, Model model){
        Zaposleni zaposleni = zaposleniService.getById(Integer.valueOf(zaposleniID));
        ZaposleniDto zaposleniDto = izZaposlenogUFormu.convert(zaposleni);
        model.addAttribute("zaposleniDto", zaposleniDto);
        return "admin-menadzeri-izmeni";
    }

    //ADMIN
    @RequestMapping("/admin/menadzeri/novi")
    public String noviMenadzer(Model model){
        model.addAttribute("zaposleniDto", new ZaposleniDto());
        return "admin-menadzeri-novi";
    }

    //ADMIN
    @RequestMapping(value = "/admin/menadzeri/sacuvaj", method = RequestMethod.POST)
    public String sacuvajIliIzmeniMenadzera(@Valid ZaposleniDto zaposleniDto, BindingResult bindingResult){
        if(bindingResult.hasErrors()){
            return "admin-menadzeri-novi";
        }
        Optional<Zaposleni> probaMenadzer = zaposleniRepository.findZaposleniByUsername(zaposleniDto.getUsername());
        Optional<TipKorisnika> uloge = tipKorisnikaService.findById(3);
        if(uloge.isPresent()){
            if(!probaMenadzer.isPresent()) {
                zaposleniDto.setUloge(uloge.get());
                zaposleniDto.setPassword(passwordEncoder.encode(zaposleniDto.getPassword()));
                zaposleniService.saveUst(zaposleniDto);
            }
            else {
                return "admin-menadzeri-novi";
            }
        }
        return "redirect:/admin/menadzeri/lista";
    }

    //ADMIN
    @RequestMapping(value = "/admin/menadzeri/sacuvaj-izmene", method = RequestMethod.POST)
    public String izmeniMenadzera(@Valid ZaposleniDto zaposleniDto, BindingResult bindingResult){

        if(bindingResult.hasErrors()){
            return "admin-menadzeri-novi";
        }
        Optional<TipKorisnika> uloge = tipKorisnikaService.findById(3);
        if(uloge.isPresent()){
                zaposleniDto.setUloge(uloge.get());
                zaposleniService.saveUst(zaposleniDto);
        }
        return "redirect:/admin/menadzeri/lista";
    }

    //ADMIN
    @RequestMapping("/admin/menadzeri/obrisi/{zaposleniID}")
    public String obrisi(@PathVariable String zaposleniID){
        zaposleniService.deleteById(Integer.valueOf(zaposleniID));
        return "redirect:/admin/menadzeri/lista";
    }

    //MENADZER
    @RequestMapping({"/menadzer/projekcije/izvestaj"})
    public String brojProjekcija(Model model){
        model.addAttribute("filmovi", filmService.listAll());
        return "menadzer-brojProjekcija";
    }

    //MENADZER
    @RequestMapping({"/menadzer/rezervacije/izvestaj"})
    public String brojREzervacija(Model model){
        model.addAttribute("projekcije", projekcijaService.listAll());
        return "menadzer-brojRezervacija";
    }
}


