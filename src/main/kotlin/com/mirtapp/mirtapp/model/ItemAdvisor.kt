package com.mirtapp.mirtapp.model

class ItemAdvisor {

    fun getSuggestedItemsFor(aNewUser: User, aProvider: Provider): List<Item> {

        return aNewUser.pendingItems.filter { i ->
            i.isPending && i.product.providers.contains(aProvider)}
    }
}
