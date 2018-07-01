package mrs.app.reservation

import javax.validation.Constraint
import javax.validation.Payload
import java.lang.annotation.Documented
import java.lang.annotation.Retention

import java.lang.annotation.ElementType.*
import java.lang.annotation.RetentionPolicy.RUNTIME
import kotlin.reflect.KClass

@Documented
@Constraint(validatedBy = [EndTimeMustBeAfterStartTimeValidator::class])
@Target(AnnotationTarget.CLASS, AnnotationTarget.FILE, AnnotationTarget.ANNOTATION_CLASS)
@Retention(RUNTIME)
annotation class EndTimeMustBeAfterStartTime(val message: String = "{mrs.app.reservation.EndTimeMustBeAfterStartTime.message}", val groups: Array<KClass<*>> = [], val payload: Array<KClass<out Payload>> = [])
