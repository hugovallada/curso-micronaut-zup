package com.github.hugovallada.casadocodigo.autor

import io.micronaut.http.annotation.Body
import io.micronaut.http.annotation.Controller
import io.micronaut.http.annotation.Post
import io.micronaut.validation.Validated
import javax.validation.Valid

@Validated
@Controller("/autores")
class CadastroAutorController {

    @Post
    fun cadastrar(@Body @Valid request: NovoAutorRequest){
        println("Requisição => $request")
        val autor = request.toModel()
        println("Autor => ${autor.nome}")
    }


}