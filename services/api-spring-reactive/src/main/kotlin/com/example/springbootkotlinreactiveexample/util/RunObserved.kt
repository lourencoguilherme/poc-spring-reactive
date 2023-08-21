/*package com.example.springbootkotlinreactiveexample.util

import io.micrometer.context.ContextSnapshot
import io.micrometer.observation.Observation
import io.micrometer.observation.ObservationRegistry
import io.micrometer.observation.contextpropagation.ObservationThreadLocalAccessor
import kotlinx.coroutines.reactor.awaitSingleOrNull
import kotlinx.coroutines.reactor.mono
import reactor.core.publisher.Mono

suspend fun ObservationRegistry.runObserved(name: String, f: suspend () -> Unit) {
    Mono.deferContextual { contextView ->
        ContextSnapshot.setThreadLocalsFrom(
            contextView,
            ObservationThreadLocalAccessor.KEY,
        ).use {
            val observation = Observation.start(name, this)
            Mono.just(observation).flatMap {
                mono { f() }
            }.doOnError {
                observation.error(it)
                observation.stop()
            }.doOnSuccess {
                observation.stop()
            }
        }
    }.awaitSingleOrNull()
}

suspend inline fun observeCtx(crossinline f: () -> Unit) {
    Mono.deferContextual { contextView ->
        ContextSnapshot.setThreadLocalsFrom(
            contextView,
            ObservationThreadLocalAccessor.KEY,
        ).use {
            f()
            Mono.empty<Unit>()
        }
    }.awaitSingleOrNull()
}
*/