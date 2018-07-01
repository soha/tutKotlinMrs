package mrs.app.reservation

import javax.validation.ConstraintValidator
import javax.validation.ConstraintValidatorContext

class EndTimeMustBeAfterStartTimeValidator : ConstraintValidator<EndTimeMustBeAfterStartTime, ReservationForm> {
    private var message: String = ""

    override fun initialize(constraintAnnotation: EndTimeMustBeAfterStartTime) {
        message = constraintAnnotation.message
    }

    override fun isValid(value: ReservationForm, context: ConstraintValidatorContext): Boolean {
        if (value.startTime == null || value.endTime == null) {
            return true
        }
        val isEndTimeMustBeAfterStartTime = value.endTime.isAfter(value.startTime)
        if (!isEndTimeMustBeAfterStartTime) {
            context.disableDefaultConstraintViolation()
            context.buildConstraintViolationWithTemplate(message).addPropertyNode("endTime").addConstraintViolation()
        }
        return isEndTimeMustBeAfterStartTime
    }
}