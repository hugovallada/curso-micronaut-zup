package com.github.hugovallada.casadocodigo.autor

import io.micronaut.http.HttpResponse
import io.micronaut.http.annotation.Body
import io.micronaut.http.annotation.Controller
import io.micronaut.http.annotation.Post
import io.micronaut.http.uri.UriBuilder
import io.micronaut.validation.Validated
import javax.transaction.Transactional
import javax.validation.Valid

@Validated
@Controller("/autores")
class CadastroAutorController(
    private val autorRepository: AutorRepository,
    private val enderecoClient: EnderecoClient
) {

    @Post
    @Transactional
    fun cadastrar(@Body @Valid request: NovoAutorRequest) : HttpResponse<Any> {

        val consulta = enderecoClient.consulta(request.cep)

        val corpo = consulta.body() ?: return HttpResponse.badRequest()

        val autor = request.toModel(corpo)
        autorRepository.save(autor)
        val uri = UriBuilder.of("/autores/{id}")
            .expand(mutableMapOf(Pair("id", autor.id)))
        return HttpResponse.created(uri)
    }


}