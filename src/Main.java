import java.time.LocalDate;

public class Main
{
    public static void main(String[] args)
    {
        // Valid products
        Product cheese = new Cheese("Cheese", 100, 10, LocalDate.now().plusDays(5), 0.2);
        Product biscuits = new Biscuits("Biscuits", 150, 5, LocalDate.now().plusDays(2), 0.7);
        Product tv = new TV("TV", 5000, 3, 10);
        Product scratchCard = new MobileCard("Scratch Card", 50, 20);

        // Expired product
        Product expiredCheese = new Cheese("Old Cheese", 80, 5, LocalDate.now().minusDays(1), 0.3);

        // Case: Valid products, enough quantity, sufficient balance
        try
        {
            System.out.println("\n=== Case 1: Normal Checkout ===");
            Cart cart1 = new Cart();
            cart1.add(cheese, 2);
            cart1.add(biscuits, 1);
            Customer customer1 = new Customer("Mohammed", 1000);
            CheckoutService.checkout(customer1, cart1);
        }
        catch (Exception e)
        {
            System.out.println("Error: " + e.getMessage());
        }

        // Case: Expired product
        try
        {
            System.out.println("\n=== Case 2: Expired Product ===");
            Cart cart2 = new Cart();
            cart2.add(expiredCheese, 1);
            Customer customer2 = new Customer("Ali", 1000);
            CheckoutService.checkout(customer2, cart2);
        }
        catch (Exception e)
        {
            System.out.println("Error: " + e.getMessage());
        }

        // Case: Requested quantity exceeds available stock
        try
        {
            System.out.println("\n=== Case 3: Quantity Exceeds Stock ===");
            Cart cart3 = new Cart();
            cart3.add(tv, 5); // Only 3 in stock
            Customer customer3 = new Customer("Ahmed", 100000);
            CheckoutService.checkout(customer3, cart3);
        }
        catch (Exception e)
        {
            System.out.println("Error: " + e.getMessage());
        }

        // Case: Insufficient balance
        try
        {
            System.out.println("\n=== Case 4: Insufficient Balance ===");
            Cart cart4 = new Cart();
            cart4.add(tv, 1); // Price is 5000
            Customer customer4 = new Customer("Omar", 1000); // Low balance
            CheckoutService.checkout(customer4, cart4);
        } catch (Exception e)
        {
            System.out.println("Error: " + e.getMessage());
        }

        // Case: Empty cart
        try
        {
            System.out.println("\n=== Case 5: Empty Cart ===");
            Cart cart5 = new Cart();
            Customer customer5 = new Customer("Kareem", 1000);
            CheckoutService.checkout(customer5, cart5);
        }
        catch (Exception e)
        {
            System.out.println("Error: " + e.getMessage());
        }

        // Case: Product that does not require shipping
        try
        {
            System.out.println("\n=== Case 6: No Shipping Needed ===");
            Cart cart6 = new Cart();
            cart6.add(scratchCard, 2); // No shipping required
            Customer customer6 = new Customer("Nour", 1000);
            CheckoutService.checkout(customer6, cart6);
        }
        catch (Exception e)
        {
            System.out.println("Error: " + e.getMessage());
        }
    }
}