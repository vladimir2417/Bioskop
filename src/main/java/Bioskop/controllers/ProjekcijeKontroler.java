package Bioskop.controllers;

import Bioskop.models.*;
import Bioskop.repositories.*;
import Bioskop.services.FilmService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.transaction.Transactional;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

@Controller
public class ProjekcijeKontroler {

    @Autowired
    private KorisnikRepository korisnikRepository;
    @Autowired
    private ProjekcijaRepository projekcijaRepository;
    @Autowired
    private BioskopRepository bioskopRepository;
    @Autowired
    private FilmRespository filmRespository;
    @Autowired
    private FilmService filmService;
    @Autowired
    private SalaRepository salaRepository;
    @Autowired
    private RezervacijaRepository rezervacijaRepository;

    //KORISNIK
    @RequestMapping("/korisnik/projekcije")
    public String prikazilistuProjekcija(
            @RequestParam(value="datum", required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) Date datum,
            @RequestParam(value="grad", required = false) String grad,
            @RequestParam(value="bioskop", required = false) String bioskopNaziv,
            Model model){

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String usernameLoginovanog = authentication.getName();
        Optional<Korisnik> loginovanKorisnik= korisnikRepository.findKorisnikByUsername(usernameLoginovanog);
        Korisnik korisnik = loginovanKorisnik.get();
        model.addAttribute("korisnik", korisnik);

        Bioskop bioskop = bioskopRepository.findBioskopByNaziv(bioskopNaziv);
        List<Projekcija> projekcije = projekcijaRepository.findAllByDatumAndBioskopAndGrad(datum, bioskop, grad);
        if(!projekcije.isEmpty()){
            model.addAttribute("projekcije", projekcije);
        }
        else {
            model.addAttribute("greska", "Nema projekcija za unete parametre!");
        }
        return "korisnik-projekcije";
    }

    //KORISNIK
    @RequestMapping("/korisnik/projekcije/{filmID}")
    public String prikaziListuProjekcijaPoFilmu(@PathVariable String filmID, Model model){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String usernameLoginovanog = authentication.getName();
        Optional<Korisnik> loginovanKorisnik= korisnikRepository.findKorisnikByUsername(usernameLoginovanog);
        Korisnik korisnik = loginovanKorisnik.get();
        model.addAttribute("korisnik", korisnik);

        Film film = filmService.getById(Long.valueOf(filmID));
        List<Projekcija> projekcije = projekcijaRepository.findAllByFilm(film);
        if(!projekcije.isEmpty()){
            model.addAttribute("projekcije", projekcije);
        }
        else {
            model.addAttribute("greska", "Nema projekcija za unete parametre!");
        }
        return "korisnik-projekcije";
    }

    //ADMIN
    @RequestMapping("/admin/projekcije/nova")
    public String novaProjekcija(Model model){
        model.addAttribute("filmovi", filmRespository.findAll());
        model.addAttribute("bioskopi", bioskopRepository.findAll());
        model.addAttribute("sale", salaRepository.findAll());
        return "admin-projekcije-nova";
    }

    //ADMIN
    @RequestMapping(value = "/admin/projekcije/sacuvaj", method = RequestMethod.POST)
    public String sacuvajIliIzmeniProjekciju(@RequestParam(value="bioskopStr", required = false) String bioskopID,
                                             @RequestParam(value="filmStr", required = false) String filmID,
                                             @RequestParam(value="salaVrsta", required = false) String salaVrsta,
                                             @RequestParam(value="datumStr", required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) Date datum,
                                             @RequestParam(value="datumStr", required = false) String datumStr,
                                             @RequestParam(value="cena", required = false) String cenaStr,
                                             @RequestParam(value="vremeStr", required = false)  @DateTimeFormat(pattern="HH:mm") Date vreme,
                                             @RequestParam(value="vremeStr", required = false) String vremeStr
                                             ) throws ParseException {

        Optional<Bioskop> bioskop = bioskopRepository.findById(Long.valueOf(bioskopID));
        Optional<Film> film = filmRespository.findById(Long.valueOf(filmID));
        Optional<Sala> sala = salaRepository.findAllByBioskopAndNaziv(bioskop.get(), salaVrsta);
        String grad = bioskop.get().getGrad().getNaziv();
        Integer cena = Integer.valueOf(cenaStr);

        Projekcija projekcija = new Projekcija();
        projekcija.setBioskop(bioskop.get());
        projekcija.setFilm(film.get());
        projekcija.setSala(sala.get());
        projekcija.setGrad(grad);
        projekcija.setDatum(datum);
        projekcija.setVreme(vreme);
        projekcija.setCena(cena);
        projekcijaRepository.save(projekcija);

        String year = String.valueOf(Calendar.getInstance().get(Calendar.YEAR));
        String month = String.valueOf(Calendar.getInstance().get(Calendar.MONTH)+1);
        String day = String.valueOf(Calendar.getInstance().get(Calendar.DAY_OF_MONTH));

        String hour = String.valueOf(Calendar.getInstance().get(Calendar.HOUR_OF_DAY));
        String minute = String.valueOf(Calendar.getInstance().get(Calendar.MINUTE));
        String second = String.valueOf(Calendar.getInstance().get(Calendar.SECOND));

        if(month.length()<2){
            month="0"+month;
        }
        if(day.length()<2){
            day="0"+day;
        }
        if(hour.length()<2){
            hour="0"+hour;
        }
        if(minute.length()<2){
            minute="0"+minute;
        }
        if(second.length()<2){
            second="0"+second;
        }

        String dateStart = year+"-"+month+"-"+day+" "+hour+":"+minute+":"+second;
        String dateStop = datumStr + " "+ vremeStr+":00";

        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

        Date d1 = null;
        Date d2 = null;

            d1 = format.parse(dateStart);
            d2 = format.parse(dateStop);

            long diff = d2.getTime() - d1.getTime();

        TimerTask timerTask = new TimerTask() {
            @Transactional
            @Modifying
            @Override
            public void run() {
                System.out.println("Rezervacije obrisane za " + diff + " "+dateStart+" "+dateStop );
                rezervacijaRepository.deleteAllByProjekcija(projekcija);
                cancel();
            }
        };

        Timer timer = new Timer("Timer");
        long period = 1000L;
        timer.scheduleAtFixedRate(timerTask, diff-30000L, period);

        TimerTask timerTask2 = new TimerTask() {
            @Transactional
            @Modifying
            @Override
            public void run() {
                System.out.println("Projekcija obrisana za " + diff + " "+dateStart+" "+dateStop );
                projekcijaRepository.deleteById(Long.valueOf(projekcija.getProjekcijaID()));
                cancel();
            }
        };

        Timer timer2 = new Timer("Timer");
        timer2.scheduleAtFixedRate(timerTask2, diff, period);
        System.out.println("Projekcija obrisana za " + diff + " "+dateStart+" "+dateStop );

        return "redirect:/admin/projekcije/lista";
    }

    //ADMIN
    @RequestMapping({"/admin/projekcije/lista"})
    public String listaProjekcija(Model model){

        model.addAttribute("projekcije", projekcijaRepository.findAll());
        return "admin-projekcije-lista";
    }

    //ADMIN
    @RequestMapping("/admin/projekcije/obrisi/{projekcijaID}")
    public String obrisi(@PathVariable String projekcijaID){
        projekcijaRepository.deleteById(Long.valueOf(projekcijaID));
        return "redirect:/admin/projekcije/lista";
    }

}
