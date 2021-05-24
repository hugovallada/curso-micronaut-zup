package com.github.hugovallada.casadocodigo.autor

import io.micronaut.core.annotation.Introspected
import io.micronaut.http.HttpResponse
import javax.validation.constraints.Email
import javax.validation.constraints.NotBlank
import javax.validation.constraints.Size

@Introspected
data class NovoAutorRequest(
    @field:NotBlank val nome: String,
    @field:Email @field:EmailDuplicado val email: String,
    @field:NotBlank @field:Size(max = 400) val descricao: String,
    @field:NotBlank @field:Cep val cep:String,
    @field:NotBlank val numero: String
) {
    fun toModel(enderecoResponse: EnderecoResponse): Autor {
        val endereco = Endereco(enderecoResponse, numero)
        return Autor(nome, email, descricao, endereco)
    }
}
