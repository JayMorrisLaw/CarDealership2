package com.pluralsight;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

public class ContractFileManager {

    public void saveContracts(List<Contract> contracts) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter("contracts.csv", true))) {
            for (Contract contract : contracts) {

                writer.write(contract.getDate() + "," +
                        contract.getCustomerName() + "," +
                        contract.getCustomerEmail() + "," +
                        contract.getVehicle().toString() + "," +
                        contract.getTotalPrice() + "," +
                        contract.getMonthlyPayment());


                if (contract instanceof SalesContract) {
                    SalesContract sc = (SalesContract) contract;
                    writer.write("," + sc.getSalesTaxAmount() + "," +
                            sc.getRecordingFee() + "," +
                            sc.getProcessingFee() + "," +
                            sc.isFinance());
                } else if (contract instanceof LeaseContract) {
                    LeaseContract lc = (LeaseContract) contract;
                    writer.write("," + lc.getExpectedEndingValue() + "," +
                            lc.getLeaseFee());
                }

                writer.close();
            }
        } catch (IOException e) {
            System.err.println("Oops! Could not write to file: " + e.getMessage());
        }
    }
}
