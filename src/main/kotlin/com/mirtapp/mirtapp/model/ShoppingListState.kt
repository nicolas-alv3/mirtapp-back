package com.mirtapp.mirtapp.model

import javax.persistence.Entity

@Entity
abstract class ShoppingListState: AbstractJpaPersistable<Long>() {
    abstract val name :String
    abstract fun isOpen():Boolean
    open fun putInPending(shoppingList: ShoppingList, itemId: Long) {}
    open fun close(shoppingList: ShoppingList) {}
}
