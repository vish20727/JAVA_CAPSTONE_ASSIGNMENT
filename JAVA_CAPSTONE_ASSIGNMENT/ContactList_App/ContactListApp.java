import java.io.*;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

class Contact {
    private String name;
    private String phoneNumber;
    private String email;

    public Contact(String name, String phoneNumber, String email) {
        this.name = name;
        this.phoneNumber = phoneNumber;
        this.email = email;
    }

    public String toCSV() {
        return name + "," + phoneNumber + "," + email;
    }

    public static Contact fromCSV(String line) {
        String[] parts = line.split(",", -1);
        if (parts.length < 3) return null;
        return new Contact(parts[0], parts[1], parts[2]);
    }

    @Override
    public String toString() {
        return "Name: " + name + ", Phone: " + phoneNumber + ", Email: " + email;
    }
}

public class ContactListApp {
    private static HashMap<String, Contact> contacts = new HashMap<>();
    private static Scanner sc = new Scanner(System.in);

    private static void addContact() {
        System.out.print("Enter contact name: ");
        sc.nextLine();
        String name = sc.nextLine();
        System.out.print("Enter phone number: ");
        String phone = sc.nextLine();
        System.out.print("Enter email: ");
        String email = sc.nextLine();
        contacts.put(name, new Contact(name, phone, email));
        System.out.println("Contact added successfully.");
    }

    private static void removeContact() {
        System.out.print("Enter contact name to remove: ");
        sc.nextLine();
        String name = sc.nextLine();
        if (contacts.remove(name) != null) System.out.println("Contact removed.");
        else System.out.println("Contact not found.");
    }

    private static void searchContact() {
        System.out.print("Enter contact name to search: ");
        sc.nextLine();
        String name = sc.nextLine();
        Contact c = contacts.get(name);
        if (c != null) System.out.println(c);
        else System.out.println("Contact not found.");
    }

    private static void displayAllContacts() {
        if (contacts.isEmpty()) {
            System.out.println("No contacts to display.");
            return;
        }
        for (Map.Entry<String, Contact> e : contacts.entrySet()) {
            System.out.println(e.getValue());
        }
    }

    private static void saveContactsToFile(String filename) {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(filename))) {
            for (Contact c : contacts.values()) {
                bw.write(c.toCSV());
                bw.newLine();
            }
            System.out.println("Contacts saved to " + filename);
        } catch (IOException e) {
            System.out.println("Error saving contacts: " + e.getMessage());
        }
    }

    private static void loadContactsFromFile(String filename) {
        try (BufferedReader br = new BufferedReader(new FileReader(filename))) {
            contacts.clear();
            String line;
            while ((line = br.readLine()) != null) {
                Contact c = Contact.fromCSV(line);
                if (c != null) contacts.put(c.toString().split(",")[0].replace("Name: ", "").trim(), c);
            }
            System.out.println("Contacts loaded from " + filename);
        } catch (FileNotFoundException e) {
            System.out.println("File not found: " + filename);
        } catch (IOException e) {
            System.out.println("Error loading contacts: " + e.getMessage());
        }
    }

    public static void main(String[] args) {
        int choice;
        do {
            System.out.println("\n--- Contact List Application ---");
            System.out.println("1. Add Contact");
            System.out.println("2. Remove Contact");
            System.out.println("3. Search Contact");
            System.out.println("4. Display All Contacts");
            System.out.println("5. Save to File");
            System.out.println("6. Load from File");
            System.out.println("7. Exit");
            System.out.print("Enter choice: ");
            choice = sc.nextInt();
            switch (choice) {
                case 1 -> addContact();
                case 2 -> removeContact();
                case 3 -> searchContact();
                case 4 -> displayAllContacts();
                case 5 -> {
                    System.out.print("Enter filename to save: ");
                    sc.nextLine();
                    String fname = sc.nextLine();
                    saveContactsToFile(fname);
                }
                case 6 -> {
                    System.out.print("Enter filename to load: ");
                    sc.nextLine();
                    String fname = sc.nextLine();
                    loadContactsFromFile(fname);
                }
                case 7 -> System.out.println("Exiting...");
                default -> System.out.println("Invalid choice.");
            }
        } while (choice != 7);
    }
}
