package com.github.hugovallada.casadocodigo.autor

import io.micronaut.http.HttpRequest
import io.micronaut.http.HttpStatus
import io.micronaut.http.annotation.Delete
import io.micronaut.http.client.HttpClient
import io.micronaut.http.client.annotation.Client
import io.micronaut.http.client.exceptions.HttpClientResponseException
import io.micronaut.test.extensions.junit5.annotation.MicronautTest
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import java.net.http.HttpResponse
import javax.inject.Inject

@MicronautTest
internal class DeletaAutorControllerTest{

    @field:Inject
    @field:Client("/")
    lateinit var client: HttpClient

    @field:Inject
    lateinit var repository: AutorRepository

    lateinit var autor: Autor

    @BeforeEach
    internal fun setUp() {
        val enderecoResponse = EnderecoResponse("14010090","Rua Tibiriça", "Ribeirão Preto", "SP")
        val endereco = Endereco(enderecoResponse  , "638")
        autor = Autor("Hugo", "valladahugo5@gmail.com","Um autor", endereco)

        repository.save(autor)
    }

    @AfterEach
    internal fun tearDown() {
        repository.deleteAll()
    }

    @Test
    internal fun `deletar autor caso o Id passado seja válido`() {
        val request = HttpRequest.DELETE("/autores/1", Any::class.java)

        val response = client.toBlocking().exchange(request, Any::class.java)
        assertEquals(HttpStatus.NO_CONTENT, response.status)
    }


    @Test
    internal fun `deve retornar 404 quando o id for inválido`(){
        val request = HttpRequest.DELETE("/autores/1000", Any::class.java)

        assertThrows(HttpClientResponseException::class.java) {
            client.toBlocking().exchange(request, Any::class.java)
        }.run {
            assertEquals(HttpStatus.NOT_FOUND, status)
        }
    }
}