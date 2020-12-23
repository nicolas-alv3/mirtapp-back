import com.mirtapp.mirtapp.model.*
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import kotlin.test.assertEquals
import kotlin.test.assertNotEquals

class ShoppingListTest {

    lateinit var shList:ShoppingList;
    lateinit var product:Product;

    @BeforeEach
    fun setup(){
        shList = ShoppingList()
        product = Product("Pitusa",10.0,ProductCategory.ALMACEN)
    }

    @Test fun `given an empty shopping list the price is zero`(){
        assertEquals(0.0, shList.getTotalPrice())
    }

    @Test fun `given an empty shopping list the length is zero`(){
        assertEquals(0, shList.getLength())
    }

    @Test fun `given an empty shopping list when iAdd aList of one product then it has length one`(){
        val p1 = Item(1,ProductMeasure.UNIDAD, Product())
        shList.addList(mutableListOf(p1))
        assertEquals(1, shList.getLength())
    }

    @Test fun `when add a new Item with 10 amount and a product of 10 price, the total price of the item is 100`(){
        val item = Item(10,ProductMeasure.UNIDAD, Product(price = 10.0))
        assertEquals(100.0, item.price())
    }

    @Test fun `a new Item has zero amount and measure caja`(){
        assertEquals(0,Item().amount)
        assertEquals(ProductMeasure.CAJA,Item().measure)
    }

    @Test fun `a new shopping list starts in open state`(){
        assert(shList.state.isOpen())
    }

    @Test fun `given aNew shopping list when it is closed its state is closed`(){
        shList.close()

        assertEquals(false,shList.state.isOpen())
    }

    @Test fun `given aNew shopping list all their items are not pending`(){
        assertEquals(false,shList.list.any { i -> i.isPending })
    }

    @Test fun `given aShopping list with two items when i Put the first in pendig then it is`(){
        val item1 = Item(10,ProductMeasure.UNIDAD,product)
        item1.setId(1L)
        val list = mutableListOf<Item>(item1,Item(20,ProductMeasure.UNIDAD,product))
        shList.addList(list)

        shList.putInPending(1L)

        assert(shList.list.find { i -> i.getId() == 1L }!!.isPending)
    }

    @Test fun `once the shopping list is closed cannot change items to pending`(){
        val item1 = Item(10,ProductMeasure.UNIDAD,product)
        val hash = item1.hashCode()
        val list = mutableListOf<Item>(item1,Item(20,ProductMeasure.UNIDAD,product))
        shList.addList(list)

        shList.close()
        shList.putInPending(1L)

        assertEquals(false, shList.list.find { i ->i.hashCode().toInt() == hash }!!.isPending)
    }

    @Test fun `when i Close the shopping list then the products inside are different instances`(){
        val item1 = Item(10,ProductMeasure.UNIDAD,product)
        val hash1 = product.hashCode()
        val list = mutableListOf<Item>(item1,Item(20,ProductMeasure.UNIDAD,product))
        shList.addList(list)

        shList.close()

        assertNotEquals(shList.list.get(0).hashCode(),hash1)
    }

    @Test fun `when i try ti close the shopping list in open state the state is still open`(){
        val item1 = Item(10,ProductMeasure.UNIDAD,product)
        val hash1 = product.hashCode()
        val list = mutableListOf<Item>(item1,Item(20,ProductMeasure.UNIDAD,product))
        shList.addList(list)

        shList.state.close(shList)

        assert(shList.state.isOpen())
    }
}