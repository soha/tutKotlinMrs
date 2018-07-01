package mrs.app.reservation

import javax.validation.Constraint
import javax.validation.Payload
import java.lang.annotation.Documented
import java.lang.annotation.Retention

import java.lang.annotation.RetentionPolicy.RUNTIME
import kotlin.reflect.KClass

@Documented
@Constraint(validatedBy = [ThirtyMinutesUnitValidator::class])
@Target(AnnotationTarget.CLASS, AnnotationTarget.FIELD, AnnotationTarget.ANNOTATION_CLASS)
@Retention(RUNTIME)
annotation class ThirtyMinutesUnit(val message: String = "{mrs.app.reservation.ThirtyMinutesUnit.message}", val groups: Array<KClass<*>> = [], val payload: Array<KClass<out Payload>> = [])
