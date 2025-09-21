package cohort_65.java.currency.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ConvertResponse {
    private double amount;
    private String from;
    private String to;
    private double result;
    private String message;
}
