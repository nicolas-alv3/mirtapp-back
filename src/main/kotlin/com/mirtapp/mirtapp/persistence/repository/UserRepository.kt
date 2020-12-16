package com.mirtapp.mirtapp.persistence.repository

import com.mirtapp.mirtapp.model.User
import org.springframework.data.jpa.repository.JpaRepository

interface UserRepository : JpaRepository<User, Long> {
}