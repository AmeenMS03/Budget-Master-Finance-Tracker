public class Budget{
    private double balance = 0.0;
    private double budgetLimit = 0.0;

	private boolean limit = false;
    
    public Budget(double balance,double budgetLimit){
        this.balance = balance;
        this.budgetLimit = budgetLimit;
		limit = balance > budgetLimit;
    }

	public String getLimit() {
        return limit ? "You are above the Budget Limit" : "You are below the Budget Limit! Enjoy :)"; //simpliefied the code 
	}

    public double getBalance() {
        return balance;
    }
    public void setBalance(double balance) {
        this.balance = balance;
    }
    public double getBudgetLimit() {
        return budgetLimit;
    }
    public void setBudgetLimit(double budgetLimit) {
        this.budgetLimit = budgetLimit;
    }
    
    @Override
    public String toString() {
        return "Balance: " + balance + ", BudgetLimit: " + budgetLimit;
    }
}
