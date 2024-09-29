interface GoalActions { //create interface so in the future if we require creating more classes based on goals, we can use it

    void createGoal(String goalType);
    void setTargetAmount(double targetAmount);
    void deposit(double amount);
    boolean withdraw(double amount);
    boolean isGoalReached(); 
    String getGoalType();
    double getTargetAmount();
    double getCurrentAmount(); 

}

//this interface is providing same methods to both of the classes "Savings Goal" and "Spendings Goal". 