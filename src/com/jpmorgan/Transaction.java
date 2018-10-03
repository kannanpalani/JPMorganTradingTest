package com.jpmorgan;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;


/**
 *
 * This pojo contains the entity details about the buying and selling .
 *
 */
public class Transaction {
    private String entityName;
    private String typeOfTransaction;
    private BigDecimal agreedFx;
    private String currency;
    private LocalDate instructionDate;
    private LocalDate  settlementDate;
    private BigDecimal units;
    private BigDecimal pricePerUnit;
    private BigDecimal tradeAmountInUSD;
    private Integer rank;

    public Transaction() {
    }


    public void setUnits(BigDecimal units) {
        this.units = units;
    }

    public BigDecimal getTradeAmountInUSD() {
        return tradeAmountInUSD;
    }

    public void setTradeAmountInUSD(BigDecimal tradeAmountInUSD) {
        this.tradeAmountInUSD = tradeAmountInUSD;
    }

    public String getEntityName() {
        return entityName;
    }

    public void setEntityName(String entityName) {
        this.entityName = entityName;
    }

    public String getTypeOfTransaction() {
        return typeOfTransaction;
    }

    public void setTypeOfTransaction(String typeOfTransaction) {
        this.typeOfTransaction = typeOfTransaction;
    }

    public BigDecimal getAgreedFx() {
        return agreedFx;
    }

    public void setAgreedFx(BigDecimal agreedFx) {
        this.agreedFx = agreedFx;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public LocalDate getInstructionDate() {
        return instructionDate;
    }

    public void setInstructionDate(LocalDate instructionDate) {
        this.instructionDate = instructionDate;
    }

    public LocalDate getSettlementDate() {
        return settlementDate;
    }

    public void setSettlementDate(LocalDate settlementDate) {
        this.settlementDate = settlementDate;
    }

    public BigDecimal getPricePerUnit() {
        return pricePerUnit;
    }

    public void setPricePerUnit(BigDecimal pricePerUnit) {
        this.pricePerUnit = pricePerUnit;
    }

    public BigDecimal getUnits() {
        return units;
    }

    public Integer getRank() {
        return rank;
    }

    public void setRank(Integer rank) {
        this.rank = rank;
    }

    @Override
    public String toString() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd MMM yyyy");
        return  entityName + "              " +
                        typeOfTransaction +
                "          " + agreedFx +
                "           " + currency +
                "         " + instructionDate.format(formatter)+
                "         " + settlementDate.format(formatter) +
                "         " + units +
                "          " + pricePerUnit +
                "          " +rank  +
                "         " + tradeAmountInUSD ;
    }
}
