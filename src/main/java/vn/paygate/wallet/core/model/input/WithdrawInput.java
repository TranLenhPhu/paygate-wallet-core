package vn.paygate.wallet.core.model.input;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
public class WithdrawInput {

    @NotNull(message = "sender is required")
    Long sender;
    @NotNull(message = "amount is required")
    BigDecimal amount;
    String ver02_cashout_id;
    String ver02_cashout_performed_time;
    String ver02_trans_id;
    String ver02_trans_performed_time;
    String ver02_bank_id;
    @NotNull(message = "bank_code is required")
    String bank_code;
    @NotNull(message = "bank_number is required")
    String bank_number;
    @NotNull(message = "bank_account_name is required")
    String bank_account_name;

}
