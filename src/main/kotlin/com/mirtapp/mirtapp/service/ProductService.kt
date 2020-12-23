package com.mirtapp.mirtapp.service

import com.loshermanos.service.exception.AlreadyExistException
import com.mirtapp.mirtapp.model.Product
import com.mirtapp.mirtapp.persistence.ProductDAO
import com.mirtapp.mirtapp.service.exception.NotFoundException
import org.springframework.context.annotation.Scope
import org.springframework.stereotype.Component


@Scope(value = "session")
@Component(value = "productService")
class ProductService(val productDAO: ProductDAO) {

    fun save(product: Product): Product {
        checkIfExistsProductWithSameDescription(product.description)
        return productDAO.save(product)
    }

    private fun checkIfExistsProductWithSameDescription(description: String) {
        if(productDAO.existsWithSameDescription(description))
            throw AlreadyExistException("Ya existe un producto con ese nombre.")
    }

    fun getAll(): List<Product> {
        return productDAO.getAll()
    }

    fun getById(id:Long): Product {
        checkIfExistProductWithId(id)
        return productDAO.get(id)
    }

    private fun checkIfExistProductWithId(id: Long) {
        if( !productDAO.existById(id) )
            throw NotFoundException("No se encontro producto con ese id")
    }

    fun deleteById(id: Long){
        checkIfExistProductWithId(id)
        productDAO.deleteById(id)
    }
}
