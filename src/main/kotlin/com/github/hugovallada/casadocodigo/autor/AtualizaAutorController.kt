package com.github.hugovallada.casadocodigo.autor

import io.micronaut.http.HttpResponse
import io.micronaut.http.annotation.*
import javax.transaction.Transactional
import javax.validation.Valid

@Controller("/autores")
class AtualizaAutorController(private val autorRepository: AutorRepository) {

    @Put("/{id}")
    @Transactional
    fun atualizar(@PathVariable id: Long, descricao: String) : HttpResponse<Any> {
        val autorOptional = autorRepository.findById(id)

        if (autorOptional.isEmpty) {
            return HttpResponse.notFound()
        }

        val autor = autorOptional.get().let { autor ->
            autor.descricao = descricao
            autor
        }

        // Por conta do @Transactional, a atualização acontecerá mesmo sem o update

        return HttpResponse.ok(DetalhesDoAutorResponse(autor))
    }
}