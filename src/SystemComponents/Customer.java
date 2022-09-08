package SystemComponents;

import ProcessingList.ProcessingList;

public class Customer {
    private String customer_id;
    private BarcodeReader barcode_reader;

    public Customer(String customer_id, BarcodeReader br) {
        this.customer_id = customer_id;
        barcode_reader = br;
    }

//    Setters

    public void setCustomer_id(String customer_id) {
        this.customer_id = customer_id;
    }

    public void setBarcodeReader(BarcodeReader br) {
        this.barcode_reader = br;
    }

//    Getters
    public String getCustomer_id() {
        return customer_id;
    }

    public BarcodeReader getBarcodeReader() {
        return barcode_reader;
    }
//This method adds products to the purchasing list for buying
    public void addProduct(Product product, int quantity) {
        boolean product_found = false;
        ProcessingList.getInstance().addRear(getBarcodeReader().getId(),product.getProductId(),product.getProductName(), product.getUnitPrice(), quantity);
//        for (int i = 0; i < products_list.length; i++) {
//            if (item_id == products_list[i].getProductId()) {
//                pl.addRear(barcode_reader.getId(), products_list[i].getProductId(), products_list[i].getProductName(), products_list[i].getUnitPrice(), quantity);
//                product_found = true;
//            }
//        }
//        if (!product_found) {
//            System.out.println("Product is not available in the store");
//        }
    }

//    public void deleteProduct(String product_id, Product[] products_list) {
//        boolean product_found = false;
//        String product_name = "none";
//        for (int i = 0; i < products_list.length; i++) {
//            if (products_list[i].getProductId() == product_id ) {
//                product_name = products_list[i].getProductName();
//                product_found = true;
//            }
//        }
//
//        if (!product_found) {
//            System.out.println("The scanned code does not match any existing product in the database");
//            return;
//        }
//        ProcessingList pl = ProcessingList.getInstance();
//        int product_index = pl.search(barcode_reader.getId(), product_name);
//        if (product_index != -1) {
//            pl.delete(product_index);
//        } else {
//            System.out.println("You did not add this item to delete it.");
//        }
//    }

//    public void deleteProduct(String product_name) {
//        ProcessingList.getInstance().delete(ProcessingList.getInstance().search(getBarcodeReader().getId(), product_name));
//
//
//    }
    public void deleteProduct(String product_name) {
        int deleting_product_index = ProcessingList.getInstance().search(this.getBarcodeReader().getId(), product_name);
        if (deleting_product_index == 0) {
            ProcessingList.getInstance().deleteHead();
        } else if (deleting_product_index == (ProcessingList.getInstance().getSize() - 1)) {
            ProcessingList.getInstance().deleteTail();
        } else {
            ProcessingList.getInstance().delete(deleting_product_index);
        }


    }

//    public void deleteProduct(String product_name) {
//        ProcessingList.getInstance().delete(ProcessingList.getInstance().search(getBarcodeReader().getId(), product_name));
//
//    }
}
