import java.util.Map;
import java.util.LinkedHashMap;

public class Cart
{
    private Map<Product, Integer> items = new LinkedHashMap<>();

    public void add(Product product, int quantity)
    {
        if (quantity > product.getQuantity())
        {
            throw new IllegalStateException("Not enough quantity in stock.");
        }
        items.put(product, items.getOrDefault(product, 0) + quantity);
    }

    public boolean isEmpty()
    {
        return items.isEmpty();
    }
    public Map<Product, Integer> getItems()
    {
        return items;
    }
}
