package profesorp.oauth2;

import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@EnableResourceServer
@RestController
public class ResourceServerConfiguration extends ResourceServerConfigurerAdapter {
	@RequestMapping("/public")
	public String publico() {
		return "Public Page";
	}

	@RequestMapping("/private")
	public String privada() {
		return "Private Page";
	}

	@RequestMapping("/admin")
	public String admin() {
		return "Admin Page";
	}

	@Override
	public void configure(HttpSecurity http) throws Exception {
		http.authorizeRequests().antMatchers("/oauth/token", "/oauth/authorize**", "/public").permitAll();
//			 .anyRequest().authenticated();
		http.requestMatchers().antMatchers("/private").and().authorizeRequests().antMatchers("/private")
				.access("hasRole('USER')").and().requestMatchers().antMatchers("/admin").and().authorizeRequests()
				.antMatchers("/admin").access("hasRole('ADMIN')");
	}

}
