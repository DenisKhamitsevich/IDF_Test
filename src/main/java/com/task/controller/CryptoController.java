package com.task.controller;

import com.task.entity.Cryptocurrency;
import com.task.service.CryptoService;
import com.task.service.NotifyService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/crypto")
@Api(tags = "Crypto")
@AllArgsConstructor
public class CryptoController {

    private final CryptoService cryptoService;

    private final NotifyService notifyService;

    @GetMapping
    @ApiOperation(value = "Get all cryptocurrencies")
    public List<Cryptocurrency> getAll() {
        return cryptoService.getAllCurrencies();
    }

    @GetMapping("{symbol}")
    @ApiOperation(value = "Get current cryptocurrency price")
    public Double getCurrencyPrice(@PathVariable String symbol) {
        return cryptoService.getCurrencyPrice(symbol);
    }

    @PostMapping
    @ApiOperation(value = "Sign up for currency notification")
    public void notify(@RequestParam String username,@RequestParam String symbol) {
        notifyService.notify(username,symbol);
    }
}
