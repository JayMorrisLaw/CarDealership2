package com.pluralsight;

public class SalesContract extends Contract{
    private double salesTaxAmount;
    private double recordingFee;
    private double processingFee;
    private boolean finance;

    public SalesContract(String date, String customerName, String customerEmail, Vehicle vehicle, boolean finance) {
        super(date, customerName, customerEmail, vehicle);
        this.finance = finance;
        this.recordingFee = 100.00;

        double price = vehicle.getPrice();
        this.salesTaxAmount = price * 0.05;

        this.processingFee = price < 10000 ? 295.00 : 495.00;
    }

    public double getSalesTaxAmount() {
        return salesTaxAmount;
    }

    public void setSalesTaxAmount(double salesTaxAmount) {
        this.salesTaxAmount = salesTaxAmount;
    }

    public double getRecordingFee() {
        return recordingFee;
    }

    public void setRecordingFee(double recordingFee) {
        this.recordingFee = recordingFee;
    }

    public boolean isFinance() {
        return finance;
    }

    public void setFinance(boolean finance) {
        this.finance = finance;
    }

    public double getProcessingFee() {
        return processingFee;
    }

    public void setProcessingFee(double processingFee) {
        this.processingFee = processingFee;
    }

    @Override
    public double getTotalPrice() {
        return getVehicle().getPrice() + salesTaxAmount + recordingFee + processingFee;
    }
    @Override
    public double getMonthlyPayment() {
        if (!finance) return 0.0;

        double basePrice = getVehicle().getPrice();
        double rate = basePrice >= 10000 ? 0.0425 : 0.0525;
        int months = basePrice >= 10000 ? 48 : 24;

        double loanAmount = getTotalPrice();


        return (loanAmount * rate / 12) / (1 - Math.pow(1 + rate / 12, -months));
    }
}
