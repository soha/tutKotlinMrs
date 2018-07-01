package mrs.app.login

import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.RequestMapping

@Controller
class LoginController {
    @RequestMapping("loginForm")
    internal fun loginForm(): String {
        return "login/loginForm"
    }
}