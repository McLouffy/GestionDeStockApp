package com.projetNFA036.Security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {
	@Autowired
	private javax.sql.DataSource dataSource;

	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {

		PasswordEncoder passwordEncoder = passwordEncoder();

		System.out.println("Passwors Encoded BCRYPT :******************** ");
		System.out.println(passwordEncoder.encode("123"));
		auth.jdbcAuthentication().dataSource(dataSource)
				.usersByUsernameQuery("select username , password, enabled from user where username =?")
				.authoritiesByUsernameQuery("SELECT u.username, r.role " + "FROM user_role ur, user u , roles r "
						+ "WHERE u.id_utilisateur = ur.id_utilisateur AND ur.id_role = r.id_role AND u.username = ?")
				.passwordEncoder(passwordEncoder).rolePrefix("ROLE_");
	}

	// httpS : gérer le comportement au niveau de l'authentification
	@Override
	protected void configure(HttpSecurity http) throws Exception {

		http.authorizeRequests().antMatchers("/showCreate").hasAnyRole("ADMIN");
		http.authorizeRequests().antMatchers("/saveProduit").hasAnyRole("ADMIN");
		http.authorizeRequests().antMatchers("/ListeProduits").hasAnyRole("ADMIN", "USER");
		// Pour supprimer, modifier... il faut avoir le role ADMIN
		http.authorizeRequests().antMatchers("/supprimerProduit", "/modifierProduit", "/updateProduit")
				.hasAnyRole("ADMIN");
		// toutes les requetes doivent etre authentifiées avec un formulaire formLogin
		// anyRequest : permet de cacher toutes les pages si on est pas connecté

		http.authorizeRequests().antMatchers("/login").permitAll();
		http.formLogin().loginPage("/login");
		// si exception, on redirige vers la page accesDenied
		// http.exceptionHandling().accessDeniedPage("/accessDenied");

		http.authorizeRequests().antMatchers("/webjars/**").permitAll();
		http.authorizeRequests().anyRequest().authenticated();
	}

	
	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

}
