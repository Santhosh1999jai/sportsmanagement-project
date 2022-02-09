package com.example.demo.sercurity.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.example.demo.security.AuthEntryPointJwt;
import com.example.demo.security.AuthTokenFilter;
import com.example.demo.security.iml.UserDetailsServiceImpl;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)



public class SecurtiyConfigured extends WebSecurityConfigurerAdapter {
	@Autowired
	UserDetailsServiceImpl userDetailsService;

	@Autowired
	private AuthEntryPointJwt unauthorizedHandler;

	@Override
	@Bean
	protected AuthenticationManager authenticationManager() throws Exception {
		// TODO Auto-generated method stub
		return super.authenticationManager();
	}

	@Bean
	public AuthTokenFilter authenticationJwtTokenFilter() {
		return new AuthTokenFilter();
	}

	@Override
	public void configure(AuthenticationManagerBuilder authenticationManagerBuilder) throws Exception {
		authenticationManagerBuilder.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder());
	}

	
	@Override
	@Bean
	public AuthenticationManager authenticationManagerBean() throws Exception {
		return super.authenticationManagerBean();
	}

	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

	

//	@Override
//	protected void configure(HttpSecurity http) throws Exception {
//		http.csrf().disable()
//		.authorizeRequests().antMatchers("/authenticate","/register").permitAll().anyRequest().authenticated()
//		.and().	exceptionHandling().authenticationEntryPoint(jwtAuthenticationEntryPoint).and().sessionManagement().
//		sessionCreationPolicy(SessionCreationPolicy.STATELESS);
//		http.addFilterBefore(jwtRequestFilter, UsernamePasswordAuthenticationFilter.class);
//	}
	
	protected void configure(HttpSecurity http) throws Exception {
		http.cors().and().csrf().disable().exceptionHandling().authenticationEntryPoint(unauthorizedHandler).and()
				.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS).and().authorizeRequests()
				.antMatchers("/api/test/**").permitAll().antMatchers("/api/auth/**").permitAll().antMatchers("/api/auth/reader/**").permitAll()
				.antMatchers("/actuator/**").permitAll().antMatchers("/swagger-ui/**").permitAll().antMatchers("/webjars/**").permitAll()
				.antMatchers("/swagger-resources/**").permitAll().antMatchers("/v2/**").permitAll()
				.antMatchers("/entrypoint/**").permitAll()
				.antMatchers("/payment/**").permitAll()
				.antMatchers("/getresult/**").permitAll()
				.antMatchers("/entrypoint/**").permitAll()
				//.antMatchers("/student/paymentsection/**").permitAll()
				
		        // permit all request with the following list of roles
		        // methods will enforce their own authorization logic
		        .antMatchers("/v1/members/")
		        //.hasAnyAuthority("ROLE_ADMIN", "ROLE_EMPLOYEE")

				//.anyRequest()
				.authenticated();
		

		http.addFilterBefore(authenticationJwtTokenFilter(), UsernamePasswordAuthenticationFilter.class);
	}

}
