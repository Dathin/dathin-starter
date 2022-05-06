package io.github.dathin.boot.dathinstarterauthorizer.security;

import org.slf4j.LoggerFactory;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
@EnableConfigurationProperties(DathinSecurityConfigProperties.class)
public class DathinSecurityConfig extends WebSecurityConfigurerAdapter {

	static {
		LoggerFactory.getLogger(DathinSecurityConfig.class).info("Initialized Dathin authorizer");
	}

	private final DathinSecurityFilter dathinSecurityFilter;

	private final SecurityFilterExceptionHandler securityFilterExceptionHandler;

	private final DathinSecurityConfigProperties dathinSecurityConfigProperties;

	public DathinSecurityConfig(DathinSecurityFilter dathinSecurityFilter, SecurityFilterExceptionHandler securityFilterExceptionHandler, DathinSecurityConfigProperties dathinSecurityConfigProperties) {
		this.dathinSecurityFilter = dathinSecurityFilter;
		this.securityFilterExceptionHandler = securityFilterExceptionHandler;
		this.dathinSecurityConfigProperties = dathinSecurityConfigProperties;
	}

	//TOLIB
	@Override
	public void configure(HttpSecurity http) throws Exception {
		http
				.cors()
				.and()
				.csrf()
				.disable()
				.sessionManagement()
				.sessionCreationPolicy(SessionCreationPolicy.STATELESS)
				.and()
				.authorizeRequests().antMatchers(mapUnAuthEndpoints()).permitAll().anyRequest().authenticated()
				.and()
				.addFilterBefore(dathinSecurityFilter, UsernamePasswordAuthenticationFilter.class).exceptionHandling()
				.authenticationEntryPoint(securityFilterExceptionHandler);
	}

	private String[] mapUnAuthEndpoints() {
		String[] swaggerPaths = {"/swagger-ui/**", "/v3/api-docs", "/v3/api-docs/swagger-config"};
		var publicPaths = dathinSecurityConfigProperties.getPublicPaths();

		if(!dathinSecurityConfigProperties.isDisableSwagger()) {
			return mergeArrays(swaggerPaths, publicPaths);
		}
		return publicPaths;
	}

	private String[] mergeArrays(String[] swaggerPaths, String[] publicPaths) {
		var swaggerLen = swaggerPaths.length;
		var publicLen = publicPaths.length;
		var twoArraysLen = swaggerLen + publicLen;
		var mergedArray = new String[twoArraysLen];
		System.arraycopy(swaggerPaths, 0, mergedArray, 0, swaggerLen);
		System.arraycopy(publicPaths, 0, mergedArray, swaggerLen, publicLen);
		return mergedArray;
	}

}
