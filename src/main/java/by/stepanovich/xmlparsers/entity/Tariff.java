package by.stepanovich.xmlparsers.entity;


import java.io.Serializable;
import java.time.LocalDate;

public class Tariff implements Serializable, Comparable<Tariff> {
    private static final long serialVersionUID = 125L;
    private String tariffName;
    private Operator operatorName;
    private Integer tariffID;
    private Double payroll;
    public CallPrices callPrices;
    private Double smsPrice;
    public Parameters parameters;
    private LocalDate openDate;

    public Tariff() {
    }

    public Tariff(String tariffName, Integer tariffID, Operator operatorName, CallPrices callPrices,
                  Double payroll, Double smsPrice, Parameters parameters, LocalDate openDate) {
        this.tariffName = tariffName;
        this.tariffID = tariffID;
        this.operatorName = operatorName;
        this.callPrices = callPrices;
        this.payroll = payroll;
        this.smsPrice = smsPrice;
        this.parameters = parameters;
        this.openDate = openDate;
    }

    public Tariff(Tariff tariff) {
        this.tariffName = tariff.getTariffName();
        this.tariffID = tariff.getTariffID();
        this.operatorName = tariff.getOperatorName();
        this.callPrices = tariff.getCallPrices();
        this.payroll = tariff.getPayroll();
        this.smsPrice = tariff.getSmsPrice();
        this.parameters = tariff.getParameters();
        this.openDate =  tariff.getOpenDate();
    }

    public String getTariffName() {
        return tariffName;
    }

    public void setTariffName(String tariffName) {
        this.tariffName = tariffName;
    }

    public Integer getTariffID() {
        return tariffID;
    }

    public void setTariffID(Integer tariffID) {
        this.tariffID = tariffID;
    }

    public Operator getOperatorName() {
        return operatorName;
    }

    public void setOperatorName(Operator operatorName) {
        this.operatorName = operatorName;
    }

    public CallPrices getCallPrices() {
        return callPrices;
    }

    public void setCallPrices(CallPrices callPrices) {
        this.callPrices = callPrices;
    }

    public Double getPayroll() {
        return payroll;
    }

    public void setPayroll(Double payroll) {
        this.payroll = payroll;
    }

    public Double getSmsPrice() {
        return smsPrice;
    }

    public void setSmsPrice(Double smsPrice) {
        this.smsPrice = smsPrice;
    }

    public Parameters getParameters() {
        return parameters;
    }

    public void setParameters(Parameters parameters) {
        this.parameters = parameters;
    }

    public LocalDate getOpenDate() {
        return openDate;
    }

    public void setOpenDate(LocalDate openDate) {
        this.openDate = openDate;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || o.getClass() != this.getClass()) return false;
        Tariff tariff = (Tariff) o;
        return (tariffName == tariff.tariffName || (tariffName != null && tariffName.equals(tariff.tariffName))) &&
                (tariffID == tariff.tariffID || (tariffID != null && tariffID.equals(tariff.tariffID))) &&
                (operatorName == tariff.operatorName || (operatorName != null && operatorName.equals(tariff.operatorName))) &&
                (callPrices == tariff.callPrices || (callPrices != null && callPrices.equals(tariff.callPrices))) &&
                (payroll == tariff.payroll || (payroll != null && payroll.equals(tariff.payroll))) &&
                (smsPrice == tariff.smsPrice || (smsPrice != null && smsPrice.equals(tariff.smsPrice))) &&
                (parameters == tariff.parameters || (parameters != null && parameters.equals(tariff.parameters))) &&
                (openDate == tariff.openDate || (openDate != null && openDate.equals(tariff.openDate)));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + (tariffName == null ? 0 : tariffName.hashCode());
        result = prime * result + (tariffID == null ? 0 : tariffID.hashCode());
        result = prime * result + (operatorName == null ? 0 : operatorName.hashCode());
        result = prime * result + (callPrices == null ? 0 : callPrices.hashCode());
        result = prime * result + (payroll == null ? 0 : payroll.hashCode());
        result = prime * result + (smsPrice == null ? 0 : smsPrice.hashCode());
        result = prime * result + (parameters == null ? 0 : parameters.hashCode());
        result = prime * result + (openDate == null ? 0 : openDate.hashCode());
        return result;
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append(this.getClass());
        builder.append(" tariffName = ").append(tariffName);
        builder.append(" tariffID = ").append(tariffID);
        builder.append(" operator = ").append(operatorName);
        builder.append(" callPrices = ").append(callPrices);
        builder.append(" payroll = ").append(payroll);
        builder.append(" smsPrice = ").append(smsPrice);
        builder.append(" parameters = ").append(parameters);
        builder.append(" openDate = ").append(openDate);
        return builder.toString();
    }

    @Override
    public int compareTo(Tariff o) {
        int result = this.tariffID - o.getTariffID();
        if (result == 0) {
            result = this.tariffName.compareTo(o.getTariffName());
        }
        return result;
    }
}
