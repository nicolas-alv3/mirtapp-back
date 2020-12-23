package com.mirtapp.mirtapp.model

import javax.persistence.CascadeType
import javax.persistence.Entity
import javax.persistence.OneToMany
import javax.persistence.Table

@Entity
@Table(name = "users")
class User(
        var fullName:String,
        var email:String,
        var photoURL : String,
        @OneToMany(mappedBy="itsOwner",cascade = [CascadeType.ALL])
        var shLists: MutableList<ShoppingList> = mutableListOf(),
        @OneToMany(cascade = [CascadeType.ALL])
        var pendingItems: MutableSet<Item> = mutableSetOf()
) : AbstractJpaPersistable<Long>() {
    constructor() : this("","","") {}

    fun getShListLength(): Int = shLists.size
    fun getFirstName(): String = fullName.split(' ').first()
    fun getLastName(): String = fullName.split(' ').last()
    fun addShoppingList(shList: ShoppingList) =shLists.add(shList)
    fun deleteShoppingList(shList: ShoppingList) =shLists.remove(shList)

    fun addToPendings(pendings: Collection<Item>) {
        pendingItems.addAll(pendings)
    }

    fun updatePendings(list: MutableList<Item>) {
        addToPendings(list.filter { i -> i.isPending })
        list.forEach { i -> removeFromPendings(i) }
    }

    private fun removeFromPendings(item: Item) {
        pendingItems = pendingItems.filter { i -> ! canBeRemovedFromPendingBy(i, item) }.toMutableSet()
    }

    private fun canBeRemovedFromPendingBy(pending: Item, item: Item): Boolean {
        if(item.product == pending.product && ! item.isPending)
            pending.setAnotherAmount(pending.amount - item.amount)
        return pending.amount <= 0
    }
}
