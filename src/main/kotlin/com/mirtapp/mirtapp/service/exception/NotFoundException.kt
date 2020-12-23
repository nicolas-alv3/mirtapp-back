package com.mirtapp.mirtapp.service.exception

import com.loshermanos.service.exception.MirtappException
import org.springframework.http.HttpStatus

class NotFoundException(s: String) : MirtappException(s, HttpStatus.NOT_FOUND) {
}