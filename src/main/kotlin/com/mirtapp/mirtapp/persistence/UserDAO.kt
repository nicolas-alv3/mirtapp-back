package com.mirtapp.mirtapp.persistence

import com.mirtapp.mirtapp.model.User
import com.mirtapp.mirtapp.persistence.repository.UserRepository
import org.springframework.stereotype.Component
import javax.transaction.Transactional


@Component
class UserDAO(val userRepository: UserRepository) {
    @Transactional
    fun save(user: User): User {
        return userRepository.save(user)
    }

    fun getAll(): List<User> {
        return userRepository.findAll()
    }

    fun get(userId: Long): User {
        return userRepository.findById(userId).get()
    }

    fun existByEmail(email: String): Boolean {
        return userRepository.existsByEmail(email)
    }

    fun getByEmail(email: String): User {
        return userRepository.findUserByEmail(email)
    }

    fun existById(id:Long): Boolean {
        return userRepository.existsById(id)
    }
}
