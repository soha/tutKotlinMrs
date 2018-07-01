package mrs.domain.model

import javax.persistence.*

@Entity
@Table(name = "usr")
data class User (
    @Id
    var userId: String = "",
    var password: String = "",
    var firstName: String = "",
    var lastName: String = "",
    @Enumerated(EnumType.STRING)
    var roleName: RoleName = RoleName.USER
)