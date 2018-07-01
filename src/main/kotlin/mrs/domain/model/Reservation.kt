package mrs.domain.model

import java.io.Serializable
import java.time.LocalTime
import java.util.Objects

import javax.persistence.*

@Entity
data class Reservation(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var reservationId: Int = 0,
    var startTime: LocalTime = LocalTime.now(),
    var endTime: LocalTime = LocalTime.now(),

    @ManyToOne
    @JoinColumns(JoinColumn(name = "reserved_date"), JoinColumn(name = "room_id"))
    var reservableRoom: ReservableRoom = ReservableRoom(),

    @ManyToOne
    @JoinColumn(name = "user_id")
    var user: User = User()
) {
    fun overlap(target: Reservation): Boolean {
        if (reservableRoom.reservableRoomId != target.reservableRoom.reservableRoomId) {
            return false
        }
        return if (startTime == target.startTime && endTime == target.endTime) {
            true
        } else target.endTime.isAfter(startTime) && endTime.isAfter(target.startTime)
    }
}