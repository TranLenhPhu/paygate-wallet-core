package vn.paygate.wallet.core.model.output;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.sql.Date;

@Getter
@Setter
public class WithdrawOutput {

    String error_code;

    Long sender_id;
    BigDecimal sender_balance;

    Long fee_config_id;
    Long fee_config_type;

    Long withdraw_transaction_id;
    BigDecimal withdraw_transaction_amount;
    Long withdraw_transaction_receiver;
    Long withdraw_transaction_sender;
    Date withdraw_transaction_performed_time;
    String withdraw_transaction_ver02_trans_id;
    String withdraw_transaction_ver02_trans_performed_time;

    Long cashout_request_id;
    BigDecimal cashout_request_amount;
    Long cashout_request_type;
    Long cashout_request_receiver;
    Long cashout_request_sender;
    Date cashout_request_performed_time;
    Long cashout_request_ver02_cashout_id;
    Date cashout_request_ver02_cashout_performed_time;
    Long cashout_request_ver02_bank_id;
    String cashout_request_bank_code;
    String cashout_request_bank_number;
    String cashout_request_bank_account_name;

}
