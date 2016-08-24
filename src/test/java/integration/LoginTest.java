package integration;

import org.junit.Assert;
import org.junit.Test;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import net.odyne.petrinet.entities.AuthToken;

public class LoginTest {

	private static String URL = "http://localhost:8090";

	RestTemplate rest = new RestTemplate();

	@Test
	public void test1() {
		ResponseEntity<AuthToken> res = login("rene", "scorp");
		Assert.assertEquals(200, res.getStatusCodeValue());
		Assert.assertEquals("schoenfelder2211@gmail.com", res.getBody().getUser().getEmail());
	}

	private ResponseEntity<AuthToken> login(String username, String password) {
		String url = URL + "/login?username=" + username + "&password=" + password;
		return rest.exchange(url, HttpMethod.GET, new HttpEntity<String>(""), AuthToken.class);
	}

	// HttpHeaders headers = new HttpHeaders();
	// headers.setContentType(MediaType.APPLICATION_JSON);
	// HttpEntity<String> requestEntity = new HttpEntity<String>("", headers);

}
