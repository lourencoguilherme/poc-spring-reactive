package com.example.springbootkotlinreactiveexample.util

import org.springframework.web.reactive.function.client.WebClient
import org.springframework.web.reactive.function.client.awaitBody

suspend inline fun <reified T> WebClient.find(uriParam: String): MutableList<T> = this
    .get()
    .uri(uriParam)
    .retrieve()
    .awaitBody<MutableList<T>>()

suspend inline fun <reified T> WebClient.findOne(uriParam: String): T = this
    .get()
    .uri(uriParam)
    .retrieve()
    .awaitBody()