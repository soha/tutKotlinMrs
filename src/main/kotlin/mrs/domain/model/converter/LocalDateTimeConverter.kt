package mrs.domain.model.converter

import java.sql.Timestamp
import java.time.LocalDateTime
import javax.persistence.AttributeConverter
import javax.persistence.Converter

@Converter(autoApply = true)
class LocalDateTimeConverter : AttributeConverter<LocalDateTime, Timestamp> {
    override fun convertToDatabaseColumn(dateTime: LocalDateTime?): Timestamp? {
        return if (dateTime == null) null else Timestamp.valueOf(dateTime)
    }

    override fun convertToEntityAttribute(value: Timestamp?): LocalDateTime? {
        return value?.toLocalDateTime()
    }
}