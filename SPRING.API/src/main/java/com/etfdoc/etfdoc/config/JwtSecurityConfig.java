package com.etfdoc.etfdoc.config;

import com.etfdoc.etfdoc.Filters.JWTAuthenticationFilter;
import com.etfdoc.etfdoc.Filters.JWTLoginFilter;
import com.etfdoc.etfdoc.Services.CustomUserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@EnableGlobalMethodSecurity(prePostEnabled = true)
@EnableWebSecurity
@Configuration
public class JwtSecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private CustomUserDetailsService userDetailsService;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().disable().authorizeRequests()
                .antMatchers("/account/createrole").permitAll()
                .antMatchers("/account/create").permitAll()
                .antMatchers("/account/getbyemail").permitAll()
                .antMatchers("/account/update").permitAll()
                .antMatchers("/document/create").permitAll()
                .antMatchers("/privileges/create").permitAll()
                .antMatchers("/privileges/getByAccount").permitAll()
                .antMatchers("/privileges/getByDocument").permitAll()
                .antMatchers("/privileges/getByAccountAndDocument").permitAll()
                .antMatchers("/document/public").permitAll()
                .antMatchers("/document/private").permitAll()
                .antMatchers("/document/delete").permitAll()
                .antMatchers("/document/upload/*").permitAll()
                .antMatchers("/document/upload").permitAll()
                .antMatchers("/document/download").permitAll()
                .antMatchers("/document/getByKeywordAndCollaborator").permitAll()
                .antMatchers(HttpMethod.POST,"/login").permitAll()
                .anyRequest().authenticated()
                .and()
                // We filter the api/login requests
                .addFilterBefore(new JWTLoginFilter("/login", authenticationManager()),
                        UsernamePasswordAuthenticationFilter.class)
                // And filter other requests to check the presence of JWT in header
                .addFilterBefore(new JWTAuthenticationFilter(),
                        UsernamePasswordAuthenticationFilter.class);


    }

    @Override
    public void configure(WebSecurity web) throws Exception {
        web.ignoring().antMatchers(HttpMethod.OPTIONS, "/**");
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService);
    }
}
