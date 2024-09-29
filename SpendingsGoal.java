class SpendingsGoal implements GoalActions {
    private String goalType;
    private double targetAmount;
    private double currentAmount = 0;

    public SpendingsGoal() {}

    @Override
    public void createGoal(String goalType) {
        this.goalType = goalType;
    }

    @Override
    public void setTargetAmount(double targetAmount) {
        this.targetAmount = targetAmount;
    }

    @Override
    public void deposit(double amount) {
        currentAmount += amount;
    }

    @Override
    public boolean withdraw(double amount) {
        if (amount <= currentAmount) {
            currentAmount -= amount;
            return true;
        } else {
            return false;
        }
    }

    @Override
    public boolean isGoalReached() {
        return currentAmount >= targetAmount;
    }

    @Override
    public String getGoalType() {
        return goalType;
    }

    @Override
    public double getTargetAmount() {
        return targetAmount;
    }

    @Override
    public double getCurrentAmount() {
        return currentAmount;
    }

    @Override
    public String toString() {
        return "Goal: " + goalType + ", Target: " + targetAmount + ", Current Amount: " + currentAmount;
    }
}
