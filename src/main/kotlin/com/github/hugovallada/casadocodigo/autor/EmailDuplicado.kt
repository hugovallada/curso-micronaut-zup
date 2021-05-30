package com.github.hugovallada.casadocodigo.autor

import io.micronaut.core.annotation.AnnotationValue
import io.micronaut.validation.validator.constraints.ConstraintValidator
import io.micronaut.validation.validator.constraints.ConstraintValidatorContext
import javax.inject.Singleton
import javax.validation.Constraint

@MustBeDocumented
@Target(AnnotationTarget.FIELD)
@Retention(AnnotationRetention.RUNTIME)
@Constraint(validatedBy = [EmailDuplicadoValidator::class])
annotation class EmailDuplicado(val message: String = "O email já existe")

@Singleton
class EmailDuplicadoValidator(private val autorRepository: AutorRepository) :
    ConstraintValidator<EmailDuplicado, String> {
    override fun isValid(
        value: String?,
        annotationMetadata: AnnotationValue<EmailDuplicado>,
        context: ConstraintValidatorContext
    ): Boolean {

        value.run {
            if (isNullOrEmpty()) return true
            return autorRepository.findByEmail(this).isEmpty
        }

    }

}

class EmailDuplicadoValidatorNaoFuncional(private val autorRepository: AutorRepository) :
    ConstraintValidator<EmailDuplicado, String> {
    override fun isValid(
        value: String?,
        annotationMetadata: AnnotationValue<EmailDuplicado>,
        context: ConstraintValidatorContext
    ): Boolean {

        if (value == null) return true
        return autorRepository.findByEmail(value).isEmpty
    }
}
