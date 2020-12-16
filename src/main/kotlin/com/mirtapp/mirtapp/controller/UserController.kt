package com.mirtapp.mirtapp.controller

import com.mirtapp.mirtapp.DTO.ShoppingListDTO
import com.mirtapp.mirtapp.model.User
import com.mirtapp.mirtapp.service.UserService
import org.springframework.context.annotation.Scope
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.stereotype.Component
import org.springframework.web.bind.annotation.*

@CrossOrigin
@RestController
@Scope(value = "session")
@Component(value = "userController")
class UserController (val userService: UserService){
    //ControllerAdvice catch the exceptions and throws the corresponding error, saves lot of ifs.
    /*@ControllerAdvice
    class ControllerAdviceRequestError : ResponseEntityExceptionHandler() {
        @ExceptionHandler(value = [(LosHermanosException::class)])
        fun handleUserAlreadyExists(ex: LosHermanosException,request: WebRequest): ResponseEntity<String> {
            return ResponseEntity(ex.message, ex.status)
        }
    }*/

    @PostMapping("/user")
    fun postProduct(@RequestBody body: User): ResponseEntity<User> {
        return ResponseEntity(userService.save(body),HttpStatus.OK)
    }

    @PostMapping("/user/shoppingList")
    fun postShList(@RequestBody shList: ShoppingListDTO): ResponseEntity<User> {
        return ResponseEntity(userService.addShList(shList),HttpStatus.OK)
    }
    @GetMapping("/user")
    fun getProduct(): ResponseEntity<List<User>> {
        return ResponseEntity(userService.getAll(),HttpStatus.OK)
    }

}