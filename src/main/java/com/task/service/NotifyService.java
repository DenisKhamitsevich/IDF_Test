package com.task.service;

import com.task.entity.Cryptocurrency;
import com.task.entity.CurrencyPrice;
import com.task.entity.PriceNotify;
import com.task.repository.CryptoRepository;
import com.task.repository.CurrencyPriceRepository;
import com.task.repository.PriceNotifyRepository;
import lombok.RequiredArgsConstructor;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class NotifyService {

    private final PriceNotifyRepository notifyRepository;

    private final CurrencyPriceRepository priceRepository;

    private final CryptoRepository cryptoRepository;

    private final RestTemplate restTemplate;
    private final Log logger = LogFactory.getLog(getClass());

    /**
     * URL that is used to fetch information about cryptocurrency price
     */
    private final String FETCH_URL = "https://api.coinlore.net/api/ticker/?id=";


    /**
     * Sign up user for cryptocurrency price change
     *
     * @param username username of user that wants to sign up
     * @param symbol   currency symbol
     */
    public void notify(String username, String symbol) {
        PriceNotify priceNotify = new PriceNotify(username, symbol);
        CurrencyPrice currencyPrice = priceRepository.bySymbol(symbol);
        priceNotify.setPrice(currencyPrice.getPrice());
        notifyRepository.save(priceNotify);
    }

    /**
     * Fetches current cryptocurrency prices every minute
     */
    @Scheduled(cron = "0 * * * * *")
    public void fetchPrices() {
        List<Cryptocurrency> currencies = cryptoRepository.findAll();
        currencies.stream().forEach((cryptocurrency) -> {
            String symbol = cryptocurrency.getSymbol();
            ArrayList result = restTemplate.getForObject(FETCH_URL + cryptocurrency.getId(), ArrayList.class);
            if (result == null)
                return;
            LinkedHashMap<String, String> mapResult = (LinkedHashMap<String, String>) result.get(0);
            Double price = Double.valueOf(mapResult.get("price_usd"));
            Optional<CurrencyPrice> oldPrice = priceRepository.findById(symbol);
            CurrencyPrice currencyPrice = oldPrice.orElse(new CurrencyPrice(symbol));
            currencyPrice.setPrice(price);
            priceRepository.save(currencyPrice);
        });
        notifyUsers();
    }

    /**
     * Checks for users to notify about cryptocurrency price change
     */
    private void notifyUsers() {
        List<PriceNotify> notifyList = notifyRepository.findAll();
        notifyList.stream().forEach((e) -> {
            double difference = getPercentDifference(e.getPrice(), priceRepository.bySymbol(e.getSymbol()).getPrice());
            if (difference > 0.01)
                logger.warn("symbol: " + e.getSymbol() + ", username: " + e.getUsername() + ", difference: " + difference * 100 + "%");
        });
    }

    /**
     * Calculates the percent difference between old and new cryptocurrency prices
     *
     * @param oldPrice old cryptocurrency price
     * @param newPrice new cryptocurrency price
     *
     * @return percent difference between two prices
     */
    private Double getPercentDifference(Double oldPrice, Double newPrice) {
        return Math.abs((oldPrice - newPrice) / oldPrice);
    }


}
