package com.example.springbootkotlinreactiveexample.domain.formulario.repository

import com.example.springbootkotlinreactiveexample.domain.formulario.dao.Formulario
import reactor.core.publisher.Flux
import reactor.core.publisher.Mono
import java.util.*

interface FormularioRepository {

    fun findAll(): Flux<Formulario>

    fun findOne(studentId: UUID): Mono<Formulario>

    fun create(student: Formulario): Mono<Formulario>

}