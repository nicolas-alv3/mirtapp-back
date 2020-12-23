package com.mirtapp.mirtapp.model

import com.fasterxml.jackson.annotation.JsonIgnore
import javax.persistence.*
import kotlin.jvm.Transient

@Entity
@Table(name="item")
class Item(
        var amount: Int,
        val measure: ProductMeasure,
        @OneToOne(cascade = [CascadeType.ALL])
        val product: Product = Product(),
        @ManyToOne(fetch = FetchType.LAZY,cascade = [CascadeType.ALL])
        @JoinColumn(name="itsowner_id")
        @JsonIgnore
        val itsOwner : ShoppingList = ShoppingList(),
        @Transient
        var isPending : Boolean = false
) : AbstractJpaPersistable<Long>() {
    constructor() : this(0,ProductMeasure.CAJA) {}
    fun price() = amount * product.price
    fun setPending() {
        isPending=true
    }

    fun copy(): Item {
        return Item(amount,measure,product.copy(),itsOwner,isPending)
    }

    fun setAnotherAmount(newAmount: Int) {
        amount = newAmount
    }
}
