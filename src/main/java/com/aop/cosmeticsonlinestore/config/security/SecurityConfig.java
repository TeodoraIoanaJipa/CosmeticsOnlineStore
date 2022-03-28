package com.aop.cosmeticsonlinestore.config.security;

import com.aop.cosmeticsonlinestore.repository.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import javax.servlet.http.HttpServletResponse;

import static java.lang.String.format;

@EnableWebSecurity
@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    private Logger logger = LoggerFactory.getLogger(SecurityConfig.class);

    private final UserRepository userRepository;

    private final JwtTokenFilter jwtTokenFilter;

    public SecurityConfig(UserRepository userRepository,
                          JwtTokenFilter jwtTokenFilter) {
        this.userRepository = userRepository;
        this.jwtTokenFilter = jwtTokenFilter;
    }


    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
//        auth.inMemoryAuthentication()
//                .withUser("teodora").password(passwordEncoder().encode("teodora")).roles("ADMIN");
        auth.userDetailsService(username ->
                userRepository.findUserByUsername(username)
                        .orElseThrow(
                                () -> new UsernameNotFoundException(
                                        format("User: %s, not found", username)
                                )
                        ));
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {

        http = http.cors().and().csrf().disable();

        // Set session management to stateless
        http = http
                .sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and();

        // Set unauthorized requests exception handler
        http = http
                .exceptionHandling()
                .authenticationEntryPoint(
                        (request, response, ex) -> {
                            logger.error("Unauthorized request - {}", ex.getMessage());
                            response.sendError(HttpServletResponse.SC_UNAUTHORIZED, ex.getMessage());
                        }
                )
                .and()
                ;

        // Set permissions on endpoints
        http.authorizeRequests()

                .antMatchers("/").permitAll()
                // Our public endpoints
                .antMatchers("/").permitAll()
                .antMatchers(HttpMethod.GET, "/templates/home1/home/*").permitAll()
                .antMatchers(HttpMethod.GET, "/user/userLogged").permitAll()
                .antMatchers(HttpMethod.GET, "/product/*").permitAll()
                .antMatchers(HttpMethod.GET, "/contact").permitAll()
                .antMatchers( "/user/*").permitAll()
                .antMatchers(HttpMethod.GET, "/order").permitAll()
                .antMatchers(HttpMethod.GET, "/order/*").permitAll()
                .antMatchers(HttpMethod.POST, "/order").permitAll()
                // Our private endpoints
                .anyRequest().authenticated().and()
                ;

        // Add JWT token filter
        http.addFilterBefore(jwtTokenFilter, UsernamePasswordAuthenticationFilter.class);
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Override
    @Bean
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }

}
