import java.util.LinkedHashMap;
import java.util.Map;

public class ShippingService
{
    private Map<Shippable, Integer> shipment;
    private double shipping;

    public ShippingService()
    {
        shipment = new LinkedHashMap<>();
        shipping = 0;
    }

    public void putProduct(Product product, int quantity)
    {
        shipment.put((Shippable) product, quantity);
        shipping += ((Shippable) product).getWeight() * quantity * 10;
    }

    public double getShipping()
    {
        return shipping;
    }

    public void ship()
    {
        if (shipment.isEmpty())
        {
            return;
        }

        System.out.println("** Shipment notice **");
        double totalWeight = 0;
        for (Map.Entry<Shippable, Integer> entry : shipment.entrySet())
        {
            Shippable item = entry.getKey();
            int count = entry.getValue();
            double weight = count * item.getWeight();
            totalWeight += weight;
            System.out.printf("%dx %s %.0fg\n", count, item.getName(), weight * 1000);
        }
        System.out.printf("Total package weight %.1fkg\n", totalWeight);
    }
}
