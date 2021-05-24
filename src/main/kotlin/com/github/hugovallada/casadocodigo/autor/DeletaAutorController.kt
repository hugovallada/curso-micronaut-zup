package com.github.hugovallada.casadocodigo.autor

import io.micronaut.http.HttpResponse
import io.micronaut.http.annotation.Controller
import io.micronaut.http.annotation.Delete
import io.micronaut.http.annotation.PathVariable

@Controller("/autores")
class DeletaAutorController(private val autorRepository: AutorRepository) {

    @Delete("/{id}")
    fun deletaAutor(@PathVariable id: Long): HttpResponse<Any> {

        autorRepository.findById(id).run{
            if (isEmpty) {
                return HttpResponse.notFound()
            }
        }

        autorRepository.deleteById(id)
        return HttpResponse.noContent()
    }

}