package com.mirtapp.mirtapp.service

import com.loshermanos.service.exception.AlreadyExistException
import com.mirtapp.mirtapp.model.Provider
import com.mirtapp.mirtapp.persistence.ProviderDAO
import com.mirtapp.mirtapp.service.exception.NotFoundException
import org.springframework.context.annotation.Scope
import org.springframework.stereotype.Component


@Scope(value = "session")
@Component(value = "providerService")
class ProviderService(val providerDAO: ProviderDAO) {
    fun get(providerId: Long): Provider {
        checkIfProviderExists(providerId)
        return providerDAO.get(providerId)
    }

    private fun checkIfProviderExists(providerId: Long) {
        if(! providerDAO.existProviderWithId(providerId))
            throw NotFoundException("No se ha encontrado provedor con ese id")
    }

    fun save(provider: Provider): Provider {
        checkIfProviderExistsByName(provider.name)
        return providerDAO.save(provider)
    }

    private fun checkIfProviderExistsByName(name: String) {
        if(providerDAO.existProviderWithName(name))
            throw AlreadyExistException("Ya existe un proveedor con ese nombre")
    }

    fun delete(id: Long) {
        checkIfProviderExists(id)
        return providerDAO.delete(id)
    }

}
