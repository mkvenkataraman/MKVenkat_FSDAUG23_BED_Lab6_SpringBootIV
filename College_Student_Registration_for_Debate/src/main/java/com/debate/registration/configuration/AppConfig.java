package com.debate.registration.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import com.debate.registration.service.UserService;

@Configuration
public class AppConfig {

	UserService service;

	public AppConfig(UserService service) {
		super();
		this.service = service;
	}

	@Bean
	public static BCryptPasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

	@Bean
	public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration)
			throws Exception {
		return authenticationConfiguration.getAuthenticationManager();
	}

	@Bean
	public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {

		http.authorizeHttpRequests((configurer) -> configurer.requestMatchers("/registration/**").permitAll()
				.requestMatchers("/regSuccess/**").permitAll().requestMatchers("/").hasAnyRole("USER", "ADMIN")
				.requestMatchers("/students/list/").hasAnyRole("USER", "ADMIN")
				.requestMatchers("/student/showStudentFormForAdd/**").hasAnyRole("USER", "ADMIN")
				.requestMatchers("/students/saveStudent/").hasAnyRole("USER", "ADMIN")
				.requestMatchers("/students/delete/**").hasRole("ADMIN")
				.requestMatchers("/students/studentFormForEdit/**").hasRole("ADMIN").anyRequest().authenticated()

		).formLogin((form) -> form.loginPage("/login").loginProcessingUrl("/authenticateTheUser").permitAll()

		).logout(logout -> logout.logoutUrl("/logout") // The URL to trigger the logout
				.logoutSuccessUrl("/login") // URL to redirect to after logout
				.invalidateHttpSession(true) // Invalidate the session
				.deleteCookies("JSESSIONID") // Delete the session cookie
				.permitAll() // Allow access to all users
		).exceptionHandling(configurer -> configurer.accessDeniedPage("/access-denied"));
		return http.build();

	}
}
