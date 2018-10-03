package com.jpmorgan;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.LinkedList;
import java.util.List;

/**
 * Transaction helper will help to read the data
 *
 */
public class TransactionDataHelper {

    /**
     * Read the data from text file and convert into java object
     *
     * @return listOfTransaction
     */
    public static List<Transaction> getEnityListFromUserInput() {
        List<Transaction> listOfTransaction = new LinkedList<>();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd MMM yyyy");


        File file = new File("src/Test.txt");
        String fileName = file.getAbsolutePath();
        try (BufferedReader br = new BufferedReader(new FileReader(fileName))) {

            String line;
            while ((line = br.readLine()) != null) {
                String[] values = line.split(",");
                Transaction transaction = new Transaction();
                transaction.setEntityName(values[0]);
                transaction.setTypeOfTransaction(values[1]);
                BigDecimal agreedFx = new BigDecimal(values[2]);
                transaction.setAgreedFx(agreedFx);
                transaction.setCurrency(values[3]);

                transaction.setInstructionDate(LocalDate.parse(values[4], formatter));
                transaction.setSettlementDate(LocalDate.parse(values[5], formatter));

                BigDecimal units = new BigDecimal(values[6]);
                transaction.setUnits(units);
                BigDecimal priceperunit = new BigDecimal(values[7]);
                transaction.setPricePerUnit(priceperunit);
                BigDecimal tradeAmoutInusd = priceperunit.multiply(units).multiply(agreedFx);
                transaction.setTradeAmountInUSD(tradeAmoutInusd);
                listOfTransaction.add(transaction);
            }

        } catch (IOException e) {
            System.out.println("Revisit your file locaiton and path" + e);
        } catch (ArrayIndexOutOfBoundsException ae) {
            System.out.println("Please check your input may contain space" + ae);
        } catch (NumberFormatException ne) {
            System.out.println("Sorry I am not able to coovert your input please correct it " + ne);
            // ne.printStackTrace();
        }


        return listOfTransaction;
    }
}
