package com.github.hugovallada.casadocodigo.autor

import com.fasterxml.jackson.annotation.JsonProperty

data class EnderecoResponse(
    val cep: String,
    @JsonProperty("logradouro")
    val rua: String,
    @JsonProperty("localidade")
    val cidade: String,
    @JsonProperty("uf")
    val estado: String
)
