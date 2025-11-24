import java.util.Scanner;

class Account {
    private int accountNumber;
    private String accountHolderName;
    private double balance;
    private String email;
    private String phoneNumber;

    public Account(int accountNumber, String name, double balance, String email, String phone) {
        this.accountNumber = accountNumber;
        this.accountHolderName = name;
        this.balance = balance;
        this.email = email;
        this.phoneNumber = phone;
    }

    public int getAccountNumber() {
        return accountNumber;
    }

    public void deposit(double amount) {
        if (amount > 0) {
            balance += amount;
            System.out.println("Deposit successful.");
        } else {
            System.out.println("Invalid amount.");
        }
    }

    public void withdraw(double amount) {
        if (amount <= 0) {
            System.out.println("Invalid amount.");
        } else if (amount > balance) {
            System.out.println("Insufficient balance.");
        } else {
            balance -= amount;
            System.out.println("Withdrawal successful.");
        }
    }

    public void displayAccountDetails() {
        System.out.println("Account Number: " + accountNumber);
        System.out.println("Name: " + accountHolderName);
        System.out.println("Balance: " + balance);
        System.out.println("Email: " + email);
        System.out.println("Phone: " + phoneNumber);
    }

    public void updateContactDetails(String email, String phone) {
        this.email = email;
        this.phoneNumber = phone;
        System.out.println("Contact details updated.");
    }
}

public class BankApp {
    Scanner sc = new Scanner(System.in);
    Account[] accounts = new Account[100];
    int count = 0;
    int accNumberCounter = 1001;

    public void createAccount() {
        System.out.print("Enter name: ");
        sc.nextLine();
        String name = sc.nextLine();

        System.out.print("Enter initial deposit: ");
        double amt = sc.nextDouble();

        System.out.print("Enter email: ");
        sc.nextLine();
        String email = sc.nextLine();

        System.out.print("Enter phone number: ");
        String phone = sc.nextLine();

        accounts[count] = new Account(accNumberCounter, name, amt, email, phone);
        System.out.println("Account created. Account Number: " + accNumberCounter);

        accNumberCounter++;
        count++;
    }

    public Account findAccount(int accNo) {
        for (int i = 0; i < count; i++) {
            if (accounts[i].getAccountNumber() == accNo) {
                return accounts[i];
            }
        }
        return null;
    }

    public void performDeposit() {
        System.out.print("Enter account number: ");
        int acc = sc.nextInt();
        Account a = findAccount(acc);

        if (a == null) {
            System.out.println("Account not found.");
            return;
        }

        System.out.print("Enter amount: ");
        a.deposit(sc.nextDouble());
    }

    public void performWithdrawal() {
        System.out.print("Enter account number: ");
        int acc = sc.nextInt();
        Account a = findAccount(acc);

        if (a == null) {
            System.out.println("Account not found.");
            return;
        }

        System.out.print("Enter amount: ");
        a.withdraw(sc.nextDouble());
    }

    public void showAccountDetails() {
        System.out.print("Enter account number: ");
        int acc = sc.nextInt();
        Account a = findAccount(acc);

        if (a == null) System.out.println("Account not found.");
        else a.displayAccountDetails();
    }

    public void updateContact() {
        System.out.print("Enter account number: ");
        int acc = sc.nextInt();
        Account a = findAccount(acc);

        if (a == null) {
            System.out.println("Account not found.");
            return;
        }

        System.out.print("Enter new email: ");
        sc.nextLine();
        String email = sc.nextLine();

        System.out.print("Enter new phone: ");
        String phone = sc.nextLine();

        a.updateContactDetails(email, phone);
    }

    public void mainMenu() {
        int choice;
        do {
            System.out.println("\n--- Banking Application ---");
            System.out.println("1. Create Account");
            System.out.println("2. Deposit");
            System.out.println("3. Withdraw");
            System.out.println("4. View Account Details");
            System.out.println("5. Update Contact Details");
            System.out.println("6. Exit");
            System.out.print("Enter choice: ");
            choice = sc.nextInt();

            switch (choice) {
                case 1: createAccount(); break;
                case 2: performDeposit(); break;
                case 3: performWithdrawal(); break;
                case 4: showAccountDetails(); break;
                case 5: updateContact(); break;
                case 6: System.out.println("Thank you!"); break;
                default: System.out.println("Invalid choice.");
            }
        } while (choice != 6);
    }

    public static void main(String[] args) {
        new BankApp().mainMenu();
    }
}
