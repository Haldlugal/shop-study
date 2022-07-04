package ru.gb.gbshopmay.model;

import lombok.*;

import java.io.Serializable;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class PriceChangedMessage implements Serializable {

    static final long serialVersionUID = 6076956653392542387L;

    private String oldPrice;
    private String newPrice;

    @Override
    public String toString() {
        return "Price changed. OldPrice: " + oldPrice +  " newPrice: " + newPrice;
    }
}
