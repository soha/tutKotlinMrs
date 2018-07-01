package mrs.domain.service.user

import mrs.domain.model.User
import mrs.domain.repository.user.UserRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.security.core.userdetails.UsernameNotFoundException
import org.springframework.stereotype.Service
import javax.lang.model.type.NullType

@Service
class ReservationUserDetailsService : UserDetailsService {
    @Autowired
    lateinit var userRepository: UserRepository

    @Throws(UsernameNotFoundException::class)
    override fun loadUserByUsername(username: String): UserDetails? {
        val user = userRepository.findById(username)
        if (!user.isPresent) throw UsernameNotFoundException("$username is not found.")
        return ReservationUserDetails(user.get())
    }
}