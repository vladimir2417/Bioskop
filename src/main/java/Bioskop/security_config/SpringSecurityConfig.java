package Bioskop.security_config;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;


@EnableWebSecurity
public class SpringSecurityConfig extends WebSecurityConfigurerAdapter {

    @Qualifier("mojKorisnikDetaljiService")
    @Autowired
    UserDetailsService userDetailsService;

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception{
        auth.userDetailsService(userDetailsService);
    }

    @Bean
    public AuthenticationSuccessHandler myAuthenticationSuccessHandler(){
        return new MySimpleUrlAuthenticationSuccessHandler();
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception{
                http.csrf().disable()
                .authorizeRequests()
                .antMatchers("/uploads/static/slikeFilmova/**","/resources/**", "/css/**","/cute-alert-master/**","/css/css2/**","/css/list-css/**","/js2/**","/list-js/**","/fonts/**", "/images/**").permitAll()
                .antMatchers( "/admin/pocetna","/admin/korisnici/lista", "/admin/korisnici/obrisi/{korisnikID}","/admin/menadzeri/lista", "/admin/menadzeri/izmeni/{zaposleniID}", "/admin/menadzeri/novi", "/admin/menadzeri/sacuvaj","/admin/menadzeri/sacuvaj-izmene","/admin/menadzeri/obrisi/{zaposleniID}","/admin/filmovi/lista","/admin/filmovi/novi","/admin/filmovi/sacuvaj-izmene","/admin/filmovi/sacuvaj", "/admin/filmovi/izmeni/{filmID}", "/admin/filmovi/obrisi/{filmID}", "/admin/projekcije/sacuvaj", "/admin/projekcije/nova","/admin/projekcije/lista","/admin/projekcije/obrisi/{projekcijaID}").hasAuthority("admin")
                        .antMatchers("/menadzer/pocetna","/menadzer/projekcije/izvestaj","/menadzer/rezervacije/izvestaj").hasAuthority("menadzer")
                        .antMatchers("/korisnik/pocetna","/korisnik/filmovi","/korisnik/film/{filmID}","/korisnik/klubovi", "/korisnik/klubovi/clan", "/korisnik/klubovi/sacuvaj/{klubID}", "/korisnik/klubovi/obrisi/{klubID}", "/korisnik/repertoar-pretraga", "/korisnik/paketi-hrane","/korisnik/projekcije", "/korisnik/projekcije/{filmID}", "/korisnik/rezervacija/{projekcijaID}", "/korisnik/rezervacija/sacuvaj").hasAuthority("korisnik")
                .antMatchers("/","/pocetna","/korisnik-sacuvaj", "/filmovi", "/film/{filmID}", "/login","/logout").permitAll()
                        .anyRequest().authenticated()
                 .and()
               .formLogin()
               .loginPage("/login")
                        .successHandler(myAuthenticationSuccessHandler())
              .permitAll()
              .and()

              .logout().logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
                        .logoutSuccessUrl("/pocetna");
    }}
