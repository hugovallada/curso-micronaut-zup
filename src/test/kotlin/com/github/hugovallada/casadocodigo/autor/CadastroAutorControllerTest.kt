package com.github.hugovallada.casadocodigo.autor

import io.micronaut.http.HttpRequest
import io.micronaut.http.HttpResponse
import io.micronaut.http.HttpStatus
import io.micronaut.http.client.HttpClient
import io.micronaut.http.client.annotation.Client
import io.micronaut.test.annotation.MockBean
import io.micronaut.test.extensions.junit5.annotation.MicronautTest
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test
import org.mockito.Mockito
import javax.inject.Inject

@MicronautTest
internal class CadastroAutorControllerTest {

    @field:Inject
    lateinit var enderecoClient: EnderecoClient

    @field:Inject
    @field:Client("/")
    lateinit var client: HttpClient

    @field:Inject
    lateinit var repository: AutorRepository

    @Test
    internal fun `deve cadastrar um novo autor`() {
        //cenario
        val novoAutorRequest = NovoAutorRequest("Hugo","hugo@email.com", "Junior", "14230-090", "675")

        val enderecoResponse = EnderecoResponse("1400000", "Laranjeiras", "Rio", "RJ")

        Mockito.`when`(enderecoClient.consulta(novoAutorRequest.cep))
            .thenReturn(HttpResponse.ok(enderecoResponse))

        val request = HttpRequest.POST("/autores", novoAutorRequest)

        //ação
        val response = client.toBlocking().exchange(request, Any::class.java)

        assertEquals(HttpStatus.CREATED, response.status)
        assertTrue(response.headers.contains("Location"))
        assertTrue(response.header("Location")!!.matches("/autores/\\d".toRegex()))


    }

    @MockBean(EnderecoClient::class)
    fun enderecoMock(): EnderecoClient? {
        return Mockito.mock(EnderecoClient::class.java)
    }
}