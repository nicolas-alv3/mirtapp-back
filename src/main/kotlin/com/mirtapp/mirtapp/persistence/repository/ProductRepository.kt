package com.mirtapp.mirtapp.persistence.repository
import com.mirtapp.mirtapp.model.Product
import org.springframework.data.jpa.repository.JpaRepository


interface ProductRepository : JpaRepository<Product, Long> {
    fun existsByDescription(description: String):Boolean
}
