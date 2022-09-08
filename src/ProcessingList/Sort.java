package ProcessingList;

public class Sort {
    public static int partition(ProcessingList pl, int lb, int ub) {
        float pivot = pl.get(lb).getUnitPrice();
        int start = lb;
        int end = ub;

        while (start < end) {

            while (pl.get(start).getUnitPrice() <= pivot && start < pl.getSize() - 1) {
                start++;
            }

            while (pl.get(end).getUnitPrice() > pivot && end >= 0) {
                end--;
            }

            if (start < end) {
//                Node temp = pl.get(end);
                String temp_barcode_id = pl.get(end).getBarcodeId();
                String temp_item_id = pl.get(end).getItemId();
                String temp_item_name = pl.get(end).getItemName();
                float temp_unit_price = pl.get(end).getUnitPrice();
                int temp_quantity = pl.get(end).getQuantity();

                pl.get(end).setBarcodeId(pl.get(start).getBarcodeId());
                pl.get(end).setItemId(pl.get(start).getItemId());
                pl.get(end).setItemName(pl.get(start).getItemName());
                pl.get(end).setUnitPrice(pl.get(start).getUnitPrice());
                pl.get(end).setQuantity(pl.get(start).getQuantity());

                pl.get(start).setBarcodeId(temp_barcode_id);
                pl.get(start).setItemId(temp_item_id);
                pl.get(start).setItemName(temp_item_name);
                pl.get(start).setUnitPrice(temp_unit_price);
                pl.get(start).setQuantity(temp_quantity);
            }
        }
//        Node temp = pl.get(end);
        String temp_barcode_id = pl.get(end).getBarcodeId();
        String temp_item_id = pl.get(end).getItemId();
        String temp_item_name = pl.get(end).getItemName();
        float temp_unit_price = pl.get(end).getUnitPrice();
        int temp_quantity = pl.get(end).getQuantity();
//        elements[end] = elements[lb];
        pl.get(end).setBarcodeId(pl.get(lb).getBarcodeId());
        pl.get(end).setItemId(pl.get(lb).getItemId());
        pl.get(end).setItemName(pl.get(lb).getItemName());
        pl.get(end).setUnitPrice(pl.get(lb).getUnitPrice());
        pl.get(end).setQuantity(pl.get(lb).getQuantity());
//        elements[lb] = temp;
        pl.get(lb).setBarcodeId(temp_barcode_id);
        pl.get(lb).setItemId(temp_item_id);
        pl.get(lb).setItemName(temp_item_name);
        pl.get(lb).setUnitPrice(temp_unit_price);
        pl.get(lb).setQuantity(temp_quantity);
//        System.out.println(temp.getItemName());
        return end;
    }


    public static void quickSort(ProcessingList items, int lb, int ub) {
        if (items.getSize() <= 1) {
            return;
        }
        int loc;
        if (lb < ub) {
            loc = partition(items, lb, ub);
            quickSort(items, lb, loc - 1);
            quickSort(items, loc + 1, ub);


        }
    }

//    public static void  selectionSort(int[] elements) {
//        int border = -1;
//        int min = -1;
//        int minimum_position = 0;
//        for (int i = 0; i < elements.length - 1; i++ ) {
//            for (int j = border+1; j < elements.length; j++) {
//                if (j == border + 1) {
//                    min = elements[j];
//                    minimum_position = j;
//                } else if (elements[j] < min){
//                    min = elements[j];
//                    minimum_position = j;
//                }
//            }
//            elements[minimum_position] = elements[i];
//            elements[i] = min;
//            border++;
//
//
//        }
//    }
    public static void selectionSort(ProcessingList pl) {
        for (int i = 0; i < pl.getSize() - 1; i++) {
            int min = i;
            for (int j = i +1; j < pl.getSize(); j++) {
                if (pl.get(j).getUnitPrice() < pl.get(min).getUnitPrice()) {
                    min = j;
                }
            }
            if (min != i) {
                String temp_barcode_id = pl.get(min).getBarcodeId();
                String temp_item_id = pl.get(min).getItemId();
                String temp_item_name = pl.get(min).getItemName();
                float temp_unit_price = pl.get(min).getUnitPrice();
                int temp_quantity = pl.get(min).getQuantity();

                pl.get(min).setBarcodeId(pl.get(i).getBarcodeId());
                pl.get(min).setItemId(pl.get(i).getItemId());
                pl.get(min).setItemName(pl.get(i).getItemName());
                pl.get(min).setUnitPrice(pl.get(i).getUnitPrice());
                pl.get(min).setQuantity(pl.get(i).getQuantity());

                pl.get(i).setBarcodeId(temp_barcode_id);
                pl.get(i).setItemId(temp_item_id);
                pl.get(i).setItemName(temp_item_name);
                pl.get(i).setUnitPrice(temp_unit_price);
                pl.get(i).setQuantity(temp_quantity);

            }

        }
    }
}
