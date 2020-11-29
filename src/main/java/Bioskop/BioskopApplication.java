package Bioskop;

import Bioskop.security_config.WebConfig;
import Bioskop.repositories.KorisnikRepository;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@SpringBootApplication
@EnableCaching
@EnableJpaRepositories(basePackageClasses = KorisnikRepository.class)
public class BioskopApplication extends WebConfig {

	public static void main(String[] args) {
		SpringApplication.run(BioskopApplication.class, args);
	}

	@Bean
	public BCryptPasswordEncoder bCryptPasswordEncoder() {
		return new BCryptPasswordEncoder();
	}

	@Configuration
	public class AdditionalResourceWebConfiguration implements WebMvcConfigurer {

		@Override
		public void addResourceHandlers(final ResourceHandlerRegistry registry) {
			registry.addResourceHandler("/uploads/**").addResourceLocations("file:///" + System.getProperty("user.dir")
					+ "/src/main/uploads/");
		}
	}
}
