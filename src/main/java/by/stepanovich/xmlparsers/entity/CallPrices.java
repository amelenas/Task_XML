package by.stepanovich.xmlparsers.entity;

import java.io.Serializable;

public class CallPrices implements Serializable {
    private static final long serialVersionUID = 123L;
    private Double innerCallPrice;
    private Double outerCallPrice;
    private Double cityCallPrice;

    public CallPrices() {
    }

    public CallPrices(Double innerCallPerMinutePrice, Double outerCallPerMinutePrice, Double cityCallPerMinutePrice) {
        this.innerCallPrice = innerCallPerMinutePrice;
        this.outerCallPrice = outerCallPerMinutePrice;
        this.cityCallPrice = cityCallPerMinutePrice;
    }

    public Double getInnerCallPrice() {
        return innerCallPrice;
    }

    public void setInnerCallPrice(Double innerCallPrice) {
        this.innerCallPrice = innerCallPrice;
    }

    public Double getOuterCallPrice() {
        return outerCallPrice;
    }

    public void setOuterCallPrice(Double outerCallPrice) {
        this.outerCallPrice = outerCallPrice;
    }

    public Double getCityCallPrice() {
        return cityCallPrice;
    }

    public void setCityCallPrice(Double cityCallPrice) {
        this.cityCallPrice = cityCallPrice;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CallPrices callPrices = (CallPrices) o;
        return  (innerCallPrice == callPrices.innerCallPrice || (innerCallPrice != null && innerCallPrice.equals(callPrices.innerCallPrice))) &&
                (outerCallPrice == callPrices.outerCallPrice || (outerCallPrice != null && outerCallPrice.equals(callPrices.outerCallPrice))) &&
                (cityCallPrice == callPrices.cityCallPrice || (cityCallPrice != null && cityCallPrice.equals(callPrices.cityCallPrice)));

    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + (innerCallPrice == null ? 0 : innerCallPrice.hashCode());
        result = prime * result + (outerCallPrice == null ? 0 : outerCallPrice.hashCode());
        result = prime * result + (cityCallPrice == null ? 0 : cityCallPrice.hashCode());
        return result;
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append(this.getClass());
        builder.append(" innerCallPrice = ").append(innerCallPrice);
        builder.append(" outerCallPrice = ").append(outerCallPrice);
        builder.append(" cityCallPrice = ").append(cityCallPrice);
        return builder.toString();
    }
}
