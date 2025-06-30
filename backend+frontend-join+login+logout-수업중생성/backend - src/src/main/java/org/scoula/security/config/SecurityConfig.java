package org.scoula.security.config;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.mybatis.spring.annotation.MapperScan;
import org.scoula.security.filter.AuthenticationErrorFilter;
import org.scoula.security.filter.JwtAuthenticationFilter;
import org.scoula.security.filter.JwtUsernamePasswordAuthenticationFilter;
import org.scoula.security.handler.CustomAccessDeniedHandler;
import org.scoula.security.handler.CustomAuthenticationEntryPoint;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.csrf.CsrfFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CharacterEncodingFilter;
import org.springframework.web.filter.CorsFilter;

@Configuration
@EnableWebSecurity
@Log4j2
@MapperScan(basePackages = {"org.scoula.security.account.mapper"})
@ComponentScan(basePackages = {"org.scoula.security"})
@RequiredArgsConstructor

public class SecurityConfig extends WebSecurityConfigurerAdapter {

    private final UserDetailsService userDetailsService;
    private final JwtAuthenticationFilter jwtAuthenticationFilter;
    private final AuthenticationErrorFilter authenticationErrorFilter;
    private final CustomAccessDeniedHandler accessDeniedHandler;
    private final CustomAuthenticationEntryPoint authenticationEntryPoint;

    @Autowired
    private JwtUsernamePasswordAuthenticationFilter jwtUsernamePasswordAuthenticationFilter;

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    // 문자셋필터
    // post방식의 전달시 body에 들어있는 값 한글 인코딩 필터
    public CharacterEncodingFilter encodingFilter() {
        CharacterEncodingFilter encodingFilter = new CharacterEncodingFilter();
        encodingFilter.setEncoding("UTF-8");
        encodingFilter.setForceEncoding(true);
        return encodingFilter;
    }

    // AuthenticationManager 빈 등록
    @Bean
//    JWT 방식은 폼로그인과달리Spring Security의기 본인증필터를사용하지않고,
//    클라이언트→ JWT 토큰→ 커스텀필터
//    (OncePerRequestFilter 등) → SecurityContext 직접 설정
    public AuthenticationManager authenticationManager() throws Exception {
        return super.authenticationManager();

    }

    // cross origin 접근 허용
    @Bean
    public CorsFilter corsFilter() {
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        CorsConfiguration config = new CorsConfiguration();
        config.setAllowCredentials(true);
        config.addAllowedOriginPattern("*");
        config.addAllowedHeader("*");
        config.addAllowedMethod("*");
        source.registerCorsConfiguration("/**", config);
        return new CorsFilter(source);
    }

    // 접근 제한무시경로설정–resource
    @Override
    public void configure(WebSecurity web) throws Exception {
        web.ignoring().antMatchers("/assets/**", "/*", "/api/member/**",
                // Swagger 관련url은보안에서제외
                "/swagger-ui.html", "/webjars/**", "/swagger-resources/**", "/v2/api-docs"
        );
    }

    @Override
    public void configure(HttpSecurity http) throws Exception {
//        http
//                .addFilterBefore(encodingFilter(), CsrfFilter.class)
//                .addFilterBefore(authenticationErrorFilter, UsernamePasswordAuthenticationFilter.class)
//                .addFilterBefore(jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class)
//                .addFilterBefore(jwtUsernamePasswordAuthenticationFilter, UsernamePasswordAuthenticationFilter.class);

        http
                .addFilterBefore(encodingFilter(), CsrfFilter.class)  // 문자 인코딩 필터
                .addFilterBefore(jwtUsernamePasswordAuthenticationFilter, UsernamePasswordAuthenticationFilter.class) // ③ JWT 로그인 처리
                .addFilterBefore(jwtAuthenticationFilter, JwtUsernamePasswordAuthenticationFilter.class)              // ② 요청의 헤더에서 토큰 검사
                .addFilterBefore(authenticationErrorFilter, JwtAuthenticationFilter.class);                           // ① 만료된 토큰 예외 처리


        http.httpBasic().disable() // 기본 HTTP 인증비활성화
                .csrf().disable() // CSRF 비활성화
                .formLogin().disable()  // formLogin 비활성화- 관련 필터 해제
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS); // 세션 생성 모드 설정

        http
                .exceptionHandling()
                .authenticationEntryPoint(authenticationEntryPoint)
                .accessDeniedHandler(accessDeniedHandler);

        http
                .authorizeRequests()
                .antMatchers(HttpMethod.OPTIONS).permitAll()
                // 일단 모든 접근 허용
                .anyRequest().permitAll();
        
        // 경로별 접근권한설정
        // form-login기본 설정은 비활성화되어서 사라짐.
        // 권한이 없으면 403에러 화면이 뜸.
        // --> 이 에러화면보다는 로그인하는 페이지를 보여주는 것이 더 나을 것 같음.
//        http.authorizeRequests()
//                .antMatchers("/security/all").permitAll()
//                .antMatchers("/security/admin").access("hasRole('ROLE_ADMIN')")
//                .antMatchers("/security/member").access("hasAnyRole('ROLE_MEMBER', 'ROLE_ADMIN')");

        //http.formLogin();//form-login화면 다시 활성화
        //403에러가 발생했을 때 form-login화면으로 다시 redirect!

//        http
//                .authorizeRequests() // 경로별 접근 권한 설정
//                .antMatchers(HttpMethod.OPTIONS).permitAll()
//                .antMatchers("/api/security/all").permitAll()        // 모두 허용
//                .antMatchers("/api/security/member").access("hasRole('ROLE_MEMBER')")    // ROLE_MEMBER 이상 접근 허용
//                .antMatchers("/api/security/admin").access("hasRole('ROLE_ADMIN')")      // ROLE_ADMIN 이상 접근 허용
//                .anyRequest().authenticated();  // 나머지는 로그인 된 경우 모두 허용

        http.formLogin()
                .loginPage("/security/login")
                .loginProcessingUrl("/security/login")
                .defaultSuccessUrl("/");

        http.logout()
                .logoutUrl("/security/logout")
                .invalidateHttpSession(true)
                // 로그아웃설정시작
                // POST: 로그아웃 호출 url
                // 세션 invalidate
                .deleteCookies("remember-me", "JSESSION-ID") // 삭제할 쿠키 목록
                .logoutSuccessUrl("/security/logout");
        // GET: 로그아웃 이후이동할페이지
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth)
            throws Exception {
        log.info("configure .........................................");

        auth.userDetailsService(userDetailsService)
                .passwordEncoder(passwordEncoder());

      /*  auth.inMemoryAuthentication()
                .withUser("admin")
                // .password("{noop}1234")
                .password("$2a$10$EsIMfxbJ6NuvwX7MDj4WqOYFzLU9U/lddCyn0nic5dFo3VfJYrXYC")
                .roles("ADMIN", "MEMBER"); // ROLE_ADMIN
        auth.inMemoryAuthentication()
                .withUser("member")
                //.password("{noop}1234")
                .password("$2a$10$9RvLJCvVf2FiLn/w30mkduI8329Y8XN9wjfhBH7l5soIdEVVd4SxW")
                .roles("MEMBER");  // ROLE_MEMBER*/
        // ROLE_MEMBER
    }
}
