package com.github.hugovallada.casadocodigo.autor

import javax.persistence.Embeddable
import javax.persistence.Entity

@Embeddable
class Endereco(enderecoResponse: EnderecoResponse, val numero: String) {
    val rua = enderecoResponse.rua
    val cidade = enderecoResponse.cidade
    val estado = enderecoResponse.estado
}
