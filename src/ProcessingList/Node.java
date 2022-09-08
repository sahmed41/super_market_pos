package ProcessingList;

public class Node {
    private String barcode_id;
    private String item_id;
    private String item_name;
    private float unit_price;
    private int quantity;
    private Node next;

    public Node(String barcode_id, String item_id, String item_name, float unit_price, int quantity) {
        this.barcode_id = barcode_id;
        this.item_id = item_id;
        this.item_name = item_name;
        this.unit_price = unit_price;
        this.quantity = quantity;
        next = null;
    }

//    Setters
    public void setBarcodeId(String barcode_id) {
        this.barcode_id = barcode_id;
    }

    public void setItemId(String item_id) {
        this.item_id = item_id;
    }

    public void setItemName(String item_name) {
        this.item_name = item_name;
    }

    public void setUnitPrice(float unit_price) {
        this.unit_price = unit_price;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public void setNext(Node next) {
        this.next = next;
    }

//    Getters
    public String getBarcodeId() {
        return barcode_id;
    }

    public String getItemId() {
        return item_id;
    }

    public String getItemName() {
        return item_name;
    }

    public float getUnitPrice() {
        return unit_price;
    }

    public int getQuantity() {
        return quantity;
    }

    public Node getNext() {
        return next;
    }


}
