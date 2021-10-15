package toy.vote.main.controller

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.RestController
import toy.vote.main.service.UserService

@RestController
class UserController {

    @Autowired
    lateinit var userService: UserService
}
