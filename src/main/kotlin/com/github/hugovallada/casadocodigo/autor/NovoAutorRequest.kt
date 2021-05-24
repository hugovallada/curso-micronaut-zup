package com.github.hugovallada.casadocodigo.autor

data class NovoAutorRequest(
    val nome: String,
    val email: String,
    val descricao: String
)
