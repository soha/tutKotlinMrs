package mrs.domain.model.converter

import java.sql.Date
import java.time.LocalDate
import javax.persistence.AttributeConverter
import javax.persistence.Converter

@Converter(autoApply = true)
class LocalDateConverter : AttributeConverter<LocalDate, Date> {
    override fun convertToDatabaseColumn(date: LocalDate?): Date? {
        return if (date == null) null else Date.valueOf(date)
    }

    override fun convertToEntityAttribute(value: Date?): LocalDate? {
        return value?.toLocalDate()
    }
}