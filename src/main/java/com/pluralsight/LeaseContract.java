package com.pluralsight;

public class LeaseContract extends Contract {

    private double expectedEndingValue;
    private double leaseFee;

    public LeaseContract(String date, String customerName, String customerEmail, Vehicle vehicle) {
        super(date, customerName, customerEmail, vehicle);

        double price = vehicle.getPrice();
        this.expectedEndingValue = price * 0.50;
        this.leaseFee = price * 0.07;
    }

    public double getExpectedEndingValue() {
        return expectedEndingValue;
    }

    public void setExpectedEndingValue(double expectedEndingValue) {
        this.expectedEndingValue = expectedEndingValue;
    }

    public double getLeaseFee() {
        return leaseFee;
    }

    public void setLeaseFee(double leaseFee) {
        this.leaseFee = leaseFee;
    }

    @Override
    public double getTotalPrice() {
        return expectedEndingValue + leaseFee;
    }
    @Override
    public double getMonthlyPayment() {
        double rate = 0.04;
        int months = 36;
        double loanAmount = getTotalPrice();

        return (loanAmount * rate / 12) / (1 - Math.pow(1 + rate / 12, -months));
    }
}
