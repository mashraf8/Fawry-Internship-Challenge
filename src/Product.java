public abstract class Product
{
    protected String name;
    protected double price;
    protected int quantity;

    public Product(String name, double price, int quantity)
    {
        this.name = name;
        this.price = price;
        this.quantity = quantity;
    }

    public String getName()
    {
        return name;
    }
    public double getPrice()
    {
        return price;
    }
    public int getQuantity()
    {
        return quantity;
    }
    public void inStock(int qty)
    {
        if (qty > getQuantity())
        {
            throw new IllegalStateException(getName() + " is out of stock.");
        }
    }
    public void reduceQuantity(int amount)
    {
        this.quantity -= amount;
    }

}
