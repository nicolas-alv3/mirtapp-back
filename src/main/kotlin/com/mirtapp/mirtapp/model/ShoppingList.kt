package com.mirtapp.mirtapp.model

import com.fasterxml.jackson.annotation.JsonIgnore
import javax.persistence.*
@Entity
@Table(name="shopping_list")
data class ShoppingList(
        @OneToMany(cascade = [CascadeType.ALL],mappedBy="itsOwner")
        var list:MutableList<Item> = mutableListOf(),
        @ManyToOne(fetch = FetchType.LAZY,cascade = [CascadeType.ALL])
        @JoinColumn(name="itsowner_id")
        @JsonIgnore
        val itsOwner: User = User(),
        @OneToOne(fetch = FetchType.LAZY,cascade = [CascadeType.ALL])
        var state : ShoppingListState = OpenState()
) : AbstractJpaPersistable<Long>() {

    fun getTotalPrice() :Double = list.fold(0.0, { acc:Double ,p:Item -> p.price().plus(acc) })

    fun getLength(): Int = list.size

    fun addList(aList: List<Item>) {
        list.addAll(aList)
    }

    fun copyProducts()  {
        list = list.map { i -> i.copy() }.toMutableList()
    }

    fun close() {
        state = ClosedState()
        state.close(this)
    }

    fun putInPending(itemId: Long) {
        state.putInPending(this,itemId)
    }

    fun updateUserPendings() {
        itsOwner.updatePendings(list)
    }
}
