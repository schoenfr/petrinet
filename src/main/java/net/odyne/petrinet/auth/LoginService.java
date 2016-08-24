package net.odyne.petrinet.auth;

import java.util.Calendar;
import java.util.Date;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import net.odyne.petrinet.entities.AuthToken;
import net.odyne.petrinet.entities.User;
import net.odyne.petrinet.repositories.AuthTokenRepository;
import net.odyne.petrinet.repositories.UserRepository;

@RestController
public class LoginService {

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private AuthTokenRepository authTokenRepository;

	@RequestMapping(value = "/login", method = RequestMethod.GET)
	public AuthToken login(@RequestParam(value = "username") String username,
			@RequestParam(value = "password") String password) {
		User user = userRepository.findByUsername(username);
		if (user == null || !user.getPassword().equals(password))
			throw new AccessDeniedException("yo no");

		AuthToken token = new AuthToken();
		token.setUser(user);
		token.setSalt(Long.toHexString(new Random().nextLong()));

		Calendar c = Calendar.getInstance();
		c.setTime(new Date());
		c.add(Calendar.DATE, 1);
		token.setExpire(c.getTime());
		
		authTokenRepository.save(token);
		return token;
	}

}
