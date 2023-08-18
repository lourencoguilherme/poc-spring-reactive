package com.example.springbootkotlinreactiveexample.domain.customer.service

import com.example.springbootkotlinreactiveexample.domain.customer.domain.Customer
import com.example.springbootkotlinreactiveexample.domain.customer.repos.CustomerRepository
import com.example.springbootkotlinreactiveexample.domain.customer.util.Result
import org.springframework.stereotype.Service
import reactor.core.publisher.Flux
import reactor.core.publisher.Mono
import java.time.Instant
import java.util.*
import java.util.function.Consumer
import java.util.function.LongSupplier
import com.example.springbootkotlinreactiveexample.domain.customer.util.Result.SUCCESS
import com.example.springbootkotlinreactiveexample.domain.customer.util.Result.FAIL

@Service
class CustomerService(
    private val customerRepository: CustomerRepository
) {
    fun createNewCustomer(customer: Customer): Mono<Result> {
        return Mono.fromFuture(customerRepository.save(customer))
            .thenReturn(SUCCESS)
            .onErrorReturn(FAIL)
    }
    fun getCustomerByCustomerId(customerId: String): Mono<Customer> {
        return Mono.fromFuture(customerRepository.getCustomerByID(customerId))
            .doOnSuccess(Objects::requireNonNull)
            .onErrorReturn(Customer())
    }
/*
    fun getCustomerByCustomerId(customerId: String?): Mono<Customer?>? {
        return Mono.fromFuture(customerRepository.getCustomerByID(customerId))
            .doOnSuccess(Objects::requireNonNull)
            .onErrorReturn(Customer())
    }


    fun updateExistingCustomer(customer: Customer): Mono<Result?>? {
        customer.setCreatedTimeStamp(getEpochSecond.asLong)
        return Mono.fromFuture(customerRepository.getCustomerByID(customer.getCustomerID()))
            .doOnSuccess(Objects::requireNonNull)
            .doOnNext { __ -> customerRepository.updateCustomer(customer) }
            .doOnSuccess(Objects::requireNonNull)
            .thenReturn(SUCCESS)
            .onErrorReturn(FAIL)
    }

    fun updateExistingOrCreateCustomer(customer: Customer): Mono<Result?>? {
        return Mono.fromFuture(customerRepository.updateCustomer(customer))
            .thenReturn(SUCCESS)
            .onErrorReturn(FAIL)
    }

    fun deleteCustomerByCustomerId(customerId: String?): Mono<Customer> {
        return Mono.fromFuture(customerRepository.deleteCustomerById(customerId))
            .doOnSuccess(Objects::requireNonNull)
            .thenReturn(SUCCESS)
            .onErrorReturn(FAIL)
    }

    fun getCustomerList(): Flux<Customer?>? {
        return customerRepository.getAllCustomer()?.let {
            Flux.from(
                it
                    .items()
            )
                .onErrorReturn(Customer())
        }
    }*/
}