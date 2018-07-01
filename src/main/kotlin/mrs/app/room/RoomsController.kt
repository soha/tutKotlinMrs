package mrs.app.room

import java.time.LocalDate
import mrs.domain.model.ReservableRoom
import mrs.domain.service.room.RoomService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.format.annotation.DateTimeFormat
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestMethod

@Controller
@RequestMapping("rooms")
class RoomsController {
    @Autowired
    lateinit var roomService: RoomService

    @GetMapping("{date}")
    internal fun listRooms(@DateTimeFormat(iso = DateTimeFormat.ISO.DATE) @PathVariable("date") date: LocalDate, model: Model): String {
        val rooms = roomService.findReservableRooms(date)
        model.addAttribute("rooms", rooms)
        return "room/listRooms"
    }

    @GetMapping ()
    internal fun listRooms(model: Model): String {
        val today = LocalDate.now()
        model.addAttribute("date", today)
        return listRooms(today, model)
    }
}