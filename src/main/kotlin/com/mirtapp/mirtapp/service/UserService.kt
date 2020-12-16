package com.mirtapp.mirtapp.service

import com.mirtapp.mirtapp.DTO.ItemDTO
import com.mirtapp.mirtapp.DTO.ShoppingListDTO
import com.mirtapp.mirtapp.model.Item
import com.mirtapp.mirtapp.model.ShoppingList
import com.mirtapp.mirtapp.model.User
import com.mirtapp.mirtapp.persistence.ProductDAO
import com.mirtapp.mirtapp.persistence.UserDAO
import org.springframework.context.annotation.Scope
import org.springframework.stereotype.Component


@Scope(value = "session")
@Component(value = "userService")
class UserService(val userDAO: UserDAO, val productDAO: ProductDAO) {

    fun save(user: User): User {
        return userDAO.save(user)
    }

    fun getAll(): List<User> {
        return userDAO.getAll()
    }

    fun addShList(shList: ShoppingListDTO): User {
        var user = userDAO.get(shList.ownerId)
        val newShList = ShoppingList(itsOwner = user)
        newShList.addList(shList.items.map { i -> fetchItem(i,newShList) } as MutableList<Item>)
        user.addShoppingList(newShList)
        return userDAO.save(user)
    }

    private fun fetchItem(i: ItemDTO, ownerList :ShoppingList): Item {
        return Item(i.amount,i.measure,productDAO.get(i.productId),ownerList)
    }
}
