package com.example.springbootkotlinreactiveexample.domain.camposentrada.repository

import com.example.springbootkotlinreactiveexample.domain.camposentrada.dao.CampoFormularioEntrada
import reactor.core.publisher.Flux
import reactor.core.publisher.Mono
import java.util.*

interface CampoFormularioEntradaRepository {

    fun findAll(): Flux<CampoFormularioEntrada>

    fun findOne(id: UUID, formularioId: UUID): Mono<CampoFormularioEntrada>

    fun create(student: CampoFormularioEntrada): Mono<CampoFormularioEntrada>

}