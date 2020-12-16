package com.mirtapp.mirtapp.model

import javax.persistence.CascadeType
import javax.persistence.Entity
import javax.persistence.OneToMany
import javax.persistence.Table

@Entity
@Table(name = "USERs")
class User(
        var fullName:String,
        var email:String,
        var photoURL : String,
        @OneToMany(mappedBy="owner",cascade = [CascadeType.ALL])
        var shLists: MutableList<ShoppingList> = mutableListOf()
) : AbstractJpaPersistable<Long>() {
    constructor() : this("","","") {}

    fun getShListLength(): Int = shLists.size
    fun getFirstName(): String = fullName.split(' ').first()
    fun getLastName(): String = fullName.split(' ').last()
    fun addShoppingList(shList: ShoppingList) =shLists.add(shList)
    fun deleteShoppingList(shList: ShoppingList) =shLists.remove(shList)

}
