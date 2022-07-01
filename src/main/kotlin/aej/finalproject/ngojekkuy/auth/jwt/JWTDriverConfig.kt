package aej.finalproject.ngojekkuy.auth.jwt

import aej.finalproject.ngojekkuy.auth.authentication.AuthenticationFilter
import aej.finalproject.ngojekkuy.global.Constant
import aej.finalproject.ngojekkuy.model.driver.DriverDatabase
import io.jsonwebtoken.Jwts
import io.jsonwebtoken.SignatureAlgorithm
import io.jsonwebtoken.security.Keys
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.context.annotation.Configuration
import org.springframework.http.HttpMethod
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter
import org.springframework.security.config.http.SessionCreationPolicy
import org.springframework.security.core.authority.AuthorityUtils
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter
import java.util.*
import java.util.stream.Collectors
import javax.servlet.http.HttpServletRequest

@EnableWebSecurity
@Configuration
class JWTDriverConfig: WebSecurityConfigurerAdapter() {

    @Autowired
    private lateinit var authenticationFilter: AuthenticationFilter

    override fun configure(http: HttpSecurity) {
        http.sessionManagement()
            .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
            .and()
            .csrf().disable()
            .addFilterAt(authenticationFilter, UsernamePasswordAuthenticationFilter::class.java)
            .authorizeRequests()
            .antMatchers(HttpMethod.POST, *postDriverPermit.toTypedArray()).permitAll()
            .antMatchers(HttpMethod.GET, *GetDriverPermit.toTypedArray()).permitAll()
            .anyRequest().authenticated()
    }

    companion object {
        val postDriverPermit = listOf<String>(
            "/driver/login",
            "/driver/register"
        )

        val GetDriverPermit = listOf<String>(
            "/driver/ping"
        )

        fun generateToken(driverDatabase: DriverDatabase): String {
            val subject = driverDatabase.id
            val expired = Date(System.currentTimeMillis() + (60_000 * 60 * 24))
            val granted = AuthorityUtils.commaSeparatedStringToAuthorityList(driverDatabase.username)
            val grantedStream = granted.stream().map { it.authority }.collect(Collectors.toList())

            return Jwts.builder()
                .setSubject(subject)
                .claim(Constant.CLAIMS, grantedStream)
                .setExpiration(expired)
                .signWith(Keys.hmacShaKeyFor(Constant.SECRETS.toByteArray()), SignatureAlgorithm.HS256)
                .compact()
        }

        fun isPermited(request: HttpServletRequest): Boolean {
            val path = request.servletPath
            println("hasilnya adalah " + postDriverPermit.contains(path))
                return postDriverPermit.contains(path)
        }
    }
}