package SystemComponents;

public class Product {
    private String product_id;
    private String getProduct_name;
    private float unit_price;

    public Product(String product_id, String product_name, float unit_price) {
        this.product_id = product_id;
        this.getProduct_name = product_name;
        this.unit_price = unit_price;
    }

//    Setters
    public void setProductId(String product_id) {
        this.product_id = product_id;
    }

    public void setProductName(String getProduct_name) {
        this.getProduct_name = getProduct_name;
    }

    public void setUnitPrice(float unit_price) {
        this.unit_price = unit_price;
    }

//    Getters
    public String getProductId() {
        return product_id;
    }

    public String getProductName() {
        return getProduct_name;
    }

    public float getUnitPrice() {
        return unit_price;
    }
}
