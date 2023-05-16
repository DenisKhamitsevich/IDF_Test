package com.task.repository;

import com.task.entity.CurrencyPrice;
import com.task.other.error.Thrower;
import com.task.other.error.exception.NotFoundException;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CurrencyPriceRepository extends JpaRepository<CurrencyPrice, String> {
    /**
     * Retrieves CurrencyPrice that associated with the given symbol or throws exception if such currency is not found
     *
     * @param symbol currency symbol
     * @throws NotFoundException if currency with given symbol is not found
     */
    default CurrencyPrice bySymbol(String symbol) {
        Optional<CurrencyPrice> currency = findById(symbol);
        Thrower.notFound(!currency.isPresent(), "currency.not-found");
        return currency.get();
    }

}
