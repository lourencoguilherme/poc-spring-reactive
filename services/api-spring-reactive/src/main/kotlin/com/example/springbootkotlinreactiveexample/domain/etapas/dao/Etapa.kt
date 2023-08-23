package com.example.springbootkotlinreactiveexample.domain.etapas.dao

import java.util.UUID

data class Etapa(

    var etapaId: UUID = UUID.randomUUID(),
    var clientId: UUID = UUID.randomUUID(),
    var processoId: UUID = UUID.randomUUID(),
    var nome: String? = null
)