package com.github.hugovallada.casadocodigo.autor

import io.micronaut.http.annotation.Body
import io.micronaut.http.annotation.Controller
import io.micronaut.http.annotation.Post

@Controller("/autores")
class CadastroAutorController {

    @Post
    fun cadastrar(@Body request: NovoAutorRequest){
        println(request)
    }


}