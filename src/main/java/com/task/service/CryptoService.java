package com.task.service;

import com.task.entity.Cryptocurrency;
import com.task.entity.CurrencyPrice;
import com.task.repository.CryptoRepository;
import com.task.repository.CurrencyPriceRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CryptoService {

    private final CryptoRepository cryptoRepository;

    private final CurrencyPriceRepository priceRepository;

    /**
     * Retrieves List of currencies
     *
     * @return List of all currencies
     */
    public List<Cryptocurrency> getAllCurrencies() {
        return cryptoRepository.findAll();
    }

    /**
     * Retrieves price of the currency
     *
     * @param symbol currency symbol
     * @return price of the currency, associated with the given symbol
     */
    public Double getCurrencyPrice(String symbol) {
        CurrencyPrice cryptocurrency = priceRepository.bySymbol(symbol);
        return cryptocurrency.getPrice();
    }


}
