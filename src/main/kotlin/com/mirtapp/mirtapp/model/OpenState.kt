package com.mirtapp.mirtapp.model

import javax.persistence.Entity
import javax.persistence.Table

@Entity
@Table(name="open_state")
class OpenState(override val name :String = "Open state") : ShoppingListState() {
    override fun isOpen(): Boolean = true
    override fun putInPending(shoppingList: ShoppingList, itemId: Long){
        val item = shoppingList.list.find { i -> i.getId() == itemId }
        item?.setPending()
    }
}
