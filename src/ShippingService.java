import java.util.Map;

public class ShippingService
{
    public static void ship(Map<Shippable, Integer> shipment)
    {
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
