package com.mirtapp.mirtapp.controller

import com.mirtapp.mirtapp.model.Provider
import com.mirtapp.mirtapp.service.ProductService
import com.mirtapp.mirtapp.service.ProviderService
import org.springframework.context.annotation.Scope
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.stereotype.Component
import org.springframework.web.bind.annotation.*

@CrossOrigin
@RestController
@Scope(value = "session")
@Component(value = "providerController")
class ProviderController (val providerService: ProviderService) {

    @PostMapping("/provider")
    fun postProvider(@RequestBody provider : Provider): ResponseEntity<Provider> {
        return ResponseEntity(providerService.save(provider),HttpStatus.OK)
    }

    @PostMapping("/provider/delete/{id}")
    fun postProvider(@PathVariable id : Long): ResponseEntity<Unit> {
        return ResponseEntity(providerService.delete(id),HttpStatus.OK)
    }
}