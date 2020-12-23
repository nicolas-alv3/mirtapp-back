import com.mirtapp.mirtapp.model.*
import org.junit.jupiter.api.Test
import kotlin.test.assertEquals

class UserTest {
    val user = User("Nicolas Alvarez", "example@example.com","anURL")
    val cMD = User("Claudio Maria Dominguez", "example@example.com","anURL")
    val product = Product("d",10.0,ProductCategory.ALMACEN)
    val item = Item(1,ProductMeasure.UNIDAD,product)

    @Test fun `a new user set properties correctly`(){
        val newUser = User("name","email","photo")
        assertEquals("name",newUser.fullName)
        assertEquals("email",newUser.email)
        assertEquals("photo",newUser.photoURL)
    }

    @Test fun `a new user has zero shopping lists`(){
        assertEquals(0,user.getShListLength())
    }

    @Test fun `get first name from nicolas alvarez is nicolas`(){
        assertEquals("Nicolas",user.getFirstName())
    }

    @Test fun `get first name from claudio maria dominguez is claudio`(){
        assertEquals("Claudio",cMD.getFirstName())
    }

    @Test fun `get last name from nicolas alvarez is alvarez`(){
        assertEquals("Alvarez",user.getLastName())
    }

    @Test fun `get last name from claudio maria dominguez is dominguez`(){
        assertEquals("Dominguez",cMD.getLastName())
    }

    @Test fun `user adds aNew sh list then has one sh list`(){
        val shList = ShoppingList(
                mutableListOf(item)
        )
        user.addShoppingList(shList)
        assertEquals(1,user.getShListLength())
    }

    @Test fun `user with one sh list delete it then it has zero again`(){
        val shList = ShoppingList(
                mutableListOf(item)
        )

        user.deleteShoppingList(shList)

        assertEquals(0,user.getShListLength())
    }

    @Test fun `a new user starts with no pending items`(){
        assertEquals(0,User().pendingItems.size)
    }

    @Test fun `a new user close aShopping list with one pending item then the user has one pending item`(){
        val shList = ShoppingList(itsOwner = user)
        val pendingItem = Item(10,ProductMeasure.CAJA,Product(),shList,true)
        shList.addList(mutableListOf(item, pendingItem))

        user.addShoppingList(shList)

        shList.close()

        assertEquals(1,user.pendingItems.size)
    }

}