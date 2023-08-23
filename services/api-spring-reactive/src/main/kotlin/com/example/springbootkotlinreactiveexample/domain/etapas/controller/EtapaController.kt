package com.example.springbootkotlinreactiveexample.domain.etapas.controller

import com.example.springbootkotlinreactiveexample.domain.etapas.dao.Etapa
import com.example.springbootkotlinreactiveexample.domain.etapas.repository.EtapaRepository
import org.springframework.http.MediaType.APPLICATION_JSON_VALUE
import org.springframework.web.bind.annotation.*
import reactor.core.publisher.Flux
import reactor.core.publisher.Mono
import java.util.UUID

@RestController
@RequestMapping(path = ["/v1/etapas"], produces = [APPLICATION_JSON_VALUE])
class EtapaController(
    private val repository: EtapaRepository
) {

    @GetMapping
    fun findAllProcessos(): Flux<Etapa> {
        return repository.findAll()
    }

    @GetMapping(path = ["/{id}"])
    fun getProcessos(@PathVariable id: UUID): Mono<Etapa> {
        return repository.findById(id);
    }

    @PostMapping
    fun saveProcesso(@RequestBody entity: Etapa): Mono<Etapa> {
        return repository.save(entity)
    }
}