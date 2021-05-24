package com.github.hugovallada.casadocodigo.autor

import io.micronaut.http.HttpResponse
import io.micronaut.http.annotation.Body
import io.micronaut.http.annotation.Controller
import io.micronaut.http.annotation.Post
import io.micronaut.http.uri.UriBuilder
import io.micronaut.validation.Validated
import javax.validation.Valid

@Validated
@Controller("/autores")
class CadastroAutorController(private val autorRepository: AutorRepository) {

    @Post
    fun cadastrar(@Body @Valid request: NovoAutorRequest) : HttpResponse<Any> {
        val autor = request.toModel()
        autorRepository.save(autor)
        val uri = UriBuilder.of("/autores/{id}")
            .expand(mutableMapOf(Pair("id", autor.id)))
        return HttpResponse.created(uri)
    }


}