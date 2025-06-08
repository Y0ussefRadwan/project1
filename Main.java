import java.util.Scanner;
public class Main {
    public static boolean Authenticate(String s1, String s2) {
        String user = "admin";
        String password = "user1";
        return user.equals(s2) && password.equals(s1);
    }

    public static int currentId = 5; 
    public static String[] name = new String[1000];
    public static int[] price = new int[1000];
    public static int[] stock = new int[1000];
    public static int[] id = new int[1000];
    public static String[] deletedName = new String[1000];
    public static int[] deletedPrice = new int[1000];
    public static int[] deletedStock = new int[1000];
    public static int[] deletedId = new int[1000];
    public static boolean[] isDeleted = new boolean[1000];

    public static Scanner scan = new Scanner(System.in);

    public static void main(String[] args) {
        Arrays();
        int attempts = 2;
        while (attempts >= 0) {
            System.out.print("Enter username: ");
            String username = scan.next();
            System.out.print("Enter password: ");
            String password = scan.next();
            boolean authenticated = Authenticate(password, username);
                
            if (authenticated && attempts>=0) {
                System.out.println("Login Successful!");
                break;
            } else {
                System.out.println("Wrong username or password, you have " + attempts + " tries left!");
                attempts--;
            }
            
            if (attempts == -1) {
                System.exit(0); 
            }
        }

        while (true) {
            Menu();
        }
    }

    public static void Menu() {
        System.out.println("========== Store Management ==========");
        System.out.println("1. Add Product");
        System.out.println("2. Show All Products");
        System.out.println("3. Search by ID");
        System.out.println("4. Update Product by ID");
        System.out.println("5. Delete by ID");
        System.out.println("6. Restore by ID");
        System.out.println("7. Exit");
        System.out.println("======================================");
        System.out.print("Please select an operation: ");
        int choice = scan.nextInt();
        switch (choice) {
            case 1 -> Create();
            case 2 -> Read();
            case 3 -> Search();
            case 4 -> Update();
            case 5 -> Delete();
            case 6 -> Restore();
            case 7 -> Exit();
            default -> System.out.println("Invalid Input! Try again.");
        }
    }

    public static void Create() {
        System.out.println("========== Operation Starting ==========");
        scan.nextLine(); 
        System.out.print("Enter product's name: ");
        String productName = scan.nextLine();
        while (productName.trim().isEmpty()) {
            System.out.print("Product name cannot be empty! Enter again: ");
            productName = scan.nextLine();
        }

        System.out.print("Enter product's price: ");
        double productPrice = scan.nextDouble();
        while (productPrice <= 0) {
            System.out.print("Price must be a positive number! Enter the price again: ");
            productPrice = scan.nextDouble();
        }

        System.out.print("Enter stock quantity: ");
        int productStock = scan.nextInt();
        while (productStock < 0) {
            System.out.print("Stock quantity cannot be negative! Enter the stock's quantity again: ");
            productStock = scan.nextInt();
        }

        currentId++;
        int newId = currentId;

        int index = newId - 1;
        id[index] = newId;
        name[index] = productName;
        price[index] = (int) productPrice;
        stock[index] = productStock;
        isDeleted[index] = false;

        System.out.println("Product added successfully! ID: " + newId);
        System.out.println("========== Operation Done ==========");
    }

    public static void Arrays() {
          name[0] = "The Lord of the Rings";
          name[1] = "Pride and Prejudice";
          name[2] = "To Kill a Mockingbird";
          name[3] = "Harry Potter Part 1";
          name[4] = "The Great Gatsby";
          

        price[0] = 25;
        price[1] = 30;
        price[2] = 50;
        price[3] = 70;
        price[4] = 75;

        stock[0] = 10;
        stock[1] = 5;
        stock[2] = 7;
        stock[3] = 2;
        stock[4] = 8;

        id[0] = 1;
        id[1] = 2;
        id[2] = 3;
        id[3] = 4;
        id[4] = 5;
        
        isDeleted[0] = false;
        isDeleted[1] = false;
        isDeleted[2] = false;
        isDeleted[3] = false;
        isDeleted[4] = false;
    }

    public static void Read() {
        System.out.println("========== Operation Starting ==========");
        System.out.println("Welcome to Taha Hussein's Bookstore");
        for(int k=0;k<97;k++){
            System.out.print("-");
        }

        System.out.println();
        System.out.println("| ID\t\t | Name\t\t\t\t | Price\t\t | Stock \t\t|");
        for (int i = 0; i < currentId; i++) {
            for(int k=0;k<97;k++){
                System.out.print("-");
            }

            System.out.println("");
            if (!isDeleted[i]) {
                System.out.println("|"+id[i]+"\t\t"+name[i]+"\t\t\t"+price[i]+"\t\t\t"+stock[i]+"\t\t|");
            }
        }

        for(int k=0;k<97;k++){
            System.out.print("-");  
        }
        System.out.println();
        System.out.println("========== Operation Done ==========");
    }

    public static void Search() {
        System.out.println("========== Operation Starting ==========");
        System.out.println("Enter ID from 1 to " + currentId + ": ");
        int Search_id = scan.nextInt();
        int index = Search_id - 1;
        if (Search_id < 1 || Search_id > currentId || name[index] == "0" || isDeleted[index]) {
            System.out.println("The product with ID " + Search_id + " doesn't exist or is deleted.");
        }
         else {
            System.out.println("Product ID: " + Search_id);
            System.out.println("Name: " + name[index]);
            System.out.println("Price: " + price[index]);
            System.out.println("Stock: " + stock[index]);
        }
        System.out.println("========== Operation Done ==========");
    }

    public static void Update() {
        System.out.println("========== Operation Starting ==========");
        System.out.print("Please enter the ID: ");
        int enteredID = scan.nextInt();
        int updatedIndex = -1;
        for (int i = 0; i < currentId; i++) {
            if (id[i] == enteredID && !isDeleted[i]) {
                updatedIndex = i;
                break;
            }
        }

        if (updatedIndex == -1) {
            System.out.println("This ID doesn't exist or the product is deleted.");
            return;
        }

        scan.nextLine();
        System.out.print("Please enter the updated name: ");
        name[updatedIndex] = scan.nextLine();

        int updatedPrice;
        do {
            System.out.print("Please enter the updated price: ");
            updatedPrice = scan.nextInt();
            if(updatedPrice<0){
                System.out.println("The price can't be negative, do u want us to give them money for buying the book?");
                System.out.println("Please enter a valid price");
            }

            else if(updatedPrice==0){
                System.out.println("GET LOST, THIS IS NOT A CHARITY!");
                System.out.println("Please enter a valid price");
            }

        } while (updatedPrice <= 0);
        price[updatedIndex] = updatedPrice;

        int updatedStock;
        do {
            System.out.print("Please enter the updated stock: ");
            updatedStock = scan.nextInt();
            if(updatedStock<0){
                System.out.println("Please enter the REAL stock");
            }

        } while (updatedStock < 0);
        stock[updatedIndex] = updatedStock;

        System.out.println("The information is updated successfully.");
        System.out.println("========== Operation Done ==========");
    }

    public static void Delete() {
        System.out.println("========== Operation Starting ==========");
        System.out.println("Enter ID to delete from 1 to " + currentId + ": ");
        int deleteId = scan.nextInt();
        int index = deleteId - 1;
        if (deleteId < 1 || deleteId > currentId || isDeleted[index]) {
            System.out.println("The product with ID " + deleteId + " doesn't exist or is already deleted.");
            return;
        } 
        else {
            isDeleted[index] = true;
            System.out.println("The product with ID " + deleteId + " has been deleted.");
        }

        deletedName[index] = name[index];
        deletedPrice[index] = price[index];
        deletedStock[index] = stock[index];
        deletedId[index] = id[index];
        name[index]="0";
        price[index]=0;
        stock[index]=0;
        id[index]=0;
        System.out.println("========== Operation Done ==========");
    }

    public static void Restore() {
        System.out.println("========== Operation Starting ==========");
        System.out.println("Enter ID to restore from 1 to " + currentId + ": ");
        int restoreId = scan.nextInt();
        int index = restoreId - 1;
        if (restoreId < 1 || restoreId > currentId || !isDeleted[index]) {
            System.out.println("The product with ID " + restoreId + " doesn't exist or is not deleted.");
            return;
        }
         else {
            isDeleted[index] = false;
            System.out.println("The product with ID " + restoreId + " has been restored.");
        }
        name[index] = deletedName[index];
        price[index] = deletedPrice[index];
        stock[index] = deletedStock[index];
        id[index] = deletedId[index];
        deletedName[index]="0";
        deletedPrice[index]=0;
        deletedStock[index]=0;
        deletedId[index]=0;
        System.out.println("========== Operation Done ==========");
    }
    public static void Exit(){
        System.out.print("Thanks for your memorable transaction, please consider revisiting.\nHere is a little gift for you : \"2azkar 2l Saba7 Wa 2l Masa2\" book!");
        System.exit(0);
    }
}