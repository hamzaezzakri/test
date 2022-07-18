package ma.formations.configuration;

import ma.formations.jwt.AuthEntryPointJwt;
import ma.formations.jwt.AuthorizationTokenFilter;
import ma.formations.jwt.JwtUtils;
import ma.formations.oauth2.OAuth2LoginSuccessHandler;
import ma.formations.oauth2.OAuth2UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

/**
 * @author Hamza Ezzakri
 * @CreatedAt 6/3/2022 6:42 PM
 */

@Configuration
@EnableWebSecurity
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

    @Autowired
    private UserDetailsService userDetailsService;
    @Autowired
    private AuthEntryPointJwt unauthorizedHnadler;
    @Autowired
    private AuthorizationTokenFilter authorizationTokenFilter;
    @Autowired
    private JwtUtils jwtUtils;
    @Autowired private OAuth2LoginSuccessHandler oauth2LoginSuccessHandler;
    @Autowired private OAuth2UserServiceImpl oAuth2UserService;

    @Bean
    public PasswordEncoder passwordEncoder(){

        return new BCryptPasswordEncoder();
    }

    @Bean
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {

        auth.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder());
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {

        http
                //.cors()
                //.and()
                //.csrf().disable()
                //.exceptionHandling().authenticationEntryPoint(unauthorizedHnadler)
                //.and()
                //.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.NEVER)
                //.and()
                .authorizeRequests().antMatchers("/auth/**","/login","/","/oauth2/**").permitAll()
                .antMatchers("/employees/**").hasAnyAuthority("ADMIN","CLIENT")
                .antMatchers("/articles/**").hasAnyAuthority("ADMIN","CLIENT")
                .antMatchers("/categories/**").hasAnyAuthority("ADMIN","CLIENT")
                .antMatchers("/formations/**").hasAnyAuthority("ADMIN","CLIENT")
                .antMatchers("/admin/**").hasAuthority("ADMIN")
                .antMatchers("/client/**").hasAuthority("CLIENT")
                .anyRequest().authenticated()
                .and()
                .formLogin()
                    //.loginPage("/login")
                    //.defaultSuccessUrl("/")
                .and()
                .oauth2Login()
                    //.loginPage("/login")
                    .userInfoEndpoint().userService(oAuth2UserService)
                    .and()
                    .successHandler(oauth2LoginSuccessHandler);
                //.and()
                //.addFilterBefore(authorizationTokenFilter, UsernamePasswordAuthenticationFilter.class)
                //.addFilter(new AuthenticationTokenFilter(authenticationManager(),jwtUtils));
    }

    @Override
    public void configure(WebSecurity web) throws Exception {

        web.ignoring().antMatchers("/resources/**","/static/**","/css/**","/js/**","/images/**");
    }
}
