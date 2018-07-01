package mrs.domain.model.converter

import java.sql.Time
import java.time.LocalTime
import javax.persistence.AttributeConverter
import javax.persistence.Converter

@Converter(autoApply = true)
class LocalTimeConverter : AttributeConverter<LocalTime, Time> {
    override fun convertToDatabaseColumn(time: LocalTime?): Time? {
        return if (time == null) null else Time.valueOf(time)
    }

    override fun convertToEntityAttribute(value: Time?): LocalTime? {
        return value?.toLocalTime()
    }
}