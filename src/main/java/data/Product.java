package data;

public class Product {
    private String name;
    private double price;
    private final boolean warranty;
    private String description;
    private int warrantyPrice;
    private int quantity = 1;
    private final int id;
    private int indexInArray;

    public Product(String name, boolean warranty, String description, int warrantyPrice, int id, int indexInArray) {
        this.name = name;
        this.warranty = warranty;
        this.description = description;
        this.warrantyPrice = warrantyPrice;
        this.id = id;
        this.indexInArray = indexInArray;
    }

    public boolean getWarranty() {
        return warranty;
    }

    public String getName() {
        return name;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getYearsGuaranty() {
        return warrantyPrice;
    }

    public void setYearsGuaranty(int yearsGuaranty) {
        this.warrantyPrice = yearsGuaranty;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public int getIndexInArray() { return indexInArray; }

    public void setWarrantyPrice(int warrantyPrice) {
            this.warrantyPrice = warrantyPrice;
    }
}
