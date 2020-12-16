import com.mirtapp.mirtapp.model.*
import org.junit.jupiter.api.Test
import kotlin.test.assertEquals

class UserTest {
    val user = User("Nicolas Alvarez", "example@example.com","anURL")
    val cMD = User("Claudio Maria Dominguez", "example@example.com","anURL")
    val product = Product("d",10.0,ProductCategory.ALMACEN)
    val item = Item(1,ProductMeasure.UNIDAD,product)

    @Test fun aNewUserHasZeroShoppingLists(){
        assertEquals(0,user.getShListLength())
    }

    @Test fun getFirstNameFromNicolasAlvarezIsNicolas(){
        assertEquals("Nicolas",user.getFirstName())
    }

    @Test fun getFirstNameFromClaudioMariaDominguezIsClaudio(){
        assertEquals("Claudio",cMD.getFirstName())
    }

    @Test fun getLastNameFromNicolasAlvarezIsAlvarez(){
        assertEquals("Alvarez",user.getLastName())
    }

    @Test fun getLastNameFromClaudioMariaDominguezIsDominguez(){
        assertEquals("Dominguez",cMD.getLastName())
    }

    @Test fun userAddsANewShListThenHasOneShList(){
        val shList = ShoppingList(
                mutableListOf(item)
        )
        user.addShoppingList(shList)
        assertEquals(1,user.getShListLength())
    }

    @Test fun userWithOneShListDeleteItThenItHasZeroAgain(){
        val shList = ShoppingList(
                mutableListOf(item)
        )

        user.deleteShoppingList(shList)

        assertEquals(0,user.getShListLength())
    }

}