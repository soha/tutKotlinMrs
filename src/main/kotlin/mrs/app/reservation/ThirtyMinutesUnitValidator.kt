package mrs.app.reservation

import javax.validation.ConstraintValidator
import javax.validation.ConstraintValidatorContext
import java.time.LocalTime

class ThirtyMinutesUnitValidator : ConstraintValidator<ThirtyMinutesUnit, LocalTime> {
    override fun initialize(constraintAnnotation: ThirtyMinutesUnit?) {}

    override fun isValid(value: LocalTime?, context: ConstraintValidatorContext): Boolean {
        return if (value == null) {
            true
        } else value.minute % 30 == 0
    }
}