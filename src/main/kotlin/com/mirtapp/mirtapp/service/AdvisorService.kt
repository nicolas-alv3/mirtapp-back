package com.mirtapp.mirtapp.service

import com.mirtapp.mirtapp.model.Item
import com.mirtapp.mirtapp.model.ItemAdvisor
import org.springframework.context.annotation.Scope
import org.springframework.stereotype.Component


@Scope(value = "session")
@Component(value = "advisorService")
class AdvisorService(val productService : ProductService, val userService: UserService, val providerService : ProviderService) {
    fun getAdviceFor(userId: Long, providerId: Long): List<Item> {
        val user = userService.get(userId)
        val provider = providerService.get(providerId)
        return ItemAdvisor().getSuggestedItemsFor(user,provider)
    }

}
