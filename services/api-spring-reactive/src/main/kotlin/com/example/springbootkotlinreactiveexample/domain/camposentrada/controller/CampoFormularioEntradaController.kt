package com.example.springbootkotlinreactiveexample.domain.camposentrada.controller

import com.example.springbootkotlinreactiveexample.domain.camposentrada.dao.CampoFormularioEntrada
import com.example.springbootkotlinreactiveexample.domain.camposentrada.repository.CampoFormularioEntradaRepository
import org.springframework.http.MediaType.APPLICATION_JSON_VALUE
import org.springframework.web.bind.annotation.*
import reactor.core.publisher.Flux
import reactor.core.publisher.Mono
import java.util.UUID

@RestController
@RequestMapping(path = ["/v1/formularios/{formularioId}/campos-entrada"], produces = [APPLICATION_JSON_VALUE])
class CampoFormularioEntradaController(
    private val formularioRepository: CampoFormularioEntradaRepository
) {

    @GetMapping
    fun findAllStudents(): Flux<CampoFormularioEntrada> {
        return formularioRepository.findAll()
    }

    @GetMapping(path = ["/{id}"])
    fun findStudentById(@PathVariable formularioId: UUID, @PathVariable id: UUID): Mono<CampoFormularioEntrada> {
        return formularioRepository.findOne(id, formularioId);
    }

    @PostMapping
    fun save(@PathVariable formularioId: UUID, @RequestBody formulario: CampoFormularioEntrada): Mono<CampoFormularioEntrada> {
        formulario.defineFormularioId(formularioId)
        formularioRepository.create(formulario)
        return Mono.just(formulario)
    }
}