package com.mirtapp.mirtapp.persistence

import com.mirtapp.mirtapp.model.Provider
import com.mirtapp.mirtapp.persistence.repository.ProviderRepository
import org.springframework.stereotype.Component

@Component
class ProviderDAO(val providerRepository: ProviderRepository) {
    fun get(providerId: Long): Provider {
        // Provider exists in database
        return providerRepository.findById(providerId).get()
    }

    fun existProviderWithId(providerId: Long): Boolean {
        return providerRepository.existsById(providerId)
    }

    fun existProviderWithName(name: String): Boolean {
        return providerRepository.existsByName(name)
    }

    fun save(provider: Provider): Provider {
        return providerRepository.save(provider)
    }

    fun delete(id: Long) {
        return providerRepository.deleteById(id)
    }

}
