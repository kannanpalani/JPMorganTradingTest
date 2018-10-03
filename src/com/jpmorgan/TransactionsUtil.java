package com.jpmorgan;

import java.math.BigDecimal;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.temporal.TemporalAdjusters;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class TransactionsUtil {

    /**
     * calculate the total amount per day
     *
     * @param listOfTransaction
     * @return
     */

    public static Map<LocalDate, BigDecimal> groupTrasactionAmtByDate(List<Transaction> listOfTransaction) {
        Map<LocalDate, BigDecimal> groupByDateTotalTradAmount = new HashMap<>();
        for (Transaction transactionInstanceItem : listOfTransaction) {

            BigDecimal totalAmountOfTradByDate = groupByDateTotalTradAmount.get(transactionInstanceItem.getSettlementDate());
            if (totalAmountOfTradByDate == null) {
                groupByDateTotalTradAmount.put(transactionInstanceItem.getSettlementDate(), transactionInstanceItem.getTradeAmountInUSD());
            } else {
                BigDecimal temptotalAmountOfTradByDate = totalAmountOfTradByDate.add(transactionInstanceItem
                        .getTradeAmountInUSD());
                groupByDateTotalTradAmount.put(transactionInstanceItem.getSettlementDate(), temptotalAmountOfTradByDate);
            }
        }
        return groupByDateTotalTradAmount;
    }

    public static void groupingByBuyingAndSelling(List<Transaction> listOfTransaction, List<Transaction>
            listOfSellingTransaction, List<Transaction> listOfBuyingTransaction) {
        int sellingRank = 1;
        int buyingRank = 1;
        for (Transaction transactionItem : listOfTransaction) {
                       if (("S").equalsIgnoreCase(transactionItem.getTypeOfTransaction())) {
                buildEntityList(listOfSellingTransaction, sellingRank, transactionItem);
                sellingRank++;
            } else if (("B").equalsIgnoreCase(transactionItem.getTypeOfTransaction())) {
                buildEntityList(listOfBuyingTransaction, buyingRank, transactionItem);
                buyingRank++;
            }
        }
    }

    public static void printBuyingAndSellingAmounsByDate(Map<LocalDate, BigDecimal> incomingTotalAmountPerDay,
                                                         Map<LocalDate, BigDecimal> outgoingTotalAmountPerDay) {
        System.out.println("===================Settlement incoming By Date ======================");
        System.out.println("Date         " + "    Amount   in USD  \n");

        //Printing Incoming (Selling) amount in USD by Date
        printSumOfTradeAmountByDate(incomingTotalAmountPerDay);
        System.out.println("");
        System.out.println("===================Settlement outgoing By Date ======================");
        System.out.println("Date         " + "    Amount   in USD  \n");

        //Printing outgoing (Buying) amount in USD by Date
        printSumOfTradeAmountByDate(outgoingTotalAmountPerDay);
    }

    public static void printTransactionDetailsWithRanking(List<Transaction> listOfSellingTransaction, List<Transaction>
            listOfBuyingTransaction) {
        printHeadings();
        // printing all the values with ranking for incoming
        listOfSellingTransaction.forEach(System.out::println);

        System.out.println("\n\n");

        // printing all the values with ranking for outgoing
        listOfBuyingTransaction.forEach(System.out::println);

    }

    private static void printHeadings() {
        System.out.println
                ("\n\n==========================================================================================================================================================");
        System.out.println("Transaction   , Buy / Sell , AgreedFx   , Currency   , InstructionDate    , " +
                "SettlementDate   , Units     , Price per unit , Rank ,   Trading Amount in USD  ");

        System.out.println
                ("==========================================================================================================================================================");
    }



    /**
     * Build the entity list for selling or buying
     *
     * @param listOfBuyingTransaction
     * @param rank
     * @param transactionItem
     */
    private static void buildEntityList(List<Transaction> listOfBuyingTransaction, int rank, Transaction transactionItem) {
        transactionItem.setRank(rank);
        findAndAssembleNextWorkingDayIfSettelementDayisWeekend(transactionItem);
        listOfBuyingTransaction.add(transactionItem);
    }

    /**
     * Find the weekend day based on currency
     * if settlement day is weekend set the next working day as settlement day.
     *
     * @param transactionItem
     */
    private static void findAndAssembleNextWorkingDayIfSettelementDayisWeekend(Transaction transactionItem) {
        DayOfWeek dayOfWeek = transactionItem.getSettlementDate().getDayOfWeek();
        if (transactionItem.getCurrency().equalsIgnoreCase("AED") || transactionItem.getCurrency().equalsIgnoreCase("SAR")) {
            if (DayOfWeek.FRIDAY.equals(dayOfWeek) || DayOfWeek.SATURDAY.equals(dayOfWeek))
                transactionItem.setSettlementDate(transactionItem.getSettlementDate().with(TemporalAdjusters.next(DayOfWeek.SUNDAY)));
        } else {
            if (DayOfWeek.SATURDAY.equals(dayOfWeek) || DayOfWeek.SUNDAY.equals(dayOfWeek))
                transactionItem.setSettlementDate(transactionItem.getSettlementDate().with(TemporalAdjusters.next
                        (DayOfWeek.MONDAY)));
        }
    }

    private static void printSumOfTradeAmountByDate(Map<LocalDate, BigDecimal> incomingTotalAmountPerDay) {
        for (Map.Entry<LocalDate, BigDecimal> incomingTotalAmountPerDayEntry : incomingTotalAmountPerDay.entrySet()) {
            System.out.println(incomingTotalAmountPerDayEntry.getKey() + "        " + incomingTotalAmountPerDayEntry.getValue());
        }
    }
    public static void sortingTranactionByTradeAmount(List<Transaction> listOfTransaction) {
        listOfTransaction.sort((Transaction transaction1, Transaction transaction2) -> transaction2.getTradeAmountInUSD().compareTo(transaction1.getTradeAmountInUSD()));
    }
}
