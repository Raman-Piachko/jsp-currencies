package com.epam.rd.jsp.currencies;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Collection;
import java.util.Map;
import java.util.TreeMap;
import java.util.stream.Collectors;

public class Currencies {
    private Map<String, BigDecimal> curs = new TreeMap<>();

    public void addCurrency(String currency, BigDecimal weight) {
        curs.put(currency, weight);
    }

    public Collection<String> getCurrencies() {
        return curs.keySet();
    }

    public Map<String, BigDecimal> getExchangeRates(String referenceCurrency) {
        BigDecimal refCurrencyValue = curs.get(referenceCurrency);
        Map<String, BigDecimal> collectMap = curs.entrySet()
                .stream()
                .collect(Collectors.toMap(Map.Entry::getKey, e -> convertCurrency(e, refCurrencyValue)));

        return new TreeMap<>(collectMap);
    }

    public BigDecimal convert(BigDecimal amount, String sourceCurrency, String targetCurrency) {
        BigDecimal source = curs.get(sourceCurrency);
        BigDecimal target = curs.get(targetCurrency);
        return calculateCurrency(source, target)
                .multiply(amount)
                .setScale(5, RoundingMode.HALF_UP);
    }

    public BigDecimal calculateCurrency(BigDecimal source, BigDecimal target) {
        return source.divide(target, 100, RoundingMode.HALF_UP);
    }

    private BigDecimal convertCurrency(Map.Entry<String, BigDecimal> entry, BigDecimal rangeValue) {
        return rangeValue.divide(entry.getValue(), 5, RoundingMode.HALF_UP);
    }

}