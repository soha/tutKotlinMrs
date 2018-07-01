package mrs.domain.model

import java.io.Serializable
import java.time.LocalDate
import javax.persistence.Embeddable

@Embeddable
class ReservableRoomId : Serializable {
    var roomId: Int = 0;
    var reservedDate: LocalDate = LocalDate.now();

    constructor(roomId : Int, reservedDate : LocalDate) {
        this.roomId = roomId
        this.reservedDate = reservedDate
    }
    constructor()
}