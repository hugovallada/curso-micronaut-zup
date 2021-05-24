package com.github.hugovallada.casadocodigo.autor

import io.micronaut.http.HttpResponse
import io.micronaut.http.annotation.Controller
import io.micronaut.http.annotation.Get

@Controller("/autores")
class BuscaAutoresController(private val autorRepository: AutorRepository) {

    @Get
    fun listar(): HttpResponse<List<DetalhesDoAutorResponse>> {
        val autores = autorRepository.findAll().toList() // deixa imutável

        val response = autores.map { autor ->
            DetalhesDoAutorResponse(autor)
        }

        return HttpResponse.ok(response)
    }
}