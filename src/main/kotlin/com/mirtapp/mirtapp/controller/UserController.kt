package com.mirtapp.mirtapp.controller

import com.mirtapp.mirtapp.DTO.CloseShListDTO
import com.mirtapp.mirtapp.DTO.ShoppingListDTO
import com.mirtapp.mirtapp.DTO.UserDTO
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

    @PostMapping("/user/login")
    fun postProduct(@RequestBody body: UserDTO): ResponseEntity<User> {
        return ResponseEntity(userService.getOrsave(body),HttpStatus.OK)
    }

    @PostMapping("/user/shoppingList")
    fun postShList(@RequestBody shList: ShoppingListDTO): ResponseEntity<User> {
        return ResponseEntity(userService.addShList(shList),HttpStatus.OK)
    }

    @PostMapping("/user/shoppingList/close")
    fun closeShList(@RequestBody dto : CloseShListDTO): ResponseEntity<User> {
        return ResponseEntity(userService.closeShList(dto.userId,dto.shListId,dto.pendingIds),HttpStatus.OK)
    }

    @GetMapping("/user/{id}")
    fun getProduct(@PathVariable id :Long): ResponseEntity<User> {
        return ResponseEntity(userService.get(id),HttpStatus.OK)
    }

}