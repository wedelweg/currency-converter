package cohort_65.java.currency.service;

import cohort_65.java.currency.dto.ConvertRequest;
import cohort_65.java.currency.dto.ConvertResponse;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import java.util.Map;

@Service
public class CurrencyService {

    @Value("${fixer.api.url}")
    private String apiUrl;

    @Value("${fixer.api.key}")
    private String apiKey;

    private final RestTemplate rest = new RestTemplate();

    private ConvertResponse callFixer(double amount, String from, String to) {
        String url = String.format("%s/latest?access_key=%s", apiUrl, apiKey);

        try {
            Map<String, Object> body = rest.getForObject(url, Map.class);
            System.out.println("Fixer response: " + body);

            if (body == null || !(Boolean) body.get("success")) {
                return new ConvertResponse(amount, from, to, -1, "Fixer error or empty response");
            }

            Map<String, Object> rates = (Map<String, Object>) body.get("rates");

            if (!rates.containsKey(from) || !rates.containsKey(to)) {
                return new ConvertResponse(amount, from, to, -1, "Unsupported currency code");
            }

            double rateFrom = ((Number) rates.get(from)).doubleValue();
            double rateTo = ((Number) rates.get(to)).doubleValue();

            double result;
            if (from.equals("EUR")) {
                result = amount * rateTo;
            } else if (to.equals("EUR")) {
                result = amount / rateFrom;
            } else {
                result = amount * (rateTo / rateFrom);
            }

            return new ConvertResponse(amount, from, to, result, "Success");

        } catch (RestClientException e) {
            return new ConvertResponse(amount, from, to, -1, "HTTP error: " + e.getMessage());
        } catch (Exception e) {
            return new ConvertResponse(amount, from, to, -1, "Unexpected error: " + e.getMessage());
        }
    }

    public ConvertResponse usdToEur(ConvertRequest req) {
        return callFixer(req.getAmount(), "USD", "EUR");
    }

    public ConvertResponse eurToUsd(ConvertRequest req) {
        return callFixer(req.getAmount(), "EUR", "USD");
    }

    public ConvertResponse convertUsdCross(ConvertRequest req) {
        String target = req.getCurrency();
        if (target == null || target.isBlank()) {
            return new ConvertResponse(req.getAmount(), "USD", "?", -1, "currency must be provided");
        }
        return callFixer(req.getAmount(), "USD", target.toUpperCase());
    }
}
