package com.mirtapp.mirtapp.controller

import com.loshermanos.service.exception.MirtappException
import com.mirtapp.mirtapp.model.Item
import com.mirtapp.mirtapp.model.Product
import com.mirtapp.mirtapp.service.AdvisorService
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
@Component(value = "advisorController")
class AdvisorController (val advisorService: AdvisorService){

    @ControllerAdvice
    class ControllerAdviceRequestError : ResponseEntityExceptionHandler() {
        @ExceptionHandler(value = [(MirtappException::class)])
        fun handleUserAlreadyExists(ex: MirtappException,request: WebRequest): ResponseEntity<String> {
            return ResponseEntity(ex.message, ex.status)
        }
    }
    @GetMapping("/advice/{userId}/{providerId}")
    fun getAdviceFor(@PathVariable userId : Long,@PathVariable providerId : Long): ResponseEntity<List<Item>> {
        return ResponseEntity(advisorService.getAdviceFor(userId,providerId),HttpStatus.OK)
    }

}