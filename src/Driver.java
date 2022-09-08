import ProcessingList.*;
import SystemComponents.*;

import javax.sound.midi.Soundbank;
import java.sql.SQLOutput;
import java.util.InputMismatchException;
import java.util.Scanner;

public class Driver {

    public static void main(String[] args) {
        Product[] products = new Product[10];

        BarcodeReader[] brs = new BarcodeReader[10];
        brs[0] = new BarcodeReader("Br001");
        brs[1] = new BarcodeReader("Br002");
        brs[2] = new BarcodeReader("Br003");
        brs[3] = new BarcodeReader("Br004");
        brs[4] = new BarcodeReader("Br005");
        brs[5] = new BarcodeReader("Br006");
        brs[6] = new BarcodeReader("Br007");
        brs[7] = new BarcodeReader("Br008");
        brs[8] = new BarcodeReader("Br009");
        brs[9] = new BarcodeReader("Br010");

        Customer[] customers = new Customer[13];
        products[0] = new Product("it_001", "Custard 100g", 325);
        products[1] = new Product("it_002", "Vanilla Cake 400g Box", 1250);
        products[2] = new Product("it_003", "Rise 10Kg Pack", 1020);
        products[3] = new Product("it_004", "Rise 20Kg Pack", 2020);
        products[4] = new Product("it_053", "Chocoloate", 300);
        products[5] = new Product("it_033", "Maliban Cream Cracker 100g", 300 );
        products[6] = new Product( "it_054", "Vanilla Serieal 150g", 230);
        products[7] = new Product("it_058", "Mint Chip Ice Cream", 450);
        products[8]= new Product( "it_058", "Sugar 3Kg", 300);
        products[9] = new Product( "it_058", "KingGreen Tea 1Kg", 300);
//        products[3] = new Product("it_003", "Rise 10Kg Pack", 1020);

        ProcessingList pl1 = ProcessingList.getInstance();
        pl1.addRear("Br001", "it_003", "Rise 10Kg Pack", 1020, 3);
        pl1.addRear("Br001", "it_004", "Rise 20Kg Pack", 2020, 3);
        pl1.addRear("Br002", "it_033", "Maliban Cream Cracker 100g", 300, 2);
        pl1.addRear("Br001", "it_053", "Chocoloate", 300, 2);
        pl1.addRear("Br002", "it_054", "Vanilla Serieal 150g", 230, 1);
        pl1.addRear("Br001", "it_058", "Mint Chip Ice Cream", 450, 3);
        pl1.addRear("Br002", "it_058", "Sugar 3Kg", 300, 3);
        pl1.addRear("Br003", "it_058", "KingGreen Tea 1Kg", 300, 3);
        pl1.addRear("Br001", "it_058", "KingGreen Tea 1Kg", 300, 3);


        System.out.println("Welcome to The POS System");
        System.out.println("=========================");
        int customer_choice = -1;
//
        while (customer_choice != 0 ) {
            System.out.print("Enter 1 for customer operations, enter 2 for cashier operations, and enter 0 to exit: ");
            try {
                Scanner myObj = new Scanner(System.in);
                customer_choice = myObj.nextInt();  // Read user input
            } catch (InputMismatchException e) {
                System.out.println();
                System.out.println("Please enter a number such as (1,2,3) and not a character such as (a,b,c)");
                continue;
            }
//
            if (customer_choice == 1) {
                customer(products, customers, brs);
            } else if (customer_choice == 2) {
                cashier(brs);
            } else if (customer_choice == 0) {
                System.out.println("Exiting....");
            }
        }

    }

    public static void customer(Product[] products, Customer[] customers, BarcodeReader[] brs) {
        System.out.println("Welcome Customer!");
//        Pickup a barcode reader
        for (int i = 0; i < brs.length; i++) {
            System.out.println(i + 1 + ") " + brs[i].getId());
        }
        boolean non_existent_barcode = true; // Prevent picking up out of bound barcode reader
        Customer cus001 = new Customer("cus001", new BarcodeReader("000"));
        int barcode_choice = -1;
        while (non_existent_barcode) {
            try {
                System.out.print("Please, pick a barcode reader: ");
                Scanner barcode_reader_picker = new Scanner(System.in);
                barcode_choice = barcode_reader_picker.nextInt();
                cus001 = new Customer("cus001", brs[barcode_choice-1]);
                non_existent_barcode = false;
            } catch (ArrayIndexOutOfBoundsException e) {
                System.out.println("Pick the barcode that exist in the list");
            }

        }
        System.out.println();






        int customer_choice = -1;
        while (customer_choice != 0) {

            System.out.println("Please follow the instructions!");
            System.out.println("Enter 1 to add products");
            System.out.println("Enter 2 to delete added products");
            System.out.println("Enter 3 to view the products you have added.");
            System.out.println("Enter 4 to search for a product you have added.");
            System.out.print("Enter 0 to exit to previous menu: ");
            try {
                Scanner myObj = new Scanner(System.in);
                customer_choice = myObj.nextInt();  // Read user input
            } catch (InputMismatchException e) {
                System.out.println();
                System.out.println("Please enter a number such as (1,2,3) and not a character such as (a,b,c)");
                continue;
            }
//            System.out.println(customer_choice);
            switch (customer_choice) {
                case 1: // Addting product to the list
                    System.out.println();
                    for (int i = 0; i < products.length; i++) {
                        System.out.println(i+1 + ")" + products[i].getProductName() + " " +  products[i].getUnitPrice());
                    }
                    System.out.println();
                    System.out.print("Choose a product to add: ");
                    Scanner product_scanner = new Scanner(System.in);
                    int product_choice = product_scanner.nextInt();  // Choosing or scanning the product
                    System.out.print("Enter Quantity: ");
                    int product_quantity = product_scanner.nextInt();  // Entring quantity
                    cus001.addProduct(products[product_choice-1], product_quantity);
                    System.out.println(products[product_choice-1].getProductName() + " is added to the list");
                    System.out.println();
                    break;
                case 2:
                    System.out.println();
                    for (int i = 0; i < ProcessingList.getInstance().filterByBarcodeId(cus001.getBarcodeReader().getId()).getSize(); i++) {
                        System.out.println(i+1 + ")" + ProcessingList.getInstance().filterByBarcodeId(cus001.getBarcodeReader().getId()).get(i).getItemName() + " " +  ProcessingList.getInstance().filterByBarcodeId(cus001.getBarcodeReader().getId()).get(i).getUnitPrice());
                    }
                    try {
                        System.out.println();
                        System.out.print("Select the item you want to delete: ");
                        Scanner myObj1 = new Scanner(System.in);
                        int deletion_choice = myObj1.nextInt();  // Read user input
                        System.out.println(ProcessingList.getInstance().filterByBarcodeId(cus001.getBarcodeReader().getId()).get(deletion_choice-1).getItemName() + " is removed from the list");
                        cus001.deleteProduct(ProcessingList.getInstance().filterByBarcodeId(cus001.getBarcodeReader().getId()).get(deletion_choice-1).getItemName());
                    } catch (InputMismatchException e) {
                        System.out.println();
                        System.out.println("Please enter a number such as (1,2,3) and not a character such as (a,b,c)");
                        continue;
                    }
                    System.out.println();
                    break;
               case 3:
                   System.out.println();
                   if (ProcessingList.getInstance().filterByBarcodeId(cus001.getBarcodeReader().getId()).isEmpty()) {
                       System.out.println("No items were added using this barcode reader yet.");
                   } else {
                       ProcessingList.getInstance().filterByBarcodeId(cus001.getBarcodeReader().getId()).iterate();
                   }
                   System.out.println();
                   break;
                case 4:
                   System.out.println();
                   Scanner product_searcher = new Scanner(System.in);
                    System.out.print("Please enter the name of the product you want to search: ");
                   String product_search_term = product_searcher.nextLine();
                   if (ProcessingList.getInstance().search(cus001.getBarcodeReader().getId(), product_search_term) == -1) {
                       System.out.println("This product is not added by you. Please make sure that you have spelled it correctly");
                   } else {
                       System.out.println(ProcessingList.getInstance().get(ProcessingList.getInstance().search(cus001.getBarcodeReader().getId(), product_search_term)).getItemName() + "\t" + ProcessingList.getInstance().get(ProcessingList.getInstance().search(cus001.getBarcodeReader().getId(), product_search_term)).getUnitPrice() + "\t" + ProcessingList.getInstance().get(ProcessingList.getInstance().search(cus001.getBarcodeReader().getId(), product_search_term)).getQuantity());
                   }
                   ProcessingList.getInstance().search(cus001.getBarcodeReader().getId(), product_search_term);
                   System.out.println();
                   break;
                case 0:
                    System.out.println("Exiting...");
                    break;
                default:
                    System.out.println("Please enter a valid input!");


            }
        }
    }

    public static void cashier(BarcodeReader[] brs) {

        Cashier cash001 = new Cashier();

        System.out.println("Welcome Cashier!");
        int cashier_choice = -1;
        while (cashier_choice != 0) {
            System.out.println("Please follow the instructions!");
            System.out.println("Enter 1 to Calculate bill");
            System.out.println("Enter 2 to print the processing list");
            System.out.println("Enter 3 to print the processing list by user.");
            System.out.println("Enter 4 to sort the processing list.");
            System.out.println("Enter 5 to filter the processing list based on unit price.");
            System.out.print("Enter 0 to exit to previous menu: ");
            try {
                Scanner myObj = new Scanner(System.in);
                cashier_choice = myObj.nextInt();  // taking customer input on what to do
                System.out.println();
            } catch (InputMismatchException e) {
                System.out.println();
                System.out.println("Please enter a number such as (1,2,3) and not a character such as (a,b,c)");
                continue;
            }
            System.out.println();


            switch (cashier_choice) {
                case 1:
                    System.out.println();
                    //        Pickup a barcode reader
                    for (int i = 0; i < brs.length; i++) {
                        System.out.println(i + 1 + ") " + brs[i].getId());
                    }
//                    -------------------
                    boolean barcode_reader_non_existence = true;
                    while (barcode_reader_non_existence) {
                        try {
                            System.out.print("Please pick a barcode for billing: ");
                            Scanner barcode_reader_picker = new Scanner(System.in);
                            int barcode_choice = barcode_reader_picker.nextInt();
                            if (ProcessingList.getInstance().filterByBarcodeId(brs[barcode_choice-1].getId()).isEmpty()) {
                                System.out.println("No items were purchased using this barcode reader");
                            } else {
                                System.out.println("Total: " + cash001.calculateBill(ProcessingList.getInstance().filterByBarcodeId(brs[barcode_choice-1].getId())));
                            }
                            cash001.clearAfterBilling(brs[barcode_choice-1].getId());
                            barcode_reader_non_existence = false;
                        } catch (ArrayIndexOutOfBoundsException e) {
                            System.out.println("The barcode you have picked does not exist!");
                        }
                    }

//                    -----------------
                    System.out.println();
                    break;

                case 2:
                    System.out.println();
                    ProcessingList.getInstance().iterate();
                    System.out.println();
                    break;
                case 3:
                    System.out.println();
                    //        Pickup a barcode reader
                    for (int i = 0; i < brs.length; i++) {
                        System.out.println(i + 1 + ") " + brs[i].getId());
                    }
//                    -----------
                    boolean barcode_reader_non_existence_1 = true;
                    while (barcode_reader_non_existence_1) {
                        try {
                            System.out.print("Please pick a barcode for printing items: ");
                            Scanner barcode_reader_picker_1 = new Scanner(System.in);
                            int barcode_choice_1 = barcode_reader_picker_1.nextInt();

                            if (ProcessingList.getInstance().filterByBarcodeId(brs[barcode_choice_1-1].getId()).isEmpty()) {
                                System.out.println("No items were purchased using this barcode reader");
                            } else {
                                ProcessingList.getInstance().filterByBarcodeId(brs[barcode_choice_1-1].getId()).iterate();
                            }
                            barcode_reader_non_existence_1 = false;

                        } catch (ArrayIndexOutOfBoundsException e) {
                            System.out.println("The barcode you have picked does not exist!");
                        }
                    }

//                    ------------------
                    System.out.println();
                    break;
                case 4:
//                    System.out.println();
                    int sorting_choice = -1;

                    while (sorting_choice != 0) {
                        Scanner myObj2 = new Scanner(System.in);
                        System.out.print("Press 1 for quick sort, 2 for selection sort, and 0 to exit sorting: ");
                        try {
                            sorting_choice = myObj2.nextInt();  // Read user input
                        } catch (InputMismatchException e) {
                            System.out.println("Please enter a number such as (1,2,3) and not a character such as (a,b,c)");
                        }
                        if (sorting_choice == 1) {
                            cash001.quickSortProcessingList();
                        } else if (sorting_choice == 2) {
                            cash001.selectionSortProcessingList();
                        } else {
                            System.out.println("Please choose a valid soring method");
                        }
                        System.out.println();
                        ProcessingList.getInstance().iterate();
                        System.out.println();
                    }
                    break;
                case 5:
                    System.out.println();
                    int filtering_choice = -1;
                    while (filtering_choice != 0) {
                        try {
                            System.out.println();
                            System.out.println("Please enter 1 for filter processing list by minimum unit price,");
                            System.out.println("enter 2 for filter processing list by maximum unit price,");
                            System.out.println("enter 3 for filter processing list between unit price,");
                            System.out.print("enter 0 for exit filtering: ");
                            Scanner filtering_choice_input = new Scanner(System.in);
                            filtering_choice = filtering_choice_input.nextInt();
                            System.out.println();
                        } catch (InputMismatchException e) {
                            System.out.println();
                            System.out.println("Please enter a number such as (1,2,3) and not a character such as (a,b,c)");
                            System.out.println();
                        }

                        if (filtering_choice == 1) {
                            boolean invalid_input = true;
                            while (invalid_input) {
                                try {
                                    System.out.println();
                                    System.out.print("Please enter the minimmup price: ");
                                    Scanner min_unit_price_input = new Scanner(System.in);
                                    int min_unit_price = min_unit_price_input.nextInt();
                                    ProcessingList.getInstance().filterByMinUnitPrice(min_unit_price).iterate();
                                    System.out.println();
                                    invalid_input = false;
                                } catch (InputMismatchException e) {
                                    System.out.println();
                                    System.out.println("Please enter a number input");
                                    System.out.println();
                                }
                            }

                        } else if (filtering_choice == 2) {
                            boolean invalid_input = true;
                            while (invalid_input) {
                                try {
                                    System.out.println();
                                    System.out.print("Please enter the maximum price: ");
                                    Scanner max_unit_price_input = new Scanner(System.in);
                                    int max_unit_price = max_unit_price_input.nextInt();
                                    ProcessingList.getInstance().filterByMaxUnitPrice(max_unit_price).iterate();
                                    System.out.println();
                                    invalid_input = false;
                                } catch (InputMismatchException e) {
                                    System.out.println();
                                    System.out.println("Please enter a number input");
                                    System.out.println();
                                }
                            }


                        } else if (filtering_choice == 3) {
                            boolean invalid_input = true;
                            while (invalid_input) {
                                try {
                                    System.out.println();
                                    Scanner unit_price_input = new Scanner(System.in);
                                    System.out.print("Please enter the minimum price: ");
                                    int min_unit_price = unit_price_input.nextInt();
                                    System.out.print("Please enter the maximum price: ");
                                    int max_unit_price = unit_price_input.nextInt();
                                    ProcessingList.getInstance().filterBetweenUnitPrice(min_unit_price, max_unit_price).iterate();
                                    System.out.println();
                                    invalid_input = false;
                                } catch (InputMismatchException e) {
                                    System.out.println();
                                    System.out.println("Please enter a number input");
                                    System.out.println();
                                }
                            }
                        } else {
                            System.out.println();
                            System.out.println("Please enter a valide input.");
                        }

                    }
                    break;
                case 0:
                    System.out.println();
                    System.out.println("Exiting...");
                    System.out.println();
                    break;
                default:
                    System.out.println("Please enter a valid input!");


            }
        }
    }
}



