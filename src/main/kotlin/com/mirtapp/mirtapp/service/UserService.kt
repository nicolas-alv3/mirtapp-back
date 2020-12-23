package com.mirtapp.mirtapp.service

import com.mirtapp.mirtapp.DTO.ItemDTO
import com.mirtapp.mirtapp.DTO.ShoppingListDTO
import com.mirtapp.mirtapp.DTO.UserDTO
import com.mirtapp.mirtapp.model.Item
import com.mirtapp.mirtapp.model.ShoppingList
import com.mirtapp.mirtapp.model.User
import com.mirtapp.mirtapp.persistence.UserDAO
import com.mirtapp.mirtapp.service.exception.NotFoundException
import org.springframework.context.annotation.Scope
import org.springframework.stereotype.Component


@Scope(value = "session")
@Component(value = "userService")
class UserService(val userDAO: UserDAO, val productService: ProductService) {

    fun addShList(shList: ShoppingListDTO): User {
        var user = get(shList.ownerId)
        val newShList = ShoppingList(itsOwner = user)
        newShList.addList(shList.items.map { i -> fetchItem(i,newShList) } as MutableList<Item>)
        user.addShoppingList(newShList)
        return userDAO.save(user)
    }

    private fun fetchItem(i: ItemDTO, ownerList :ShoppingList): Item {
        return Item(i.amount,i.measure,productService.getById(i.productId),ownerList)
    }

    fun getOrsave(dto: UserDTO): User {
        if(userDAO.existByEmail(dto.email))
            return userDAO.getByEmail(dto.email)
        else
            return userDAO.save(User(dto.fullName,dto.email,dto.photoURL))
    }

    fun get(id: Long): User {
        checkIfUserExists(id)
        return userDAO.get(id)
    }

    private fun checkIfUserExists(id: Long) {
        if(!userDAO.existById(id))
            throw NotFoundException("No existe usuario con ese ID")
    }

    fun closeShList(userId: Long, shListId: Long, pendingIds: List<Long>): User {
        val user = get(userId)
        val shList = user.shLists.find { s -> s.getId() == shListId } ?: throw NotFoundException("No se encontro la lista")
        shList.list.map { i -> putInPendingIfBelongTo(pendingIds, i) }
        shList.close()
        return userDAO.save(user)
    }

    private fun putInPendingIfBelongTo(pendingIds: List<Long>, i: Item): Item {
        if(pendingIds.contains(i.getId()))
            i.setPending()
        return i
    }
}
