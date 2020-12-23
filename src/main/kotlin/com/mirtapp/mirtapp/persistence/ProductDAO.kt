package com.mirtapp.mirtapp.persistence

import com.mirtapp.mirtapp.model.Item
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

    fun get(productId: Long): Product {
        return productRepository.findById(productId).get()
    }

    fun existsWithSameDescription(description:String): Boolean {
        return productRepository.existsByDescription(description)
    }

    fun existById(id: Long): Boolean {
        return productRepository.existsById(id)
    }

    fun deleteById(id: Long) {
        return productRepository.deleteById(id)
    }
}
