package vn.paygate.wallet.core.model.input;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
public class DepositInput {

    @NotNull(message = "receiver is required")
    Long receiver;
    @NotNull(message = "amount is required")
    BigDecimal amount;
    @NotNull(message = "cashin is required")
    Long cashin_receipt;
    String ver02_cashin_id;
    String ver02_cashin_performed_time;

}
