public abstract class Transaction {

    protected int transactionID;
	protected String transactionType;
    protected double amount;
    protected String category; //The User's input
    protected String date; 
    protected String notes;
    protected String iban;
    protected String region;

    public Transaction(int transactionID, String transactionType, double amount, String category, String date, String notes, String iban, String region) {
        this.transactionID = transactionID;
	this.transactionType=transactionType;
        this.amount = amount;
        this.category = category;
        this.date = date;
        this.notes = notes;
        this.iban = iban;
        this.region = region;
    }
    public abstract void displayTransaction();

    public int getTransactionID() {
        return transactionID;
    }
    public void setTransactionID(int transactionID) {
        this.transactionID = transactionID;
    }
	public String getTransactionType() {
        return transactionType;
    }
    public void setTransactionType(String transactionType) {
        this.transactionType = transactionType;
    }
    public double getAmount() {
        return amount;
    }
    public void setAmount(double amount) {
        this.amount = amount;
    }
    public String getCategory() {
        return category;
    }
    public void setCategory(String category) {
        this.category = category;
    }
    public String getDate() {
        return date;
    }
    public void setDate(String date) {
        this.date = date;
    }
    public String getNotes() {
        return notes;
    }
    public void setNotes(String notes) {
        this.notes = notes;
    }
    public String getIban() {
        return iban;
    }
    public void setIban(String iban) {
        this.iban = iban;
    }
    public String getRegion() {
        return region;
    }
    public void setRegion(String region) {
        this.region = region;
    } 
}
