package com.github.hugovallada.casadocodigo.autor

import org.hibernate.annotations.CreationTimestamp
import java.time.LocalDateTime
import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.Id

@Entity
class Autor(
    val nome: String,
    val email: String,
    var descricao: String,
    val endereco: Endereco? // Modifica o endereço para aceitar nulo, para permitir a execução dos testes
) {
    @Id @GeneratedValue
    var id: Long? = null

    @CreationTimestamp
    var criadoEm: LocalDateTime? = null
}
