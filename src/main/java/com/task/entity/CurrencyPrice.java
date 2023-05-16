package com.task.entity;

import lombok.*;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
@Getter
@Setter
@EqualsAndHashCode(of = "symbol")
@AllArgsConstructor
@NoArgsConstructor
public class CurrencyPrice {

    @Id
    private String symbol;

    @Column
    private Double price;

    public CurrencyPrice(String symbol){
        this.symbol=symbol;
    }
}
