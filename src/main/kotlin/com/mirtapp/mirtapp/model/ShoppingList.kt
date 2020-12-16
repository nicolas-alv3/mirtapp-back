package com.mirtapp.mirtapp.model

import com.fasterxml.jackson.annotation.JsonIgnore
import javax.persistence.*
@Entity
data class ShoppingList(
        @OneToMany(cascade = [CascadeType.ALL],mappedBy="owner")
        var list:MutableList<Item> = mutableListOf(),
        @ManyToOne(fetch = FetchType.LAZY,cascade = [CascadeType.ALL])
        @JoinColumn(name="owner_id")
        @JsonIgnore
        val owner: User = User()
) : AbstractJpaPersistable<Long>() {
    fun getTotalPrice() :Double = list.fold(0.0, { acc:Double ,p:Item -> p.price().plus(acc)})
    fun getLength(): Int = list.size
    fun addList(aList: List<Item>) {
        list.addAll(aList)
    }
}