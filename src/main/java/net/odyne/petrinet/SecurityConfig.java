package net.odyne.petrinet;

import java.util.Collections;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;

import net.odyne.petrinet.entities.Customer;
import net.odyne.petrinet.repositories.UserRepository;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(securedEnabled = true, prePostEnabled = true)
public class SecurityConfig extends WebSecurityConfigurerAdapter {

	@Autowired
	private UserRepository userRepository;

	public static GrantedAuthority ROLE_USER = new SimpleGrantedAuthority("ROLE_USER");

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http

		// no session management
				.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS).and()

		// no authorization needed for logging in
				.authorizeRequests().antMatchers("/login").permitAll().and()

		// add authentication for all remaining requests
				.authorizeRequests().anyRequest().authenticated().and().httpBasic()

		// disable csrf for testing purposes
				.and().csrf().disable();
	}

	@Override
	public void configure(AuthenticationManagerBuilder auth) throws Exception {
		DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
		provider.setUserDetailsService((username) -> new LoginToken(userRepository.findByUsername(username)));
		auth.authenticationProvider(provider);
	}

	/**
	 * Decorating a spring security user with a customer.
	 */
	public static class LoginToken extends User {

		private static final long serialVersionUID = -696064533050881506L;

		private Customer customer;

		public LoginToken(Customer customer) {
			super(customer.getUsername(), customer.getPassword(), Collections.singleton(ROLE_USER));
			this.customer = customer;
		}

		public Customer getCustomer() {
			return customer;
		}

	}

}
