package com.mirtapp.mirtapp.model

import com.fasterxml.jackson.annotation.JsonIgnore
import javax.persistence.*

@Entity
class Item(
        val amount: Int,
        val measure: ProductMeasure,
        @OneToOne(cascade = [CascadeType.ALL])
        val product: Product = Product(),
        @ManyToOne(fetch = FetchType.LAZY,cascade = [CascadeType.ALL])
        @JoinColumn(name="owner_id")
        @JsonIgnore
        val owner : ShoppingList = ShoppingList()
) : AbstractJpaPersistable<Long>() {
    constructor() : this(0,ProductMeasure.CAJA) {}
    fun price() = amount * product.price
}
