package com.mirtapp.mirtapp.service

import com.mirtapp.mirtapp.model.Product
import com.mirtapp.mirtapp.persistence.ProductDAO
import org.springframework.context.annotation.Scope
import org.springframework.stereotype.Component


@Scope(value = "session")
@Component(value = "productService")
class ProductService(val productDAO: ProductDAO) {

    fun save(product: Product): Product {
        return productDAO.save(product)
    }

    fun getAll(): List<Product> {
        return productDAO.getAll()
    }
}
