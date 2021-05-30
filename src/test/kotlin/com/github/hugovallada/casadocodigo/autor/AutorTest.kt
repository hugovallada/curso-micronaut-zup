package com.github.hugovallada.casadocodigo.autor

import io.micronaut.test.annotation.TransactionMode
import io.micronaut.test.extensions.junit5.annotation.MicronautTest
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test
import javax.inject.Inject
import org.junit.jupiter.api.Assertions.*

@MicronautTest(rollback = false, transactionMode = TransactionMode.SINGLE_TRANSACTION) // Before e os testes do micronaut acontecem na msm transação
internal class AutorTest {

    /**
     * louça: sujou, limpou -> @AfterEach
     * louça: limpou, sujou -> @BeforeEach
     * louça: usa louça descartável -> rollback = true
     * louça: uso a louça, joga fora, compra nova -> recriar o banco a cada teste
     */

    @Inject
    lateinit var repository: AutorRepository

    @AfterEach
    internal fun tearDown() {
        repository.deleteAll()
    }

    @Test
    internal fun `deve inserir um novo carro`() {
        // Cenário
        val autor = Autor("Hugo", "email@email.com","Autor", null)

        // Ação
        repository.save(autor)

        // Validação
        assertEquals(1, repository.count())
    } // por padrão o Micronaut faz o rollback para não sujar o banco

    @Test
    internal fun `deve encontrar carro por placa`() {

        // Cenário
        val autor = Autor("Hugo", "email@email.com","Autor", null)
        repository.save(autor)

        // Ação
        val encontrado = repository.findByEmail("email@email.com")

        //Validação
        assertNotNull(encontrado.get())
        assertEquals("Hugo", encontrado.get().nome)

    }
}