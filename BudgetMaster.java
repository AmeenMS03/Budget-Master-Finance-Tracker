import java.util.*;

public class BudgetMaster {


    private static ArrayList<Transaction> transactions = new ArrayList<>(); //Datatype can be the object
    private static Budget budget;
    private static SavingsGoal userGoal;
    private static SpendingsGoal userGoal1;

    private static Scanner input = new Scanner(System.in); 

	public static void main(String[] args) {
	
		int userChoice = -1;

		do { 
            try {
                DisplayMenu(); //invoke method to print menu
                userChoice = UserMenuChoice(); //invoke method to record input

                switch (userChoice) {
                    case 1: LogNewTransaction(); break;
                    case 2: UpdateTransaction(); break;	
                    case 3: SetUpdateBudgetLimits(); break;	
                    case 4: TrackProgress(); break;		
                    case 5: GenerateFinancialReports(); break;	
                    case 6: SavingsGoal(); break;	
                    case 7: SpendingsGoal(); break;
                    case 0: System.out.println("Thank You for Using CSC301's BudgetMaster™ (Your Personal Finance Manager), See You Again."); break;
                    default: System.out.println("Invalid choice. Please try again.");
                }
            } catch (InputMismatchException e) {
                System.out.println("Invalid input. Please enter a valid number.");
                input.nextLine(); // clear the invalid input
            } catch (Exception e) {
                System.out.println("An error occurred: " + e.getMessage());
            }
		} while (userChoice != 0); //if the user didnt quit, then keep matching
	}
	
	public static void DisplayMenu() {  // prints this first
		System.out.println("----------------------------------------------------------------------");
		System.out.println("Personal Finance Manager System (BudgetMaster™), Fall 24-25");
		System.out.println("----------------------------------------------------------------------");
		System.out.println("1. Log a new transaction (Income, Expense, Transfer, etc.)");
		System.out.println("2. Update transaction details (amount, category, date, notes, etc.)");
		System.out.println("3. Set or update budget limits for various categories.");
		System.out.println("4. Track progress towards budget goals and Financial Goals (Budget Limit, Savings Goal, Spending Goal)");
		System.out.println("5. Generate financial reports");
		System.out.println("6. Set or Update Savings Goal");
        System.out.println("7. Set or Update Spendings Goal");
		System.out.println("0. Exit");
		System.out.println("----------------------------------------------------------------------");
	}

	public static int UserMenuChoice(){ //prints this first
        int choice;
        do { //keeps printing this until the under input is not this
            System.out.println("Input Your Choice (or re-enter your choice) (1, 2, 3, 4, 5, 6, 7, 0):");
            System.out.println("----------------------------------------------------------------------");
            choice = input.nextInt(); 
            if (choice < 0 || choice > 7) {
                System.out.println("Invalid input. Please enter a number between 0 and 7");
                System.out.println("----------------------------------------------------------------------");
            }
        } while (choice < 0 || choice > 7);
        return choice; //returning choice value
    }
	
	public static void LogNewTransaction(){
        
        try { 
            System.out.print("Enter Transaction ID: ");
            int transactionID = input.nextInt();
            System.out.print("Enter An Amount: "); 
            double amount = input.nextDouble();
            String transactionType;
            
            while (true){ //Keeps running loop until valid repsonse is inputed
                System.out.print("Enter transaction type (Income, Expense, Investment): ");
                transactionType = input.next().toLowerCase();
                
                if (transactionType.equals("income") || transactionType.equals("expense") || transactionType.equals("investment")){
                    break;
                } // We did .equals because it is an alternative way of comparing values as == couldnt compare for us

                else{
                    System.out.println("Invalid transaction type, please try again");
                }
            }

            System.out.print("Enter An Category (food, stationary, etc): "); 
            String category = input.next();
            System.out.print("Enter An Date: "); 
            String date = input.next();
            System.out.print("Enter An Notes: "); 
            String notes = input.next();
            System.out.print("Enter An IBAN: "); 
            String iban = input.next();
            String region;
            while (true){ //Keeps running loop until valid repsonse is inputed
                System.out.print("Enter the Region (Domestic/International): "); 
                region = input.next().toLowerCase();
                if (region.equals("domestic") || region.equals("international") ){
                    break;
                }
                else{
                    System.out.println("Invalid transaction type, please try again");
                }
            }
            Transaction transaction = null; //so the object becomes null - clearing the list
            boolean repeatSwitch = true;

            while (repeatSwitch){ //keeps repeating the loop until user selected a valid transaction type
                System.out.print("Which Type of Transaction (1-Income, 2-Expense, 3-Investment): "); 
                int type = input.nextInt();

                switch (type) {
                    case 1:
                        transaction = new Income(transactionID, transactionType, amount, category, date, notes, iban, region);
                        transactions.add(transaction);
                        System.out.println("Transaction logged successfully.");
                        repeatSwitch = false; //changes repeatSwitch to false, which breaks the loop
                        break;
                    case 2:
                        transaction = new Expense(transactionID, transactionType, amount, category, date, notes, iban, region);
                        transactions.add(transaction);
                        System.out.println("Transaction logged successfully.");
                        repeatSwitch = false;
                        break;
                    case 3:
                        transaction = new Investment(transactionID, transactionType, amount, category, date, notes, iban, region);
                        transactions.add(transaction);
                        System.out.println("Transaction logged successfully.");
                        repeatSwitch = false;
                        break;
                    default: System.out.println("Invalid transaction type. Please try again");
                        break;
                }
            }
        }        
        catch (InputMismatchException e) { //Throws exception when invalid input is entered
            System.out.println("Invalid input. Please enter correct types.");
            input.nextLine();
        } catch (Exception e) { //Throws exception when different type of error is encountered
                System.out.println("Error updating transaction: " + e.getMessage());
        }    
    }

	public static void UpdateTransaction(){
		try { //exception handling to avoid breaking 
            for(int i = 0; i < transactions.size(); i++){
                System.out.println(i+1 + ". "+ transactions.get(i).getTransactionID()); //getting and printing all transaction IDs
            }

            System.out.print("Enter the transaction ID you want to generate a update: ");
            int transactionID = input.nextInt(); //recording ID the user entered

            int newTransactionID1 = checkTransactionID(transactionID); //sends transactionID to checkTransactionID method, where it is checked
                                                                       //to see if it exists in transactions ArrayList. The same or updated ID is 
                                                                       //stored as newTransactionID1
                                                                       
            if (newTransactionID1 == -1) { // Handle invalid transaction ID
                System.out.println("TransactionID not found. Please try again.");
                return; // Exit the method if not found
            }

            for(int j = 0; j < transactions.size(); j++){ 

                if(newTransactionID1 == transactions.get(j).getTransactionID()){ //if provided ID matches ID of the transaction in ArrayList, the code is run

                    System.out.print("Enter a new transaction ID: ");
                    int newTransactionID2 = input.nextInt();
                    transactions.get(j).setTransactionID(newTransactionID2);
                    String newTransactionType;
                    while (true){
                        System.out.print("Enter a new transaction type (Income, Expense, Investment): ");
                        newTransactionType = input.next().toLowerCase();
                        if (newTransactionType.equals("income") || newTransactionType.equals("expense") || newTransactionType.equals("investment") )
                            break;                         
                        else{
                            System.out.println("Invalid transaction type, please try again");
                        }
                    }   
                    
                    transactions.get(j).setTransactionType(newTransactionType);
                    System.out.print("Enter a new amount: ");
                    double newAmount = input.nextDouble();
                    transactions.get(j).setAmount(newAmount);
                    System.out.print("Enter a new category (food, stationary, etc): ");
                    String newCategory = input.next();
                    transactions.get(j).setCategory(newCategory);
                    System.out.print("Enter a new date: ");
                    String newDate = input.next();
                    transactions.get(j).setDate(newDate);
                    System.out.print("Enter new notes: ");
                    String newNotes = input.next();
                    transactions.get(j).setNotes(newNotes);
                    System.out.print("Enter a new IBAN: ");
                    String newIban = input.next();
                    transactions.get(j).setIban(newIban);
                    String newRegion;
                    while (true){
                        System.out.print("Enter a new region (Domestic/Internationl): ");
                        newRegion = input.next().toLowerCase();
                        if (newRegion.equals("domestic") || newRegion.equals("international") ){
                            break;
                        }
                        else{
                            System.out.println("Invalid transaction type, please try again");
                        }
                    }                    
                    transactions.get(j).setRegion(newRegion);
                }
            }
        }
        catch (InputMismatchException e) {
            System.out.println("Invalid input. Please enter correct types.");
            input.nextLine();
        } catch (Exception e) {
                System.out.println("Error updating transaction: " + e.getMessage());
        }    
    }

	public static void SetUpdateBudgetLimits(){
		double totalAmount = 0;
        try {

            for (Transaction transaction : transactions) { //for each transaction stored in transaction
                totalAmount = totalAmount + transaction.getAmount(); //add all amounts for all transactions  
            }

            System.out.println("----------------------------------------------------------------------");
            System.out.println("Budget Limit");
            System.out.println("----------------------------------------------------------------------");
            System.out.println("1. Set your budget limit.");
            System.out.println("2. Update your budget limit.");
            System.out.println("----------------------------------------------------------------------");
            
            int setOrUpdate = input.nextInt(); //record 
            
            System.out.println("Total amount from transactions: " + totalAmount); //print them their total transaction amounts first
            
            if (setOrUpdate == 2) { // 2 is selected, then they can update their budget limit
                System.out.println("----------------------------------------------------------------------");
                System.out.print("Enter Budget Limit you want to update: ");
                System.out.println("----------------------------------------------------------------------");
                double updateValue = input.nextDouble();
                budget.setBudgetLimit(updateValue); //saving the new limit to the variable in Budget Class
            }
            else {
                System.out.println("----------------------------------------------------------------------");
                System.out.print("Enter your budget Limit: ");
                System.out.println("----------------------------------------------------------------------");
                double limit = input.nextDouble(); //record the user's input
                
                budget =  new Budget(totalAmount, limit); //send it to the class through constructor
            }
            System.out.println("----------------------------------------------------------------------");
            System.out.println("Your budget has been set:");
            System.out.println(budget.toString());  //print the budget recorded 
            System.out.println("----------------------------------------------------------------------");
        }
        catch (InputMismatchException e) {
            System.out.println("Invalid input. Please enter correct types.");
            input.nextLine();
        } catch (Exception e) {
                System.out.println("Error updating transaction: " + e.getMessage());
        }     
	}

	public static void TrackProgress(){ //Sees if the person has reached their limit.
        try { //try the code and throw exception for any errors
            
            if (budget == null) { //if no budget exists, create a new one 
                System.out.println("No budget has been set. Please set your budget first.");
                SetUpdateBudgetLimits(); // We then send the person to another method so the budget gets set
            }       
            if (userGoal == null) {
                System.out.println("No Savings Goal has been set. Please set your Savings Goal first.");
                SavingsGoal();
                return;  // To avoid continuing after setting the goal
            } 
            if (userGoal1 == null) {
                System.out.println("No Spendings Goal has been set. Please set your Spendings Goal first.");
                SpendingsGoal();
                return;  // To avoid continuing after setting the goal
            }

            else {
                System.out.println("----------------------------------------------------------------------");
                System.out.println("Your current budget limit is: " + budget.getBudgetLimit());
                System.out.println(budget.getLimit());

                //check savings goal
                System.out.println("Your Savings Goal is: " + userGoal.getTargetAmount());
                if (userGoal.isGoalReached()) {
                    System.out.println("Congratulations! You have reached your savings goal.");
                } else {
                    System.out.println("You are below your savings goal.");
                }

                // Check spendings goal
                System.out.println("Your Spendings Goal is: " + userGoal1.getTargetAmount());
                if (userGoal1.isGoalReached()) {
                    System.out.println("Warning! You have exceeded your spendings goal.");
                } else {
                    System.out.println("You are below your spendings goal. Keep it up!");
                }
            }
        }
        catch (InputMismatchException e) {
            System.out.println("Invalid input. Please enter correct types.");
            input.nextLine();
        }catch (Exception e) {
                System.out.println("Error updating transaction: " + e.getMessage());
        }         
	}
	
	public static void GenerateFinancialReports(){
        try {

            for(int i = 0; i < transactions.size(); i++){
                System.out.println(i+1 + ". " + transactions.get(i).getTransactionID()); // get the IDs for the person to choose to which he wants
            }                                                                               // to print financial report of
            
            System.out.print("Enter the transaction ID you want to generate a report: ");
            int transactionID = input.nextInt(); //the person puts the trnsaction ID it wants details of 
            input.nextLine();

            int newTransactionID = checkTransactionID(transactionID); //invoke method to chheck and match the IDs, once recieved, record it

            if (newTransactionID == -1) { 
                System.out.println("TransactionID not found. Please try again.");
                return; 
            }

            for(int j = 0; j < transactions.size(); j++){

                if(newTransactionID == transactions.get(j).getTransactionID()){		 //get information of the transactions 

                    System.out.println("Transaction ID: "+ newTransactionID);
                    String transactionType = transactions.get(j).getTransactionType();
                    System.out.println("Transaction Type (Income, Expense, Investment): " + transactionType);
                    double amount = transactions.get(j).getAmount();
                    System.out.println("Amount: " + amount);
                    String category = transactions.get(j).getCategory();
                    System.out.println("Category: " + category);
                    String date = transactions.get(j).getDate();
                    System.out.println("Date: "+ date);
                    String notes = transactions.get(j).getNotes();
                    System.out.println("Notes: " + notes);
                    String iban = transactions.get(j).getIban();
                    System.out.println("IBAN: "+iban);
                    String region = transactions.get(j).getRegion();
                    System.out.println("Region: " + region);

                }
                else{
                    System.out.println("TransactionID does not exist.");
                }
            }
        }
        catch (InputMismatchException e) {
            System.out.println("Please enter correct ID.");
            input.nextLine();
        } catch (Exception e) {
                System.out.println("Error updating transaction: " + e.getMessage());
        }    
	}

	public static void SavingsGoal(){

        try {

            System.out.println("----------------------------------------------------------------------");
            System.out.println("View your Savings Goal"); 
            System.out.println("----------------------------------------------------------------------");
            System.out.println("1. Create a new Goal");
            System.out.println("2. Deposit savings");
            System.out.println("3. Withdraw savings");
            System.out.println("4. Savings report");
            System.out.println("----------------------------------------------------------------------");
        
            int choice = input.nextInt();
            boolean repeatSwitch = true;
        
            if (userGoal == null) {
                userGoal = new SavingsGoal(); // Using the FinancialGoal class that implements the interface
            } //of the object is empty, then create it
        
            while (repeatSwitch) { //while condition is true, keep the user in the loop
                try {
                    switch (choice) {
                        case 1:
                            System.out.print("Enter Your Goal Type: ");
                            String goalType = input.next();
                            userGoal.createGoal(goalType);  // Use interface method
                            System.out.print("Enter Your Target Amount: ");  //ask user for target amount
                            double targetAmount = input.nextDouble();  //record
                            userGoal.setTargetAmount(targetAmount); // Use interface method
                            repeatSwitch = false; //break the loop 
                            break;
                        case 2:
                            System.out.print("Enter the amount you want to deposit: ");
                            double depositAmount = input.nextDouble();
                            userGoal.deposit(depositAmount); // Use interface method
                            if (userGoal.isGoalReached()) { // if condition is true.
                                System.out.println("Congratulations!!! You have reached your goal.");
                            }
                            repeatSwitch = false; //break the loop
                            break;
                        case 3:
                            System.out.print("Enter the amount you want to withdraw: ");
                            double withdrawAmount = input.nextDouble();
                            if (userGoal.withdraw(withdrawAmount)) { // Use interface method
                                System.out.println("Withdrawal successful.");
                            } else {
                                System.out.println("Insufficient funds.");
                            }
                            repeatSwitch = false; //break the loop
                            break;
                        case 4:
                            System.out.println("----------------------------------------------------------------------");
                            System.out.println("Savings Report");
                            System.out.println("----------------------------------------------------------------------");
                            System.out.println("Goal Type: " + userGoal.getGoalType()); // Use interface method
                            System.out.println("Goal Reached: " + userGoal.isGoalReached()); // Use interface method
                            System.out.println("Target Amount: " + userGoal.getTargetAmount()); // Use interface method
                            System.out.println("Current Savings: " + userGoal.getCurrentAmount()); // Use interface method
                            repeatSwitch = false;
                            break;
                        default:
                            System.out.println("Invalid choice. Please try again.");
                            break;
                    }
                } catch (InputMismatchException e) {
                    System.out.println("Invalid input. Please enter the correct types.");
                    input.nextLine(); // clear the scanner buffer
                } catch (Exception e) {
                    System.out.println("Error: " + e.getMessage());
                }
            }
        }
        catch (InputMismatchException e) {
            System.out.println("Invalid input. Please enter correct types.");
            input.nextLine();
        } catch (Exception e) {
                System.out.println("Error updating transaction: " + e.getMessage());
        }    
	}

    public static void SpendingsGoal(){

        try {

            System.out.println("----------------------------------------------------------------------");
            System.out.println("View your Spendings Goal"); 
            System.out.println("----------------------------------------------------------------------");
            System.out.println("1. Create a new Goal");
            System.out.println("2. Deposit savings");
            System.out.println("3. Withdraw savings");
            System.out.println("4. Spendings report");
            System.out.println("----------------------------------------------------------------------");
        
            int choice = input.nextInt();
            boolean repeatSwitch = true;
        
            if (userGoal1 == null) {
                userGoal1 = new SpendingsGoal(); // Using the FinancialGoal class that implements the interface
            } //of the object is empty, then create it
        
            while (repeatSwitch) { //while condition is true, keep the user in the loop
                try {
                    switch (choice) {
                        case 1:
                            System.out.print("Enter Your Goal Type: ");
                            String goalType = input.next();
                            userGoal1.createGoal(goalType);  // Use interface method
                            System.out.print("Enter Your Target Amount: ");  //ask user for target amount
                            double targetAmount = input.nextDouble();  //record
                            userGoal1.setTargetAmount(targetAmount); // Use interface method
                            repeatSwitch = false; //break the loop 
                            break;
                        case 2:
                            System.out.print("Enter the amount you want to deposit: ");
                            double depositAmount = input.nextDouble();
                            userGoal1.deposit(depositAmount); // Use interface method
                            if (userGoal1.isGoalReached()) { // if condition is true.
                                System.out.println("Congratulations!!! You have reached your goal.");
                            }
                            repeatSwitch = false; //break the loop
                            break;
                        case 3:
                            System.out.print("Enter the amount you want to withdraw: ");
                            double withdrawAmount = input.nextDouble();
                            if (userGoal1.withdraw(withdrawAmount)) { // Use interface method
                                System.out.println("Withdrawal successful.");
                            } else {
                                System.out.println("Insufficient funds.");
                            }
                            repeatSwitch = false; //break the loop
                            break;
                        case 4:
                            System.out.println("----------------------------------------------------------------------");
                            System.out.println("Spendings Report");
                            System.out.println("----------------------------------------------------------------------");
                            System.out.println("Goal Type: " + userGoal1.getGoalType()); // Use interface method
                            System.out.println("Goal Reached: " + userGoal1.isGoalReached()); // Use interface method
                            System.out.println("Target Amount: " + userGoal1.getTargetAmount()); // Use interface method
                            System.out.println("Current Savings: " + userGoal1.getCurrentAmount()); // Use interface method
                            repeatSwitch = false;
                            break;
                        default:
                            System.out.println("Invalid choice. Please try again.");
                            break;
                    }
                } catch (InputMismatchException e) {
                    System.out.println("Invalid input. Please enter the correct types.");
                    input.nextLine(); // clear the scanner buffer
                } catch (Exception e) {
                    System.out.println("Error: " + e.getMessage());
                }
            }
        }
        catch (InputMismatchException e) {
            System.out.println("Invalid input. Please enter correct types.");
            input.nextLine();
        } catch (Exception e) {
                System.out.println("Error updating transaction: " + e.getMessage());
        }    
	}

    public static int checkTransactionID (int transactionID){
        
        // boolean continueLoop = true;
        // int newTransactionID = 0;
        
        for (Transaction t : transactions) {
            if (t.getTransactionID() == transactionID) {
                return transactionID; // Return the valid transaction ID
            }
        }
        return -1; // Return -1 if not found
    }
}