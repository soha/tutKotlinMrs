package mrs.app.reservation

import java.io.Serializable
import java.time.LocalTime
import javax.validation.constraints.NotNull
import org.springframework.format.annotation.DateTimeFormat

@EndTimeMustBeAfterStartTime(message = "終了時刻は開始時刻より後にしてください")
class ReservationForm : Serializable {
    @NotNull(message = "必須です")
    @ThirtyMinutesUnit(message = "30分単位で入力してください")
    @DateTimeFormat(pattern = "HH:mm")
    lateinit var startTime: LocalTime

    @NotNull(message = "必須です")
    @ThirtyMinutesUnit(message = "30分単位で入力してください")
    @DateTimeFormat(pattern = "HH:mm")
    lateinit var endTime: LocalTime
}