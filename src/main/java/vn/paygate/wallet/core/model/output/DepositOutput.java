package vn.paygate.wallet.core.model.output;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.sql.Date;

@Getter
@Setter
public class DepositOutput {

    String error_code;

    Long receiver_id;
    BigDecimal receiver_balance;

    Long receipt_transaction_id;
    BigDecimal receipt_transaction_amount;
    Long receipt_transaction_receiver;
    Long receipt_transaction_sender;
    Date receipt_transaction_performed_time;

    Long sender_fee_transaction_id;
    BigDecimal sender_fee_transaction_amount;
    Long sender_fee_transaction_receiver;
    Long sender_fee_transaction_sender;
    Date sender_fee_transaction_performed_time;
    Long sender_fee_transaction_fee_config_id;
    Long sender_fee_transaction_fee_config_type;

    String luuky_transaction_id;
    String luuky_transaction_amount;
    String luuky_transaction_receiver;
    String luuky_transaction_sender;
    String luuky_transaction_performed_time;

    String cashin_id;
    String cashin_luuky_transaction_id;
    String cashin_amount;
    String cashin_receiver;
    String cashin_performed_time;
    Long cashin_fee_config_id;
    Long cashin_fee_config_type;

}
