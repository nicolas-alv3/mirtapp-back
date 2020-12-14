package com.mirtapp.mirtapp.controller

import com.mirtapp.mirtapp.model.Product
import com.mirtapp.mirtapp.service.ProductService
import org.springframework.context.annotation.Scope
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.stereotype.Component
import org.springframework.web.bind.annotation.*

@CrossOrigin
@RestController
@Scope(value = "session")
@Component(value = "productController")
class ProductController (val productService: ProductService){
    //ControllerAdvice catch the exceptions and throws the corresponding error, saves lot of ifs.
    /*@ControllerAdvice
    class ControllerAdviceRequestError : ResponseEntityExceptionHandler() {
        @ExceptionHandler(value = [(LosHermanosException::class)])
        fun handleUserAlreadyExists(ex: LosHermanosException,request: WebRequest): ResponseEntity<String> {
            return ResponseEntity(ex.message, ex.status)
        }
    }*/

    @PostMapping("/product")
    fun postProduct(@RequestBody body: Product): ResponseEntity<Product> {
        return ResponseEntity(productService.save(body),HttpStatus.OK)
    }

    @GetMapping("/product")
    fun getProduct(): ResponseEntity<List<Product>> {
        return ResponseEntity(productService.getAll(),HttpStatus.OK)
    }

}