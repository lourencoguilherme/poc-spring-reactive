package com.example.springbootkotlinreactiveexample.domain.customer.repos

import com.example.springbootkotlinreactiveexample.domain.customer.domain.Customer
import org.springframework.stereotype.Service
import software.amazon.awssdk.core.async.SdkPublisher
import software.amazon.awssdk.enhanced.dynamodb.DynamoDbAsyncTable
import software.amazon.awssdk.enhanced.dynamodb.Key
import software.amazon.awssdk.enhanced.dynamodb.model.PagePublisher
import software.amazon.awssdk.enhanced.dynamodb.model.QueryConditional
import software.amazon.awssdk.enhanced.dynamodb.model.QueryEnhancedRequest
import java.util.concurrent.CompletableFuture

@Service
class CustomerRepository(
    private var customerDynamoDbAsyncTable: DynamoDbAsyncTable<Customer>
) {
    //CREATE
    fun save(customer: Customer): CompletableFuture<Void> {
        return customerDynamoDbAsyncTable.putItem(customer)
    }

    //READ
    fun getCustomerByID(customerId: String): CompletableFuture<Customer> {
        return customerDynamoDbAsyncTable.getItem(getKeyBuild(customerId))
    }

    //UPDATE
    fun updateCustomer(customer: Customer): CompletableFuture<Customer>? {
        return customerDynamoDbAsyncTable!!.updateItem(customer)
    }

    //DELETE
    fun deleteCustomerById(customerId: String): CompletableFuture<Customer?>? {
        return customerDynamoDbAsyncTable.deleteItem(getKeyBuild(customerId))
    }

    //READ_CUSTOMER_ADDRESS_ONLY
    fun getCustomerAddress(customerId: String?): SdkPublisher<Customer>? {
        return customerDynamoDbAsyncTable!!.query { r: QueryEnhancedRequest.Builder ->
            r.queryConditional(
                QueryConditional.keyEqualTo { k: Key.Builder ->
                    k.partitionValue(
                        customerId
                    )
                }
            )
                .addAttributeToProject("CustomerAddress")
        }
            .items()
    }

    //GET_ALL_ITEM
    fun getAllCustomer(): PagePublisher<Customer>? {
        return customerDynamoDbAsyncTable!!.scan()
    }

    private fun getKeyBuild(customerId: String): Key? {
        return Key.builder()
            .partitionValue(customerId)
            .build()
    }
}