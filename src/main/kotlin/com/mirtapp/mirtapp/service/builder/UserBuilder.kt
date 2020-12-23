package com.mirtapp.mirtapp.service.builder

import com.mirtapp.mirtapp.model.User

class UserBuilder() {
    fun aUser(): User {
        return User("Builded fullname","Builded email","Builded url", mutableListOf())
    }

}
