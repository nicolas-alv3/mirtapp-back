import com.mirtapp.mirtapp.model.Item
import com.mirtapp.mirtapp.model.Product
import com.mirtapp.mirtapp.model.ProductMeasure
import com.mirtapp.mirtapp.model.ShoppingList
import org.junit.jupiter.api.Test
import kotlin.test.assertEquals

class ShoppingListTest {

    var shList = ShoppingList()

    @Test fun givenAnEmptyShoppingListThePriceIsZero(){
        assertEquals(0.0, shList.getTotalPrice())
    }

    @Test fun givenAnEmptyShoppingListTheLengthIsZero(){
        assertEquals(0, shList.getLength())
    }

    @Test fun givenAnEmptyShoppingListWhenIAddAListOfOneProductThenItHasLengthOne(){
        val p1 = Item(1,ProductMeasure.UNIDAD, Product())
        shList.addList(mutableListOf(p1))
        assertEquals(1, shList.getLength())
    }
}