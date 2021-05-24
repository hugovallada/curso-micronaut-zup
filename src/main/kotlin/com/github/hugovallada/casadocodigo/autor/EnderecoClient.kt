package com.github.hugovallada.casadocodigo.autor

import io.micronaut.http.HttpResponse
import io.micronaut.http.MediaType
import io.micronaut.http.annotation.Get
import io.micronaut.http.client.annotation.Client

@Client("https://viacep.com.br/ws/")
interface EnderecoClient {

    @Get("{cep}/json")
    fun consulta(cep: String): HttpResponse<EnderecoResponse>

    /**
     * Método unicamente para trabalhar com um provedor diferente de XML
     *
     */
    @Get("http://cep.republicavirtual.com.br/web_cep.php?cep={cep}&formato=xml", consumes = [MediaType.APPLICATION_XML])
    fun consultaXml(cep: String): HttpResponse<EnderecoResponseXML>
}