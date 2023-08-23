package com.example.springbootkotlinreactiveexample.domain.etapas.repository

import com.example.springbootkotlinreactiveexample.domain.etapas.dao.Etapa
import org.springframework.data.mongodb.repository.ReactiveMongoRepository
import java.util.UUID

interface EtapaRepository: ReactiveMongoRepository<Etapa, UUID>