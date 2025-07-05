import java.util.Map;
import java.util.LinkedHashMap;

public class Cart
{
    private Map<Product, Integer> items;

    public Cart()
    {
        items = new LinkedHashMap<>();
    }

    public void add(Product product, int quantity)
    {
        product.inStock(quantity);
        items.put(product, items.getOrDefault(product, 0) + quantity);
    }

    public void assertNotEmpty()
    {
        if (items.isEmpty())
        {
            throw new IllegalStateException("Cart is empty.");
        }
    }

    public Map<Product, Integer> getItems()
    {
        return items;
    }
}
