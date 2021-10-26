package toy.vote.main.service

import org.json.JSONObject
import org.springframework.stereotype.Service
import toy.vote.main.enumclass.UserStatus
import toy.vote.main.exception.UserException
import toy.vote.main.wrapper.User
import java.net.URI
import java.net.http.HttpClient
import java.net.http.HttpRequest
import java.net.http.HttpResponse
import java.time.Duration

@Service
class UserServiceImpl :UserService {

    override fun selectUserByJwt(jwt: String): User? {
        try {
            val client = HttpClient.newBuilder().build()
            val request = HttpRequest.newBuilder()
                .uri(URI.create("http://localhost:8081/auth/user"))
                .GET()
                .timeout(Duration.ofSeconds(10))
                .header("jwt", jwt)
                .build()
            val response = client.send(request, HttpResponse.BodyHandlers.ofString())
            if(response.statusCode() == 200){
                return User(JSONObject(response.body()).getString("ndi"),
                    JSONObject(response.body()).getString("email"),
                    JSONObject(response.body()).getString("password"),
                    UserStatus.valueOf(JSONObject(response.body()).getString("status")),
                    JSONObject(response.body()).getString("name")
                    )
            } else{
                throw UserException.NullUserException()
            }
        } catch (e: Exception){
          throw UserException.NullUserException()
        }
    }
}
