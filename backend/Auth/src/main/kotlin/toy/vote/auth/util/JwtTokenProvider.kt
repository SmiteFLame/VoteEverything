package toy.vote.auth.util

import io.jsonwebtoken.Claims
import io.jsonwebtoken.Jwts
import io.jsonwebtoken.SignatureAlgorithm
import java.util.*

class JwtTokenProvider {
    companion object {
        private const val authKey = "TOY_PROJECT"
        fun getToken(key: String): String {
            return Jwts.builder()
                .setIssuer(key)
                .setExpiration(Date(System.currentTimeMillis() + 60 * 24 * 1000)) // 1day
                .signWith(SignatureAlgorithm.HS512, authKey).compact()
        }

        fun checkToken(token: String): String {
            return Jwts.parser().setSigningKey(authKey).parseClaimsJws(token).body.issuer
        }
    }
}