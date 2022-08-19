package aej.finalproject.ngojekkuy.auth.jwt

import aej.finalproject.ngojekkuy.auth.authentication.AuthenticationFilter
import aej.finalproject.ngojekkuy.global.Constant
import aej.finalproject.ngojekkuy.user.model.driver.DriverDatabase
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
            .antMatchers(HttpMethod.GET, *getDriverPermitRole.toTypedArray()).hasAuthority("DRIVER")
            .antMatchers(HttpMethod.GET, *getCustomerPermitRole.toTypedArray()).hasAuthority("CUSTOMER")
            .antMatchers(HttpMethod.POST, *postDriverCustomerPermit.toTypedArray()).permitAll()
            .anyRequest().authenticated()
    }

    companion object {
        val postDriverCustomerPermit = listOf<String>(
            "/driver/login",
            "/driver/register",
            "/customer/register",
            "/customer/login"
        )

        val getPermit = listOf<String>(
            "/loc/route",
            "/loc/reverse"
        )

        val getDriverPermitRole = listOf<String>(
            "/driver/{\\d+}"
        )

        val getCustomerPermitRole = listOf<String>(
            "/customer/{\\d+}"
        )

        fun generateToken(id: String, role: String): String {
            val expired = Date(System.currentTimeMillis() + (60_000 * 60 * 24))
            val granted = AuthorityUtils.commaSeparatedStringToAuthorityList(role)
            val grantedStream = granted.stream().map { it.authority }.collect(Collectors.toList())

            return Jwts.builder()
                .setSubject(id)
                .claim(Constant.AUTH, grantedStream)
                .setExpiration(expired)
                .signWith(Keys.hmacShaKeyFor(Constant.SECRETS.toByteArray()), SignatureAlgorithm.HS256)
                .compact()
        }

        fun isPermited(request: HttpServletRequest): Boolean {
            val path = request.servletPath
            return postDriverCustomerPermit.contains(path)
        }
    }
}