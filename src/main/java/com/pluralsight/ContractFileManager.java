package com.pluralsight;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

public class ContractFileManager {

    public void saveContracts(List<Contract> contracts) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter("contracts.csv", true))) {
            for (Contract contract : contracts) {
                writeContract(writer, contract);
            }
        } catch (IOException e) {
            System.err.println("Oops! Could not write to file: " + e.getMessage());
        }
    }

    public void saveContract(Contract contract) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter("contracts.csv", true))) {
            writeContract(writer, contract);
        } catch (IOException e) {
            System.err.println("Oops! Could not write to file: " + e.getMessage());
        }
    }

    private void writeContract(BufferedWriter writer, Contract contract) throws IOException {

        writer.write(contract.getDate() + "," +
                contract.getCustomerName() + "," +
                contract.getCustomerEmail() + "," +
                contract.getVehicle().getVin() + "," +
                contract.getVehicle().getYear() + "," +
                contract.getVehicle().getMake() + "," +
                contract.getVehicle().getModel() + "," +
                contract.getVehicle().getVehicleType() + "," +
                contract.getVehicle().getColor() + "," +
                contract.getVehicle().getOdometer() + "," +
                contract.getVehicle().getPrice() + "," +
                contract.getTotalPrice() + "," +
                contract.getMonthlyPayment());


        if (contract instanceof SalesContract) {
            SalesContract sc = (SalesContract) contract;
            writer.write(",SALE," + sc.getSalesTaxAmount() + "," +
                    sc.getRecordingFee() + "," +
                    sc.getProcessingFee() + "," +
                    sc.isFinance());
        } else if (contract instanceof LeaseContract) {
            LeaseContract lc = (LeaseContract) contract;
            writer.write(",LEASE," + lc.getExpectedEndingValue() + "," +
                    lc.getLeaseFee());
        }

        writer.newLine();
    }
}
