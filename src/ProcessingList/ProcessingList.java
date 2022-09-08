package ProcessingList;

import javax.swing.plaf.IconUIResource;
import java.util.Locale;

public class ProcessingList {
    private Node head;
    private Node tail;
    private static ProcessingList pl = new ProcessingList();

    private ProcessingList() {
        head = null;
        tail = null;
    }

//    This method will instntiate a universal processing list
    public static ProcessingList getInstance () {
        return pl;
    }

//    setters
//    public void setHead(Node head) {
//        this.head = head;
//    }

//    public void setTail(Node tail) {
//        this.tail = tail;
//    }

    //    This method sets the tail
    private void setTail () {
        Node current_node = head;
        while (current_node.getNext() != null) {
            current_node = current_node.getNext();
        }
        tail = current_node;
    }

//    getters
    public Node getHead() {
        return head;
    }

    public Node getTail() {
        return tail;
    }



//    This method checks whether the list is empty
    public boolean isEmpty() {
        if (head == null) {
            return true;
        } else {
            return false;
        }
    }

//    this method gets the size of the Processing list
    public int getSize() {
        Node current_node = head;
        int size = 0;
        while (current_node != null) {
            size = size + 1;
            current_node = current_node.getNext();
        }
        return size;
    }



//    This method returns a node in the index
    public Node get(int index) {
        Node current_node = head;
        int position = 0;
        while (current_node != null) {
            if (index == position) {
                return current_node;
            }
            current_node = current_node.getNext();
            position = position + 1;
        }
        return current_node;
    }



//       This method add items to the Front
    public void addFront(String barcode_id, String item_id, String item_name, float unit_price, int quantity) {
        Node new_node = new Node(barcode_id, item_id, item_name, unit_price, quantity);
//        new_node.next = head;
        new_node.setNext(head);
        head = new_node;
        this.setTail(); // After adding to front this sets the tail
    }

// this method adds item to anywhere in the list.
    public void addMiddle(Node previous_node, String barcode_id, String item_id, String item_name, float unit_price, int quantity) {
        if (head == null) {
            System.out.println("Error: You cannot enter a new value to the middle of an empty list");
        } else {
            Node new_node = new Node(barcode_id, item_id, item_name, unit_price, quantity);
//            new_node.next = previous_node.next;
            try {
                new_node.setNext(previous_node.getNext());
            } catch (NullPointerException e) {
                System.out.println("Non-exixting previous node");
                return;
            }

//            previous_node.next = new_node;
            previous_node.setNext(new_node);

        }
        this.setTail(); // After adding to middle this sets the tail
    }

//    This method adds items to the rear of the list
    public void addRear(String barcode_id, String item_id, String item_name, float unit_price, int quantity) {
        Node new_node = new Node(barcode_id, item_id, item_name, unit_price, quantity);
        if (head == null) {
            head = new_node;
        } else {
            Node current_node = head;
            while (current_node.getNext() != null) {
                current_node = current_node.getNext();
            }
//            current_node.next = new_node;
            current_node.setNext(new_node);
        }
        this.setTail(); // After adding to rear this sets the tail
    }

//        this method will return the index of the searched item and returns its index and if it could not find any matching nodes it would return -1
    public int search(String barcode_id, String item_name) {
        Node current_node = head;
        int position = -1;
        boolean found = false;

        while (current_node != null) {
            position++;
            if (current_node.getBarcodeId().equals(barcode_id) && current_node.getItemName().toLowerCase().equals(item_name.toLowerCase())) {
                found = true;
                break;
            }
            current_node = current_node.getNext();
        }

        if (found == false) {
            position = -1;
        }
        return position;
    }

//    This method filters items using barcode id
    public TempList filterByBarcodeId(String barcode_id) {
        TempList tl = new TempList();
        Node current_node = head;
        int counter = 0;
        while (current_node != null) {
            if (current_node.getBarcodeId() == barcode_id) {
                tl.addRear(current_node.getBarcodeId(), current_node.getItemId(), current_node.getItemName(), current_node.getUnitPrice(), current_node.getQuantity());
//                System.out.println(current_node.getItemName());
            }
            current_node = current_node.getNext();
        }
        return tl;
    }

    //    This method filters items that are equal than a given unit price
    public TempList filterByUnitPrice(float unit_price) {
        TempList tl = new TempList();
        Node current_node = head;
        int counter = 0;
        while (current_node != null) {
            if (current_node.getUnitPrice() == unit_price) {
                tl.addRear(current_node.getBarcodeId(), current_node.getItemId(), current_node.getItemName(), current_node.getUnitPrice(), current_node.getQuantity());
//                System.out.println(current_node.getItemName());
            }
            current_node = current_node.getNext();
        }
        return tl;
    }

    //    This method filters items that are higher than a given unit price
    public TempList filterByMinUnitPrice(float min_unit_price) {
        TempList tl = new TempList();
        Node current_node = head;
        int counter = 0;
        while (current_node != null) {
            if (current_node.getUnitPrice() > min_unit_price) {
                tl.addRear(current_node.getBarcodeId(), current_node.getItemId(), current_node.getItemName(), current_node.getUnitPrice(), current_node.getQuantity());
//                System.out.println(current_node.getItemName());
            }
            current_node = current_node.getNext();
        }
        return tl;
    }

    //    This method filters items that are lesser than a given unit price
    public TempList filterByMaxUnitPrice(float max_unit_price) {
        TempList tl = new TempList();
        Node current_node = head;
        int counter = 0;
        while (current_node != null) {
            if (current_node.getUnitPrice() < max_unit_price) {
                tl.addRear(current_node.getBarcodeId(), current_node.getItemId(), current_node.getItemName(), current_node.getUnitPrice(), current_node.getQuantity());
//                System.out.println(current_node.getItemName());
            }
            current_node = current_node.getNext();
        }
        return tl;
    }

    //    This method filters items that are between a given unit price
    public TempList filterBetweenUnitPrice(float min_unit_price, float max_unit_price) {
        TempList tl = new TempList();
        Node current_node = head;
        int counter = 0;
        while (current_node != null) {
            if (current_node.getUnitPrice() < max_unit_price && current_node.getUnitPrice() > min_unit_price) {
                tl.addRear(current_node.getBarcodeId(), current_node.getItemId(), current_node.getItemName(), current_node.getUnitPrice(), current_node.getQuantity());
//                System.out.println(current_node.getItemName());
            }
            current_node = current_node.getNext();
        }
        return tl;
    }

//   This method deletes the head
    public Node deleteHead() {
        Node deleting_node = getHead();
        if (getHead().getNext() == null) {
            head = null;
        } else {
            head = head.getNext();
        }
        return deleting_node;
    }

//    This method deletes items from the list
    public Node delete(int deleting_position) {
        Node deleting_node = get(deleting_position);
        Node current_node = head;
        int position = 0;
        if (deleting_position == 0) {
            head = head.getNext();
        } else {
            while (current_node != null) {
                if (position == (deleting_position - 1)) {
                    current_node.setNext(current_node.getNext().getNext());
                }
                current_node = current_node.getNext();
                position = position + 1;
            }
        }
        this.setTail(); // After deleting this sets the tail
        return deleting_node;
    }

//    Deletes the tail
    public Node deleteTail() {
        Node current_node = getHead();
        while (current_node.getNext().getNext() != null) {
            current_node = current_node.getNext();
        }
//        Node deleting_node = new Node(current_node.getNext().getBarcodeId(), current_node.getNext().getItemId(), current_node.getItemName(), current_node.getNext().getUnitPrice(), current_node.getNext().getQuantity());
        Node deleting_node = current_node.getNext();
        current_node.setNext(null);
        this.setTail();
        return deleting_node;
    }

//    This methods prits everything that is added by a particular barcode
    public void print(String barcode_id) {
        Node current_node = head;
        boolean found = false;
        System.out.println("Item ID\t\t\tItem Name\t\t\tUnit Price\t\t\tQuantity");
        System.out.println("----------------------------------------------------------------");
        while (current_node != null) {
            if (current_node.getBarcodeId().equals(barcode_id)) {
                System.out.print(current_node.getItemId() + "\t\t\t");
                System.out.print(current_node.getItemName() + "\t\t\t");
                System.out.print(current_node.getUnitPrice() + "\t\t\t");
                System.out.print(current_node.getQuantity());
                System.out.println();
                found = true;

            }
            current_node = current_node.getNext();
        }
        if (found == false) {
            System.out.println("No items were purchased");
        }
    }

    //    This method prints every element
    public void iterate() {
        Node current_node = head;
        while (current_node != null) {
            if (current_node.getNext() == null) {
                System.out.println(current_node.getBarcodeId() + "\t" + current_node.getItemId() + "\t" + current_node.getItemName() + "\t" + current_node.getUnitPrice() + "\t" + current_node.getQuantity());
            } else {
                System.out.println(current_node.getBarcodeId() + "\t" + current_node.getItemId() + "\t" + current_node.getItemName() + "\t" + current_node.getUnitPrice() + "\t" + current_node.getQuantity());
            }
            current_node = current_node.getNext();
        }
    }

}
