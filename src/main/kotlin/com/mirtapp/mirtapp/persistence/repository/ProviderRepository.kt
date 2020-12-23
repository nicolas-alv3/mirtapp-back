package com.mirtapp.mirtapp.persistence.repository

import com.mirtapp.mirtapp.model.Provider
import org.springframework.data.jpa.repository.JpaRepository

interface ProviderRepository : JpaRepository<Provider,Long>{
    fun existsByName(name: String): Boolean

}
