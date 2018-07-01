package mrs.domain.model

import java.io.Serializable
import javax.persistence.*

@Entity
data class MeetingRoom(
        @Id @GeneratedValue(strategy = GenerationType.IDENTITY) var roomId: Int = 0,
        var roomName: String = "")
