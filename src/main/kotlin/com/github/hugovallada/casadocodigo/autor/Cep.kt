package com.github.hugovallada.casadocodigo.autor

import io.micronaut.core.annotation.AnnotationValue
import io.micronaut.validation.validator.constraints.ConstraintValidator
import io.micronaut.validation.validator.constraints.ConstraintValidatorContext
import javax.inject.Singleton
import javax.validation.Constraint
import kotlin.annotation.AnnotationRetention.RUNTIME
import kotlin.annotation.AnnotationTarget.FIELD

@MustBeDocumented
@Target(FIELD)
@Constraint(validatedBy = [CepValidator::class])
@Retention(RUNTIME)
annotation class Cep(val message: String = "Não é um CEP válido")

@Singleton
class CepValidator : ConstraintValidator<Cep, String> {
    override fun isValid(
        value: String?,
        annotationMetadata: AnnotationValue<Cep>,
        context: ConstraintValidatorContext
    ): Boolean {

        if (value != null) {
            return value.matches("[0-9]{5}-[0-9]{3}".toRegex())
        }

        return true
    }

}

@Singleton
class CepValidatorFuncional : ConstraintValidator<Cep, String> {
    override fun isValid(
        value: String?,
        annotationMetadata: AnnotationValue<Cep>,
        context: ConstraintValidatorContext
    ): Boolean {

        value?.run{
            return matches("[0-9]{5}-[0-9]{3}".toRegex())
        }

        return true
    }

}
