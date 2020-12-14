package com.mirtapp.mirtapp.persistence

import com.mirtapp.mirtapp.model.Product
import com.mirtapp.mirtapp.persistence.repository.ProductRepository
import org.springframework.stereotype.Component
import javax.transaction.Transactional


@Component
class ProductDAO(val productRepository: ProductRepository) {
    @Transactional
    fun save(product: Product): Product {
        return productRepository.save(product)
    }

    fun getAll(): List<Product> {
        return productRepository.findAll()
    }
}
