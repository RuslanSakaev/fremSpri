package ru.sakaev.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        // Настраиваем аутентификацию пользователей
        auth.inMemoryAuthentication()
                .withUser("user").password("{noop}password").roles("USER")
                .and()
                .withUser("admin").password("{noop}password").roles("ADMIN");
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        // Настраиваем авторизацию запросов
        http.authorizeRequests()
                .antMatchers("/private-data").hasRole("ADMIN") // Доступ только для ADMIN
                .antMatchers("/public-data").authenticated()   // Доступ для всех аутентифицированных пользователей
                .and()
                .formLogin().loginPage("/login").permitAll()   // Настраиваем форму входа
                .and()
                .logout().logoutSuccessUrl("/login");          // Настраиваем выход из системы
    }
}
