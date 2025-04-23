package cinema;

public class Statistics {

    private int income;
    private int available;
    private int purchased;

    public Statistics(int income, int available, int purchased) {
        this.income = income;
        this.available = available;
        this.purchased = purchased;
    }

    public int getIncome() {
        return income;
    }

    public void setIncome(int income) {
        this.income = income;
    }

    public void addIncome(int income) {
        this.income += income;
    }

    public void subtractIncome(int income) {
        this.income -= income;
    }

    public int getAvailable() {
        return available;
    }

    public void setAvailable(int available) {
        this.available = available;
    }

    public void addAvailable() {
        this.available += 1;
    }

    public void subtractAvailable() {
        this.available -= 1;
    }

    public int getPurchased() {
        return purchased;
    }

    public void setPurchased(int purchased) {
        this.purchased = purchased;
    }

    public void addPurchased() {
        this.purchased += 1;
    }

    public void subtractPurchased() {
        this.purchased -= 1;
    }
}
