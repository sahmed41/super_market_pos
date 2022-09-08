//package SystemComponents;
//
//import ProcessingList.Node;
//
//public class CustomerList {
//
//    private Customer head;
//    private Customer tail;
//
//    public CustomerList() {
//        head = null;
//        tail = null;
//    }
//
//
//    //    This method sets the tail
//    private void setTail () {
//        Customer current_node = head;
//        while (current_node.getNext() != null) {
//            current_node = current_node.getNext();
//        }
//        tail = current_node;
//    }
//
//    //    getters
//    public Customer getHead() {
//        return head;
//    }
//
//    public Customer getTail() {
//        return tail;
//    }
//
//
//
//    //    This method adds items to the rear of the list
//    public void addRear(String customer_id, BarcodeReader br) {
//        Customer new_node = new Customer(customer_id, br);
//        if (head == null) {
//            head = new_node;
//        } else {
//            Customer current_node = head;
//            while (current_node.getNext() != null) {
//                current_node = current_node.getNext();
//            }
////            current_node.next = new_node;
//            current_node.setNext(new_node);
//        }
//        this.setTail(); // After adding to rear this sets the tail
//    }
//
//    //    This method checks whether the list is empty
//    public boolean isEmpty() {
//        if (head == null) {
//            return true;
//        } else {
//            return false;
//        }
//    }
//
//    //    this method gets the size of the Processing list
//    public int getSize() {
//        Customer current_node = head;
//        int size = 0;
//        while (current_node != null) {
//            size = size + 1;
//            current_node = current_node.getNext();
//        }
//        return size;
//    }
//
//
//    //    This method prints every element
////    public void iterate() {
////        Customer current_node = head;
////        while (current_node != null) {
////            if (current_node.getNext() == null) {
////                System.out.println(current_node.getItemName() + "\t" + current_node.getUnitPrice()+ "\t" + current_node.getQuantity());
////            } else {
////                System.out.println(current_node.getItemName() + "\t" + current_node.getUnitPrice()+ "\t" + current_node.getQuantity());
////            }
////            current_node = current_node.getNext();
////        }
////    }
//
//    //    This method returns a node in the index
//    public Customer get(int index) {
//        Customer current_node = head;
//        int position = 0;
//        while (current_node != null) {
//            if (index == position) {
//                return current_node;
//            }
//            current_node = current_node.getNext();
//            position = position + 1;
//        }
//        return current_node;
//    }
//}
