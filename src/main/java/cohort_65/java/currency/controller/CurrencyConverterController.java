package cohort_65.java.currency.controller;

import cohort_65.java.currency.dto.ConvertRequest;
import cohort_65.java.currency.dto.ConvertResponse;
import cohort_65.java.currency.service.CurrencyService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class CurrencyConverterController {

    private final CurrencyService service;

    public CurrencyConverterController(CurrencyService service) {
        this.service = service;
    }

    @PostMapping("/convert-usd-eur")
    public ConvertResponse usdToEur(@RequestBody ConvertRequest request) {
        return service.usdToEur(request);
    }

    @PostMapping("/convert-eur-usd")
    public ConvertResponse eurToUsd(@RequestBody ConvertRequest request) {
        return service.eurToUsd(request);
    }

    @PostMapping("/convert")
    public ConvertResponse convert(@RequestBody ConvertRequest request) {
        return service.convertUsdCross(request);
    }
}
