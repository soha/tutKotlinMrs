package mrs.domain.model

import java.io.Serializable
import javax.persistence.*

@Entity
data class ReservableRoom (
    @EmbeddedId
    var reservableRoomId: ReservableRoomId = ReservableRoomId(),

    @ManyToOne
    @JoinColumn(name = "room_id", insertable = false, updatable = false)
    @MapsId("roomId")
    var meetingRoom: MeetingRoom = MeetingRoom()
)