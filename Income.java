public class Income extends Transaction{
    public Income(int transactionID, String transactionType, double amount, String category, String date, String notes, String iban, String region) {
        super(transactionID, transactionType, amount, category, date, notes, iban, region);
    }
    @Override
    public void displayTransaction() {
        System.out.println("TransactionID: " + transactionID + "\nTransaction type: " + transactionType + 
                "\nIncome Amount: " + amount + "\nCategory: " + category + "\nDate: " + date + "\nNotes: " + 
                notes + "\nIBAN: " + iban + "\nRegion: " + region);
    }
}
