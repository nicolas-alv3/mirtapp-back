package com.loshermanos.service.exception

import org.springframework.http.HttpStatus

class InvalidProductException(message: String) : MirtappException(message,HttpStatus.BAD_REQUEST) {

}
