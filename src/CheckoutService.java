import java.util.Map;
import java.util.LinkedHashMap;

public class CheckoutService
{
    public static void checkout(Customer customer, Cart cart)
    {
        if (cart.isEmpty())
        {
            throw new IllegalStateException("Cart is empty.");
        }

        double subtotal = 0;
        double shipping = 0;
        LinkedHashMap<Shippable, Integer> shipment = new LinkedHashMap<>();

        for (Map.Entry<Product, Integer> entry : cart.getItems().entrySet())
        {
            Product product = entry.getKey();
            int qty = entry.getValue();

            if (product instanceof Expirable && ((Expirable) product).isExpired())
            {
                throw new IllegalStateException(product.getName() + " is expired.");
            }

            if (qty > product.getQuantity())
            {
                throw new IllegalStateException(product.getName() + " is out of stock.");
            }

            subtotal += qty * product.getPrice();
            product.reduceQuantity(qty);

            if (product instanceof Shippable)
            {
                shipment.put((Shippable) product, qty);
                shipping += ((Shippable) product).getWeight() * qty * 10;
            }
        }

        double total = subtotal + shipping;

        if (customer.getBalance() < total)
        {
            throw new IllegalStateException("Customer insufficient balance.");
        }

        customer.deduct(total);

        if (!shipment.isEmpty())
        {
            ShippingService.ship(shipment);
        }

        System.out.println("** Checkout receipt **");
        for (Map.Entry<Product, Integer> entry : cart.getItems().entrySet())
        {
            System.out.printf("%dx %s %.0f\n", entry.getValue(), entry.getKey().getName(), entry.getKey().getPrice() * entry.getValue());
        }
        System.out.println("----------------------");
        System.out.printf("Subtotal %.0f\n", subtotal);
        System.out.printf("Shipping %.0f\n", shipping);
        System.out.printf("Amount %.0f\n", total);
        System.out.printf("Customer Balance After Payment %.0f\n", customer.getBalance());
    }
}
