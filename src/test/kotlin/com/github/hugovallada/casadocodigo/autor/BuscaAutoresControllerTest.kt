package com.github.hugovallada.casadocodigo.autor

import io.micronaut.http.HttpStatus
import io.micronaut.http.client.HttpClient
import io.micronaut.http.client.annotation.Client
import io.micronaut.http.client.exceptions.HttpClientResponseException
import io.micronaut.test.extensions.junit5.annotation.MicronautTest
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows
import javax.inject.Inject

@MicronautTest
internal class BuscaAutoresControllerTest{

    @field:Inject
    lateinit var autorRepository: AutorRepository

    @field:Inject
    @field:Client("/")
    lateinit var client: HttpClient

    lateinit var autor: Autor

    @BeforeEach
    internal fun setUp() {
        val enderecoResponse = EnderecoResponse("14010090","Rua Tibiriça", "Ribeirão Preto", "SP")
        val endereco = Endereco(enderecoResponse  , "638")
        autor = Autor("Hugo", "valladahugo5@gmail.com","Um autor", endereco)

        autorRepository.save(autor)
    }

    @AfterEach
    internal fun tearDown() {
        autorRepository.delete(autor)
    }

    @Test
    internal fun `deve retornar os detalhes de um autor`() {

        val response = client.toBlocking().exchange("/autores?email=${autor.email}", DetalhesDoAutorResponse::class.java)

        assertEquals(HttpStatus.OK, response.status)
        assertNotNull(response.body())

        assertEquals(autor.nome, response.body()!!.nome)
        assertEquals(autor.descricao, response.body()!!.descricao)
        assertEquals(autor.email, response.body()!!.email)

    }

    @Test
    internal fun `deve retornar 404 quando o email não existir`() {

        assertThrows<HttpClientResponseException> {
            client.toBlocking().exchange("autores?email=seila@email.com", DetalhesDoAutorResponse::class.java)
        } . run {
            assertEquals(HttpStatus.NOT_FOUND, status)
        }
    }

    @Test
    internal fun `deve retornar uma lista quando um email não for passado`() {
        val response = client.toBlocking()
            .exchange("autores", List::class.java)

        response.run {
            assertTrue(body != null)
            assertEquals(HttpStatus.OK,status)
        }
    }
}