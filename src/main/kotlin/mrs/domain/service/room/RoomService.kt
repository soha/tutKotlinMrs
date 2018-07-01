package mrs.domain.service.room

import java.time.LocalDate

import mrs.domain.model.MeetingRoom
import mrs.domain.model.ReservableRoom
import mrs.domain.repository.room.MeetingRoomRepository
import mrs.domain.repository.room.ReservableRoomRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import java.util.*

@Service
@Transactional
class RoomService {
    @Autowired
    lateinit var reservableRoomRepository: ReservableRoomRepository
    @Autowired
    lateinit var meetingRoomRepository: MeetingRoomRepository

    fun findReservableRooms(date: LocalDate): List<ReservableRoom> {
        return reservableRoomRepository.findByReservableRoomIdReservedDateOrderByReservableRoomIdRoomIdAsc(date)
    }

    fun findMeetingRoom(roomId: Int): Optional<MeetingRoom> {
        return meetingRoomRepository.findById(roomId)
    }
}