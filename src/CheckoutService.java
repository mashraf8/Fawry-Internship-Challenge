import java.util.Map;
import java.util.LinkedHashMap;

public class CheckoutService
{
    public static void checkout(Customer customer, Cart cart)
    {
        cart.assertNotEmpty();

        double subtotal = 0;
        ShippingService shipment = new ShippingService();


        for (Map.Entry<Product, Integer> entry : cart.getItems().entrySet())
        {
            Product product = entry.getKey();
            int qty = entry.getValue();

            product.inStock(qty);

            if (product instanceof Expirable && ((Expirable) product).isExpired())
            {
                throw new IllegalStateException(product.getName() + " is expired.");
            }

            if (product instanceof Shippable)
            {
                shipment.putProduct(product, qty);
            }

            subtotal += qty * product.getPrice();
            product.reduceQuantity(qty);
        }

        double total = subtotal + shipment.getShipping();
        customer.deduct(total);

        //Print Shipping
        shipment.ship();


        //Print Receipt
        System.out.println("** Checkout receipt **");
        for (Map.Entry<Product, Integer> entry : cart.getItems().entrySet())
        {
            System.out.printf("%dx %s %.0f\n", entry.getValue(), entry.getKey().getName(), entry.getKey().getPrice() * entry.getValue());
        }
        System.out.println("----------------------");
        System.out.printf("Subtotal %.0f\n", subtotal);
        System.out.printf("Shipping %.0f\n", shipment.getShipping());
        System.out.printf("Amount %.0f\n", total);
        System.out.printf("Customer Balance After Payment %.0f\n", customer.getBalance());
    }
}
