package SystemComponents;

import ProcessingList.*;

public class Cashier {

//    public int calculateBill(String barcode_id) {
//        TempList tl = new TempList();
//        int total = 0;
//        tl = ProcessingList.getInstance().filterByBarcodeId(barcode_id);
//        Node current_node = tl.getHead();
//        while (current_node != null) {
//            float amount = current_node.getUnitPrice() * current_node.getQuantity();
//            total += amount;
//            current_node = current_node.getNext();
//        }
//        return total;
//
//    }
//    public float calculateBill(TempList products) {
//        float total = 0;
//        Node current_node = products.getHead();
//        while (current_node != null) {
//            float amount = current_node.getUnitPrice() * current_node.getQuantity();
//            total += amount;
//            current_node = current_node.getNext();
//        }
//        return total;
//    }
    public float calculateBill(TempList products) {
        Node current_node = products.getHead();
        if (current_node.getNext() == null) {
            return  current_node.getUnitPrice() * current_node.getQuantity();
        } else {
            float total = 0;
            while (current_node != null) {
                float amount = current_node.getUnitPrice() * current_node.getQuantity();
                total += amount;
                current_node = current_node.getNext();
            }
            return total;
        }

    }


    public void quickSortProcessingList() {
        Sort.quickSort(ProcessingList.getInstance(), 0, ProcessingList.getInstance().getSize()-1);
    }
    public void selectionSortProcessingList() {
        Sort.selectionSort(ProcessingList.getInstance());
    }

    public void clearAfterBilling(String barcode_reader_id) {
        Node current_node = ProcessingList.getInstance().filterByBarcodeId(barcode_reader_id).getHead();
        while (current_node != null) {
            ProcessingList.getInstance().delete(ProcessingList.getInstance().search("Br001", current_node.getItemName()));
            current_node  = current_node.getNext();
        }
    }
}
