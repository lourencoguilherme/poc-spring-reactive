package com.example.springbootkotlinreactiveexample.domain.processos.repository

import com.example.springbootkotlinreactiveexample.domain.processos.dao.Processo
import reactor.core.publisher.Flux
import reactor.core.publisher.Mono
import java.util.UUID

interface ProcessoRepository {

    fun findAllProcessos(): Flux<Processo>

    fun getProcessos(studentId: UUID): Mono<Processo>

    fun saveProcesso(processo: Processo): Mono<Processo>

}