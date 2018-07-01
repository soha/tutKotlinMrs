package mrs.domain.repository.room

import java.time.LocalDate

import javax.persistence.LockModeType

import mrs.domain.model.ReservableRoom
import mrs.domain.model.ReservableRoomId
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Lock
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.query.Param
import java.util.*

interface ReservableRoomRepository : JpaRepository<ReservableRoom, ReservableRoomId> {
    @Query("SELECT DISTINCT x FROM ReservableRoom x LEFT JOIN FETCH x.meetingRoom WHERE x.reservableRoomId.reservedDate = :date ORDER BY x.reservableRoomId.roomId ASC")
    fun findByReservableRoomIdReservedDateOrderByReservableRoomIdRoomIdAsc(@Param("date") reservedDate: LocalDate): List<ReservableRoom>

    @Lock(LockModeType.PESSIMISTIC_WRITE)
    fun findOneForUpdateByReservableRoomId(reservableRoomId: ReservableRoomId): Optional<ReservableRoom>
}