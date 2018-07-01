package mrs.domain.repository.reservation

import mrs.domain.model.ReservableRoomId
import mrs.domain.model.Reservation
import org.springframework.data.jpa.repository.JpaRepository

interface ReservationRepository : JpaRepository<Reservation, Int> {
    fun findByReservableRoomReservableRoomIdOrderByStartTimeAsc(reservableRoomId: ReservableRoomId): List<Reservation>
}