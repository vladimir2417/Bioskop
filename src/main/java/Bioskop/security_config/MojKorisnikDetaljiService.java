package Bioskop.security_config;

import Bioskop.models.Korisnik;
import Bioskop.models.Zaposleni;
import Bioskop.repositories.KorisnikRepository;
import Bioskop.repositories.ZaposleniRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class MojKorisnikDetaljiService implements UserDetailsService {

    @Autowired
    KorisnikRepository korisnikRepository;
    @Autowired
    ZaposleniRepository zaposleniRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<Korisnik> korisnik = korisnikRepository.findKorisnikByUsername(username);
        if (korisnik.isPresent()) {
            korisnik.orElseThrow(() -> new UsernameNotFoundException("Not found: " + username));
            return korisnik.map(MojKorisnikDetalji::new).get();
        } else {
            Optional<Zaposleni> zaposleni = zaposleniRepository.findZaposleniByUsername(username);
            zaposleni.orElseThrow(() -> new UsernameNotFoundException("Not found: " + username));
                return zaposleni.map(MojZaposleniDetalji::new).get();
        }
    }
}
