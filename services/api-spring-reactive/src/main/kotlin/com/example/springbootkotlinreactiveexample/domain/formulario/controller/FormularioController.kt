package com.example.springbootkotlinreactiveexample.domain.formulario.controller

import com.example.springbootkotlinreactiveexample.domain.formulario.dao.Formulario
import com.example.springbootkotlinreactiveexample.domain.formulario.repository.FormularioRepository
import org.springframework.http.MediaType.APPLICATION_JSON_VALUE
import org.springframework.web.bind.annotation.*
import reactor.core.publisher.Flux
import reactor.core.publisher.Mono
import java.util.UUID

@RestController
@RequestMapping(path = ["/v1/formularios"], produces = [APPLICATION_JSON_VALUE])
class FormularioController(
    private val formularioRepository: FormularioRepository
) {

    @GetMapping
    fun findAllStudents(): Flux<Formulario> {
        return formularioRepository.findAll()
    }

    @GetMapping(path = ["/{id}"])
    fun findStudentById(@PathVariable id: UUID): Mono<Formulario> {
        return formularioRepository.findOne(id);
    }

    @PostMapping
    fun saveStudent(@RequestBody formulario: Formulario): Mono<Formulario> {
        return formularioRepository.create(formulario)
    }
}