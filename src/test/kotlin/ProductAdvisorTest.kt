import com.mirtapp.mirtapp.model.*
import com.mirtapp.mirtapp.service.builder.UserBuilder
import org.junit.jupiter.api.Test
import kotlin.test.assertEquals

class ProductAdvisorTest(){

    val advisor = ItemAdvisor()
    val build : UserBuilder = UserBuilder()
    val lujan = Provider("Distribuidora Lujan")
    val laReal = Provider("La Real")

    @Test fun `aNew user has no suggestion`(){
        val aNewUser = build.aUser()

        assertEquals(advisor.getSuggestedItemsFor(aNewUser, lujan).size,0)
    }

    @Test fun `aNew user who has one pending product has that product as aSuggestion because the product can be bought in lujan`(){
        val product = Product("p1",providers = mutableListOf(lujan))
        val item = Item(10,ProductMeasure.UNIDAD,product,isPending = true)
        val aNewUser = build.aUser()
        aNewUser.addToPendings(mutableListOf(item))

        assertEquals(advisor.getSuggestedItemsFor(aNewUser, lujan).size,1)
        assertEquals(advisor.getSuggestedItemsFor(aNewUser, lujan).get(0).product,product)
    }

    @Test fun `a New user who has one pending item has no products as a Suggestion because the product cant be bought in lujan`(){
        val product = Product("p1",providers = mutableListOf(laReal))
        val item = Item(10,ProductMeasure.UNIDAD,product,isPending = true)
        val aNewUser = build.aUser()
        aNewUser.addToPendings(mutableListOf(item))

        assertEquals(advisor.getSuggestedItemsFor(aNewUser, lujan).size,0)
    }

    @Test fun `given aUser with one pending item when close a Shlist with that product then has no pendings`(){
        // A user with one pending item
        val product = Product("p1",providers = mutableListOf(lujan))
        val item = Item(10,ProductMeasure.UNIDAD,product,isPending = true)
        val aNewUser = build.aUser()
        aNewUser.addToPendings(mutableListOf(item))

        val newItem = Item(20,ProductMeasure.UNIDAD,product)
        val shoppingList = ShoppingList(mutableListOf(newItem),aNewUser)
        aNewUser.addShoppingList(shoppingList)
        shoppingList.close()

        assertEquals(advisor.getSuggestedItemsFor(aNewUser, lujan).size,0)
    }

    @Test fun `given a user with two pendig items, when one of them is bought enough,the user has only one item pending`(){
        // A user with two pending item
        val product = Product("p1",providers = mutableListOf(lujan))
        val item = Item(10,ProductMeasure.UNIDAD,product,isPending = true)
        val dummyItem = Item(1,ProductMeasure.UNIDAD, Product("p2",providers = mutableListOf(lujan)),isPending = true)
        val aNewUser = build.aUser()
        aNewUser.addToPendings(mutableListOf(item,dummyItem))

        val newItem = Item(20,ProductMeasure.UNIDAD,product)
        val shoppingList = ShoppingList(mutableListOf(newItem),aNewUser)
        aNewUser.addShoppingList(shoppingList)
        shoppingList.close()

        assertEquals(advisor.getSuggestedItemsFor(aNewUser, lujan).size,1)
    }
}