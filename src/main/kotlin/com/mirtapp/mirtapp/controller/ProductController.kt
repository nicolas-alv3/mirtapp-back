package com.mirtapp.mirtapp.controller

import com.loshermanos.service.exception.MirtappException
import com.mirtapp.mirtapp.model.Product
import com.mirtapp.mirtapp.service.ProductService
import com.mirtapp.mirtapp.service.UserService
import org.springframework.context.annotation.Scope
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.stereotype.Component
import org.springframework.web.bind.annotation.*
import org.springframework.web.context.request.WebRequest
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler

@CrossOrigin
@RestController
@Scope(value = "session")
@Component(value = "productController")
class ProductController (val productService: ProductService){

    @ControllerAdvice
    class ControllerAdviceRequestError : ResponseEntityExceptionHandler() {
        @ExceptionHandler(value = [(MirtappException::class)])
        fun handleUserAlreadyExists(ex: MirtappException,request: WebRequest): ResponseEntity<String> {
            return ResponseEntity(ex.message, ex.status)
        }
    }

    @PostMapping("/product")
    fun postProduct(@RequestBody body: Product): ResponseEntity<Product> {
        return ResponseEntity(productService.save(body),HttpStatus.OK)
    }

    @PostMapping("/delete/product/{id}")
    fun deleteProduct(@PathVariable id: Long): ResponseEntity<Unit> {
        return ResponseEntity(productService.deleteById(id),HttpStatus.OK)
    }

    @GetMapping("/product")
    fun getProduct(): ResponseEntity<List<Product>> {
        return ResponseEntity(productService.getAll(),HttpStatus.OK)
    }

    @GetMapping("/product/{id}")
    fun getProductById(@PathVariable id :Long): ResponseEntity<Product> {
        return ResponseEntity(productService.getById(id),HttpStatus.OK)
    }

}