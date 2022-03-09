package com.lessayer.common.config;

/*
 * import org.springframework.context.annotation.Bean; import
 * org.springframework.security.config.annotation.web.builders.HttpSecurity;
 * import
 * org.springframework.security.config.annotation.web.builders.WebSecurity;
 * import org.springframework.security.config.annotation.web.configuration.
 * EnableWebSecurity; import
 * org.springframework.security.config.annotation.web.configuration.
 * WebSecurityConfigurerAdapter; import
 * org.springframework.security.core.userdetails.User; import
 * org.springframework.security.core.userdetails.UserDetails; import
 * org.springframework.security.core.userdetails.UserDetailsService; import
 * org.springframework.security.crypto.factory.PasswordEncoderFactories; import
 * org.springframework.security.crypto.password.PasswordEncoder; import
 * org.springframework.security.provisioning.InMemoryUserDetailsManager;
 */
/*
 * @EnableWebSecurity public class SecurityConfig extends
 * WebSecurityConfigurerAdapter {
 * 
 * private final PasswordEncoder passwordEncoder =
 * PasswordEncoderFactories.createDelegatingPasswordEncoder();
 * 
 * @Bean UserDetailsService authentication() {
 * 
 * UserDetails user = User.builder() .username("kevin")
 * .password(passwordEncoder.encode("123")) .roles("ADMIN") .build(); return new
 * InMemoryUserDetailsManager(user);
 * 
 * }
 * 
 * @Override protected void configure(HttpSecurity http) throws Exception {
 * 
 * http.authorizeRequests() .mvcMatchers("/").hasRole("ADMIN")
 * .anyRequest().authenticated() .and() .formLogin() .and() .httpBasic();
 * 
 * }
 * 
 * }
 */