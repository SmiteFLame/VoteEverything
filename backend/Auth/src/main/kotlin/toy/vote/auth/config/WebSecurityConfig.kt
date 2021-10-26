package toy.vote.auth.config

import org.springframework.boot.autoconfigure.security.servlet.PathRequest
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.builders.WebSecurity
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter
import org.springframework.security.config.http.SessionCreationPolicy
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.security.crypto.password.PasswordEncoder

@Configuration
@EnableWebSecurity
class WebSecurityConfig : WebSecurityConfigurerAdapter() {

    @Bean
    fun getPasswordEncoder(): PasswordEncoder {
        return BCryptPasswordEncoder()
    }

    /**
     * 정적 자원에 대해서는 Security 설정을 적용하지 않는다
     */
    @Override
    override fun configure(web: WebSecurity?) {
        web?.ignoring()?.requestMatchers(PathRequest.toStaticResources().atCommonLocations())
    }

    @Override
    override fun configure(http: HttpSecurity) {
        // 비밀번호 관련
        http.cors().disable()
            .csrf().disable()
            .formLogin().disable()
            .headers().frameOptions().disable()
        http.csrf().disable().authorizeRequests()
            // 토큰을 활용하는 경우 모든 요청에 대해서 접근이 가능하도록
            .anyRequest().permitAll()
            .and()
            // 토큰을 활용하면 세션이 필요없으므로 STATELESS로 설정하여 Session을 사용하지 않는다
            .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
            .and()
            // form 기반의 로그인에 대해 비활성화 한다
            .formLogin()
            .disable()
    }
}
