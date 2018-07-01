package mrs.domain.service.reservation

import mrs.domain.model.ReservableRoom
import mrs.domain.model.ReservableRoomId
import mrs.domain.model.Reservation
import mrs.domain.repository.reservation.ReservationRepository
import mrs.domain.repository.room.ReservableRoomRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.security.access.method.P
import org.springframework.security.access.prepost.PreAuthorize
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import java.util.*

@Service
@Transactional
class ReservationService {
    @Autowired
    lateinit var reservationRepository: ReservationRepository
    @Autowired
    lateinit var reservableRoomRepository: ReservableRoomRepository

    fun findReservations(reservableRoomId: ReservableRoomId): List<Reservation> {
        return reservationRepository.findByReservableRoomReservableRoomIdOrderByStartTimeAsc(reservableRoomId)
    }

    fun reserve(reservation: Reservation): Reservation {
        val reservableRoomId = reservation.reservableRoom.reservableRoomId
        // 悲観ロック
        val reservable = reservableRoomRepository.findOneForUpdateByReservableRoomId(reservableRoomId)
        if (!reservable.isPresent)  throw UnavailableReservationException("入力の日付・部屋の組み合わせは予約できません。")

        // 重複チェック
        val overlap = reservationRepository.findByReservableRoomReservableRoomIdOrderByStartTimeAsc(reservableRoomId).stream()
                .anyMatch { x -> x.overlap(reservation) }
        if (overlap) {
            throw AlreadyReservedException("入力の時間帯はすでに予約済みです。")
        }
        // 予約情報の登録
        reservationRepository.save(reservation)
        return reservation
    }

    @PreAuthorize("hasRole('ADMIN') or #reservation.user.userId == principal.user.userId")
    fun cancel(@P("reservation") reservation: Reservation) {
        reservationRepository.delete(reservation)
    }

    fun findOne(reservationId: Int): Optional<Reservation> {
        return reservationRepository.findById(reservationId)
    }
}