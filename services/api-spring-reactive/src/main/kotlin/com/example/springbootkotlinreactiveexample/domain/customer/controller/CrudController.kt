package com.example.springbootkotlinreactiveexample.domain.customer.controller

import com.example.springbootkotlinreactiveexample.domain.customer.domain.Customer
import com.example.springbootkotlinreactiveexample.domain.customer.service.CustomerService
import com.example.springbootkotlinreactiveexample.domain.customer.util.Result
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import reactor.core.publisher.Mono

@Controller
class CrudController(
    private var customerService: CustomerService
) {
    @PostMapping("/save") //C
    fun saveCustomer(@RequestBody customer: Customer): Mono<Result> {
        return customerService.createNewCustomer(customer)
    }

    @GetMapping("/get/{customerId}") //R
    fun getCustomer(@PathVariable customerId: String): Mono<Customer> {
        return customerService.getCustomerByCustomerId(customerId)
    }
}