package com.github.hugovallada.casadocodigo.autor

import io.micronaut.http.HttpResponse
import io.micronaut.http.annotation.Controller
import io.micronaut.http.annotation.Get
import io.micronaut.http.annotation.QueryValue

@Controller("/autores")
class BuscaAutoresController(private val autorRepository: AutorRepository) {

    @Get
    fun listar(@QueryValue(defaultValue = "") email: String): HttpResponse<Any> {

        if (email.isBlank()) {
            val autores = autorRepository.findAll().toList() // deixa imutável

            val response = autores.map { autor ->
                DetalhesDoAutorResponse(autor)
            }
            return HttpResponse.ok(response)
        }

        val possivelAutor = autorRepository.findByEmail(email)

        if (possivelAutor.isPresent) {
            return HttpResponse.ok(DetalhesDoAutorResponse(possivelAutor.get()))
        }

        return HttpResponse.notFound()
    }

    @Get("/funcional")
    fun listaFuncional(@QueryValue(defaultValue = "") email: String): HttpResponse<Any> {

        email.run {
            if (isBlank()) {
                val autores = autorRepository.findAll().toList()
                autores.map {
                    DetalhesDoAutorResponse(it)
                }.run {
                    return HttpResponse.ok(this)
                }
            } else {
                val opt = autorRepository.buscarPorEmailSQL(this)
                if (opt.isPresent) {
                    return HttpResponse.ok(DetalhesDoAutorResponse(opt.get()))
                }
            }

            return HttpResponse.notFound()
        }

    }

}
