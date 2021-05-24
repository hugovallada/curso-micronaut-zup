package com.github.hugovallada.casadocodigo.autor

import io.micronaut.http.HttpResponse
import io.micronaut.http.annotation.*
import javax.validation.Valid

@Controller("/autores")
class AtualizaAutorController(private val autorRepository: AutorRepository) {

    @Put("/{id}")
    fun atualizar(@PathVariable id: Long, descricao: String) : HttpResponse<Any> {
        val autorOptional = autorRepository.findById(id)

        if (autorOptional.isEmpty) {
            return HttpResponse.notFound()
        }

        val autor = autorOptional.get().let { autor ->
            autor.descricao = descricao
            autorRepository.update(autor)
        }

        return HttpResponse.ok(DetalhesDoAutorResponse(autor))
    }
}