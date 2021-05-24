package com.github.hugovallada.casadocodigo.autor

import io.micronaut.data.annotation.Query
import io.micronaut.data.annotation.Repository
import io.micronaut.data.jpa.repository.JpaRepository
import java.util.*

@Repository
interface AutorRepository : JpaRepository<Autor, Long> {

    fun findByEmail(email: String) : Optional<Autor>

    @Query("Select a from Autor a WHERE a.email = :email")
    fun buscarPorEmail(email: String) : Optional<Autor>

    @Query("Select * from autor a where a.email = :email", nativeQuery = true)
    fun buscarPorEmailSQL(email: String) : Optional<Autor>
}