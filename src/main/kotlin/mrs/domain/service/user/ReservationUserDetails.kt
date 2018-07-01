package mrs.domain.service.user

import mrs.domain.model.User
import org.springframework.security.core.GrantedAuthority
import org.springframework.security.core.authority.AuthorityUtils
import org.springframework.security.core.userdetails.UserDetails
import java.util.*

class ReservationUserDetails(val user: User) : UserDetails {

    override fun getAuthorities(): Collection<GrantedAuthority> {
        return AuthorityUtils.createAuthorityList("ROLE_" + this.user.roleName!!.name)
    }

    override fun getPassword(): String? {
        return this.user.password
    }

    override fun getUsername(): String? {
        return this.user.userId
    }

    override fun isAccountNonExpired(): Boolean {
        return true
    }

    override fun isAccountNonLocked(): Boolean {
        return true
    }

    override fun isCredentialsNonExpired(): Boolean {
        return true
    }

    override fun isEnabled(): Boolean {
        return true
    }
}