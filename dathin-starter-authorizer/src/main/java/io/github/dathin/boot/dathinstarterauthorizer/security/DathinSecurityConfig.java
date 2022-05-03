package io.github.dathin.boot.dathinstarterauthorizer.security;

import org.slf4j.LoggerFactory;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.filter.OncePerRequestFilter;

@Configuration
@EnableWebSecurity
public class DathinSecurityConfig extends WebSecurityConfigurerAdapter {

	static {
		LoggerFactory.getLogger(DathinSecurityConfig.class).info("Initialized Dathin authorizer");
	}

	private final DathinSecurityFilter dathinSecurityFilter;

	private final SecurityFilterExceptionHandler securityFilterExceptionHandler;

	public DathinSecurityConfig(DathinSecurityFilter dathinSecurityFilter, SecurityFilterExceptionHandler securityFilterExceptionHandler) {
		this.dathinSecurityFilter = dathinSecurityFilter;
		this.securityFilterExceptionHandler = securityFilterExceptionHandler;
	}

	//TOLIB
	@Override
	public void configure(HttpSecurity http) throws Exception {
		var unAuthEndpoints = new String[] { "/swagger-ui/**", "/v3/**", "/swagger-resources/**", "/user",
				"/user/login", "/account" };

		http
				.cors()
				.and()
				.csrf()
				.disable()
				.sessionManagement()
				.sessionCreationPolicy(SessionCreationPolicy.STATELESS)
				.and()
				.authorizeRequests().antMatchers(unAuthEndpoints).permitAll().anyRequest().authenticated()
				.and()
				.addFilterBefore(dathinSecurityFilter, UsernamePasswordAuthenticationFilter.class).exceptionHandling()
				.authenticationEntryPoint(securityFilterExceptionHandler);
	}

}
