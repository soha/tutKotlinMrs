package mrs.app.reservation

import mrs.domain.service.reservation.ReservationService
import mrs.domain.service.room.RoomService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.format.annotation.DateTimeFormat
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.web.bind.annotation.*
import java.time.LocalDate
import java.time.LocalTime
import java.util.stream.Collectors.toList
import java.util.stream.Stream
import mrs.domain.model.Reservation
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RequestMapping
import mrs.domain.service.reservation.AlreadyReservedException
import mrs.domain.service.reservation.UnavailableReservationException
import mrs.domain.model.ReservableRoomId
import mrs.domain.model.ReservableRoom
import mrs.domain.service.user.ReservationUserDetails
import org.springframework.security.core.annotation.AuthenticationPrincipal
import org.springframework.validation.BindingResult
import org.springframework.validation.annotation.Validated



@Controller
@RequestMapping("reservations/{date}/{roomId}")
class ReservationsController {

    @Autowired
    lateinit var roomService: RoomService
    @Autowired
    lateinit var reservationService: ReservationService

    @ModelAttribute
    internal fun setUpForm(): ReservationForm {
        val form = ReservationForm()
        // デフォルト値
        form.startTime = LocalTime.of(9, 0)
        form.endTime = LocalTime.of(10, 0)
        return form
    }

    @GetMapping()
    internal fun reserveForm(@DateTimeFormat(iso = DateTimeFormat.ISO.DATE) @PathVariable("date") date: LocalDate,
                             @PathVariable("roomId") roomId: Int, model: Model): String {
        val reservableRoomId = ReservableRoomId(roomId, date)
        val reservations = reservationService.findReservations(reservableRoomId)
        val timeList = Stream.iterate(LocalTime.of(0, 0)) { t -> t.plusMinutes(30) }.limit((24 * 2).toLong()).collect(toList())
        model.addAttribute("room", roomService.findMeetingRoom(roomId).get())
        model.addAttribute("reservations", reservations)
        model.addAttribute("timeList", timeList)
        return "reservation/reserveForm"
    }

    @PostMapping()
    fun reserve(@Validated form: ReservationForm, bindingResult: BindingResult,
                @AuthenticationPrincipal userDetails: ReservationUserDetails,
                @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) @PathVariable("date") date: LocalDate,
                @PathVariable("roomId") roomId: Int, model: Model): String {
        if (bindingResult.hasErrors()) {
            return reserveForm(date, roomId, model)
        }
        val reservableRoom = ReservableRoom(ReservableRoomId(roomId, date))
        val reservation = Reservation()
        reservation.startTime = form.startTime
        reservation.endTime = form.endTime
        reservation.reservableRoom = reservableRoom
        reservation.user = userDetails.user
        try {
            reservationService.reserve(reservation)
        } catch (e: UnavailableReservationException) {
            model.addAttribute("error", e.message)
            return reserveForm(date, roomId, model)
        } catch (e: AlreadyReservedException) {
            model.addAttribute("error", e.message)
            return reserveForm(date, roomId, model)
        }

        return "redirect:/reservations/{date}/{roomId}"
    }

    @PostMapping(params = ["cancel"])
    fun cancel(@RequestParam("reservationId") reservationId: Int, @PathVariable("roomId") roomId: Int,
               @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) @PathVariable("date") date: LocalDate, model: Model): String {
        try {
            val reservation = reservationService.findOne(reservationId)
            if (reservation.isPresent) {
                reservationService.cancel(reservation.get())
            }
        } catch (e: AccessDeniedException) {
            model.addAttribute("error", e.message)
            return reserveForm(date, roomId, model)
        }

        return "redirect:/reservations/{date}/{roomId}"
    }
}