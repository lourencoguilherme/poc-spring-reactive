package com.example.springbootkotlinreactiveexample.domain.processos.controller

import com.example.springbootkotlinreactiveexample.domain.processos.dao.Processo
import com.example.springbootkotlinreactiveexample.domain.processos.repository.ProcessoRepository
import org.springframework.http.MediaType.APPLICATION_JSON_VALUE
import org.springframework.web.bind.annotation.*
import reactor.core.publisher.Flux
import reactor.core.publisher.Mono
import java.util.UUID

@RestController
@RequestMapping(path = ["/v1/processos"], produces = [APPLICATION_JSON_VALUE])
class ProcessoController(
    private val processoRepository: ProcessoRepository
) {

    @GetMapping
    fun findAllProcessos(): Flux<Processo> {
        return processoRepository.findAllProcessos()
    }

    @GetMapping(path = ["/{id}"])
    fun getProcessos(@PathVariable id: UUID): Mono<Processo> {
        return processoRepository.getProcessos(id);
    }

    @PostMapping
    fun saveProcesso(@RequestBody processo: Processo): Mono<Processo> {
        return processoRepository.saveProcesso(processo)
    }
}