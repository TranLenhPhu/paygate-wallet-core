package vn.paygate.wallet.core.model.output;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.sql.Date;

@Getter
@Setter
public class TransferOutput {

    String error_code;

    Long sender_id;
    BigDecimal sender_balance;

    Long receiver_id;
    BigDecimal receiver_balance;

    Long fee_config_id;
    Long fee_config_type;

    Long transaction_id;
    BigDecimal transaction_amount;
    Long transaction_receiver;
    Long transaction_sender;
    Date transaction_performed_time;
    Long transaction_ver02_trans_id;
    Date transaction_ver02_trans_performed_time;

    Long sender_fee_transaction_id;
    BigDecimal sender_fee_transaction_amount;
    Long sender_fee_transaction_receiver;
    Long sender_fee_transaction_sender;
    Date sender_fee_transaction_performed_time;

    Long receiver_fee_transaction_id;
    BigDecimal receiver_fee_transaction_amount;
    Long receiver_fee_transaction_receiver;
    Long receiver_fee_transaction_sender;
    Date receiver_fee_transaction_performed_time;

}
