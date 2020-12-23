package com.mirtapp.mirtapp.model

import javax.persistence.Entity
import javax.persistence.Table

@Entity
@Table(name="closed_state")
class ClosedState(override val name :String = "Closed state") : ShoppingListState() {
    override fun isOpen(): Boolean = false
    override fun close(shoppingList: ShoppingList) {
        shoppingList.copyProducts()
        shoppingList.updateUserPendings()
    }

}
