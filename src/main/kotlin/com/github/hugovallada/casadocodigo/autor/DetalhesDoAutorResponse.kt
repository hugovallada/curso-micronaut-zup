package com.github.hugovallada.casadocodigo.autor

class DetalhesDoAutorResponse(autor: Autor) {

    val nome = autor.nome
    val email = autor.email
    val descricao = autor.descricao

}
