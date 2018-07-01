package mrs.domain.repository.user

import org.springframework.data.jpa.repository.JpaRepository
import mrs.domain.model.User

interface UserRepository : JpaRepository<User, String>