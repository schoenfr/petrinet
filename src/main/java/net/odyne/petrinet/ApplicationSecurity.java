package net.odyne.petrinet;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

import net.odyne.petrinet.auth.AuthFilter;
import net.odyne.petrinet.repositories.UserRepository;

@Configuration
@EnableWebSecurity
public class ApplicationSecurity extends WebSecurityConfigurerAdapter {

	// @Autowired
	// private AuthProvider authProvider;

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private AuthFilter filter;

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		// http.csrf().disable();
		http.addFilterBefore(filter, BasicAuthenticationFilter.class);
		// http.authorizeRequests().anyRequest().authenticated();
		// http.authorizeRequests().antMatchers("/login**").permitAll().anyRequest().authenticated();
		// http.authorizeRequests().antMatchers("/login**").permitAll();
		// //.anyRequest().authenticated();
		http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
	}

	@Override
	public void configure(AuthenticationManagerBuilder auth) throws Exception {
		DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
		provider.setUserDetailsService((username) -> {
			System.out.println("get user " + username);
			return userRepository.findByUsername(username).toUserDetails();
		});
		auth.authenticationProvider(provider);
	}

	// provider.setUserDetailsService(new UserDetailsService() {
	// @Override
	// public UserDetails loadUserByUsername(String username) throws
	// UsernameNotFoundException {
	// return userRepository.findByName(username).toUserDetails();
	// }
	// });
}
