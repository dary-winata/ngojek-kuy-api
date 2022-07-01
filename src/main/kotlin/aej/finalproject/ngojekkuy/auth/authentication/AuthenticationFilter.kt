package aej.finalproject.ngojekkuy.auth.authentication

import aej.finalproject.ngojekkuy.auth.jwt.JWTDriverConfig
import aej.finalproject.ngojekkuy.error.ErrorException
import aej.finalproject.ngojekkuy.global.Constant
import aej.finalproject.ngojekkuy.model.BaseResponse
import aej.finalproject.ngojekkuy.service.driver.DriverService
import io.jsonwebtoken.Claims
import io.jsonwebtoken.Jwts
import io.jsonwebtoken.UnsupportedJwtException
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.authority.SimpleGrantedAuthority
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.stereotype.Component
import org.springframework.web.filter.OncePerRequestFilter
import java.util.stream.Collectors
import javax.servlet.FilterChain
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse

@Component
class AuthenticationFilter: OncePerRequestFilter() {

    override fun doFilterInternal(
        request: HttpServletRequest,
        response: HttpServletResponse,
        filterChain: FilterChain
    ) {
        try {
            if(JWTDriverConfig.isPermited(request))
                filterChain.doFilter(request, response)
            else {
                val claims = validate(request)
                if(claims[Constant.CLAIMS] != null) {
                    setupAuthentication(claims) {
                        filterChain.doFilter(request, response)
                    }
                } else{
                    SecurityContextHolder.clearContext()
                    throw ErrorException("token is required")
                }
            }
        } catch (e: Exception){
            response.status = HttpServletResponse.SC_UNAUTHORIZED
            response.contentType = "application/json"

            when(e) {
                is UnsupportedJwtException -> throw UnsupportedJwtException(e.message?: "JWT Exception")
                else -> throw ErrorException(e.message ?: "token invalid")
            }
        }
    }

    private fun validate(request: HttpServletRequest): Claims {
        val jwsToken = request.getHeader("Authorization")
        return Jwts.parserBuilder()
            .setSigningKey(Constant.SECRETS.toByteArray())
            .build()
            .parseClaimsJws(jwsToken)
            .body
    }

    private fun setupAuthentication(claims: Claims, doOnNext: () -> Unit) {
        val authorities = claims[Constant.CLAIMS] as List<String>
        val authStream = authorities.stream().map { SimpleGrantedAuthority(it) }.collect(Collectors.toList())

        val auth = UsernamePasswordAuthenticationToken(claims.subject, null, authStream)
        SecurityContextHolder.getContext().authentication = auth
        doOnNext.invoke()
    }
}