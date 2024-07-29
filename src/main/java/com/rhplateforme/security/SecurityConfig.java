package com.rhplateforme.security;

import java.util.Arrays;
import java.util.Collections;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.BeanIds;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;

import com.rhplateforme.security.JWTAuthenticationFilter;
import com.rhplateforme.service.*;

import jakarta.servlet.http.HttpServletRequest;

@Configuration
@EnableWebSecurity

@CrossOrigin(origins = "https://www.tie-job.com")
//@CrossOrigin(origins = "http://localhost:4200")
public class SecurityConfig {
	
	@Autowired
 	UserDetailsService userDetailsService;
 	
 	@Autowired
	BCryptPasswordEncoder bCryptPasswordEncoder;
 	
 	@Autowired
 	AuthenticationManager authMgr;
	
	
 	@Bean
	public AuthenticationManager authManager(HttpSecurity http, 
			BCryptPasswordEncoder bCryptPasswordEncoder, 
			UserDetailsService userDetailsService) 
	  throws Exception {
	    return http.getSharedObject(AuthenticationManagerBuilder.class)
	      .userDetailsService(userDetailsService)
	      .passwordEncoder(bCryptPasswordEncoder)
	      
	      .and()
	      .build();
	}
 	
 	 @Bean
     public SecurityFilterChain filterChain(HttpSecurity http) throws Exception { 
 		 
		    http.csrf().disable()
		    .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS).and()
		    .cors().configurationSource(new CorsConfigurationSource() {
		    	 @Override
		    	 public CorsConfiguration getCorsConfiguration(HttpServletRequest
		    	request) {
		    	 CorsConfiguration config = new CorsConfiguration();

		    	 config.setAllowedOrigins(Collections.singletonList("*"));
		    	 config.setAllowedMethods(Collections.singletonList("*"));
		    	 //config.setAllowCredentials(true);
		    	 config.setAllowedHeaders(Collections.singletonList("*"));
		    	 config.setExposedHeaders(Arrays.asList("Authorization"));
		    	 config.setMaxAge(3600L);
		    	 return config;
		    	 }
		    	 }).and()
		                        .authorizeHttpRequests()
		                        .requestMatchers("/login").permitAll()
		                        .requestMatchers("/auth/exists/**").permitAll()
		                        .requestMatchers("/auth/getuserbyemail/*").permitAll()
		                        .requestMatchers("/auth/verifieremail").permitAll()
		                        .requestMatchers("/auth/addUser").permitAll()
		                        .requestMatchers("/auth/addUserentr").permitAll()
		                        .requestMatchers("/auth/addimagee/*").permitAll()
		                        .requestMatchers("/auth/addcv/*").permitAll()
		                        .requestMatchers("/auth/updateuser").permitAll()
		                        .requestMatchers("/auth/updatepack").hasAnyAuthority("ADMIN")
		                        .requestMatchers("/auth/updatefile/*").permitAll()
		                        .requestMatchers("/auth/addoffre/*/*").hasAnyAuthority("ENTR")
		                        .requestMatchers("/auth/getoffre/*").permitAll()
		                        .requestMatchers("/auth/getseuloffre/*").permitAll()
		                        .requestMatchers("/auth/getoffreempr/*").hasAnyAuthority("ENTR")
		                        .requestMatchers("/auth/deleteoffre/*").hasAnyAuthority("ENTR")
		                       .requestMatchers("/auth/rechercheemployeeGold").permitAll()
		                        .requestMatchers("/auth/rechercheemployeesuperieur").hasAnyAuthority("ENTR")
		                        .requestMatchers("/auth/rechercheemployeerestaurer").hasAnyAuthority("ENTR")
		                        .requestMatchers("/auth/rechercheemployeeServir").permitAll()
		                        .requestMatchers("/auth/getsp").permitAll()
		                        .requestMatchers("/auth/rechercheemployeeGold_r").hasAnyAuthority("ENTR")
		                        .requestMatchers("/auth/rechercheemployeeSuperieur_r").hasAnyAuthority("ENTR")
		                        .requestMatchers("/auth/rechercheemployeeRestaurer_r").hasAnyAuthority("ENTR")
		                        .requestMatchers("/auth/rechercheemployeeServir_r").hasAnyAuthority("ENTR")
		                        .requestMatchers("/auth/allemployee").permitAll()
		                        .requestMatchers("/auth/getuser/*").permitAll()
		                        .requestMatchers("/users/all").hasAnyAuthority("admin")
		                        .anyRequest().authenticated().and()
		                        .addFilterBefore(new JWTAuthenticationFilter (authMgr),UsernamePasswordAuthenticationFilter.class)
		                        .addFilterBefore(new JWTAuthorizationFilter(),UsernamePasswordAuthenticationFilter.class);
		    
		    					
		 return http.build();
		 
	}
}