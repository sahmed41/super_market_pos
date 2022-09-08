package ProcessingList;

public class TempList {
    private Node head;
    private Node tail;

    public TempList() {
        head = null;
        tail = null;
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


    //    This method prints every element
    public void iterate() {
        Node current_node = head;
        while (current_node != null) {
            if (current_node.getNext() == null) {
                System.out.println(current_node.getItemName() + "\t" + current_node.getUnitPrice()+ "\t" + current_node.getQuantity());
            } else {
                System.out.println(current_node.getItemName() + "\t" + current_node.getUnitPrice()+ "\t" + current_node.getQuantity());
            }
            current_node = current_node.getNext();
        }
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
}
