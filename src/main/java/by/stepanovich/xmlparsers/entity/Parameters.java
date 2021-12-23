package by.stepanovich.xmlparsers.entity;

import java.io.Serializable;

public class Parameters implements Serializable {
    private static final long serialVersionUID = 124L;
    private Integer favoriteNumbersAvailable;
    private Tariffication tariffication;
    private Double connectPrice;

    public Parameters() {
    }

    public Parameters(int favoriteNumbersAvailable, Tariffication tariffication, Double connectPrice) {
        this.favoriteNumbersAvailable = favoriteNumbersAvailable;
        this.tariffication = tariffication;
        this.connectPrice = connectPrice;
    }

    public Integer getFavoriteNumbersAvailable() {
        return favoriteNumbersAvailable;
    }

    public void setFavoriteNumbersAvailable(Integer favoriteNumbersAvailable) {
        this.favoriteNumbersAvailable = favoriteNumbersAvailable;
    }

    public Tariffication getTariffication() {
        return tariffication;
    }

    public void setTariffication(Tariffication tariffication) {
        this.tariffication = tariffication;
    }

    public Double getConnectPrice() {
        return connectPrice;
    }

    public void setConnectPrice(Double connectPrice) {
        this.connectPrice = connectPrice;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Parameters parameters = (Parameters) o;
        return (favoriteNumbersAvailable == parameters.favoriteNumbersAvailable || (favoriteNumbersAvailable != null && favoriteNumbersAvailable.equals(parameters.favoriteNumbersAvailable))) &&
                (tariffication == parameters.tariffication || (tariffication != null && tariffication.equals(parameters.tariffication))) &&
                (connectPrice == parameters.connectPrice || (connectPrice != null && connectPrice.equals(parameters.connectPrice)));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + (favoriteNumbersAvailable == null ? 0 : favoriteNumbersAvailable.hashCode());
        result = prime * result + (tariffication == null ? 0 : tariffication.hashCode());
        result = prime * result + (connectPrice == null ? 0 : connectPrice.hashCode());
        return result;
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append(this.getClass());
        builder.append(" favoriteNumbersAvailable = ").append(favoriteNumbersAvailable);
        builder.append(" tariffication = ").append(tariffication);
        builder.append(" connectPrice = ").append(connectPrice);
        return builder.toString();
    }
}
