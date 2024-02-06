package org.formation;



import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import static org.springframework.security.config.Customizer.*;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.csrf.CookieCsrfTokenRepository;

@Configuration
public class SecurityConfig {

	
	@Bean
	public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
		http
			.authorizeHttpRequests((requests) -> requests
				.requestMatchers("/search","/ssrf","/ssrf/post").permitAll()
				.requestMatchers("/admin").hasRole("ADMIN")
				.anyRequest().authenticated()
			).formLogin(withDefaults())
			.logout(withDefaults())
//			.csrf(csrf -> csrf.csrfTokenRepository(CookieCsrfTokenRepository.withHttpOnlyFalse()));
			.csrf(csrf -> csrf.disable());

		return http.build();
	}
	
	@Bean
	public UserDetailsService userDetailsService() {
		UserDetails user =
			 User.withUsername("user")
				.password("{noop}user")
				.roles("USER")
				.build();

		UserDetails admin =
				 User.withUsername("admin")
					.password("{noop}admin")
					.roles("ADMIN")
					.build();
		
		return new InMemoryUserDetailsManager(user,admin);
	}
}
