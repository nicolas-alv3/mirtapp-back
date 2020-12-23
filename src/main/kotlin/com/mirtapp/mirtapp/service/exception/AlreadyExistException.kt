package com.loshermanos.service.exception

import org.springframework.http.HttpStatus

class AlreadyExistException(message: String) : MirtappException(message,HttpStatus.CONFLICT) {
}
