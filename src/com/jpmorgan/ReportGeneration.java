package com.jpmorgan;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static com.jpmorgan.TransactionsUtil.*;

public class ReportGeneration {

    public static void main(String args[]) {
        List<Transaction> listOfTransaction =TransactionDataHelper.getEnityListFromUserInput();
        //sorting by tradeAmount
        TransactionsUtil.sortingTranactionByTradeAmount(listOfTransaction);


        List<Transaction> listOfSellingTransaction = new ArrayList<>();
        List<Transaction> listOfBuyingTransaction = new ArrayList<>();

        groupingByBuyingAndSelling(listOfTransaction, listOfSellingTransaction,listOfBuyingTransaction);

        //group the amount by date for incoming (Selling)
        Map<LocalDate, BigDecimal> incomingTotalAmountPerDay = groupTrasactionAmtByDate(listOfSellingTransaction);

        //group the amount by date for outgoing (Buying)
        Map<LocalDate, BigDecimal> outgoingTotalAmountPerDay = groupTrasactionAmtByDate(listOfBuyingTransaction);

        //Print total amount for the day transaction of incoming and ourgoing
        printBuyingAndSellingAmounsByDate(incomingTotalAmountPerDay, outgoingTotalAmountPerDay);

       //print the transaction details with ranking
        printTransactionDetailsWithRanking(listOfSellingTransaction, listOfBuyingTransaction);


    }




}
