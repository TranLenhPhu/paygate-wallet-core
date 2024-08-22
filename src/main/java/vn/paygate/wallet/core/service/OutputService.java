package vn.paygate.wallet.core.service;

import vn.paygate.wallet.core.entity.Account;
import vn.paygate.wallet.core.entity.Cashin;
import vn.paygate.wallet.core.entity.Cashout;
import vn.paygate.wallet.core.entity.Transaction;
import vn.paygate.wallet.core.global_variable.ErrorCode;
import vn.paygate.wallet.core.model.input.WithdrawInput;
import vn.paygate.wallet.core.model.output.DepositOutput;
import vn.paygate.wallet.core.model.output.TransferOutput;
import vn.paygate.wallet.core.model.output.WithdrawOutput;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
public class OutputService {

    @Autowired
    private AccountService accountService;

    public DepositOutput deposit_output_set_param(DepositOutput depositOutput,
                                                  Account receiver,
                                                  Transaction receipt_transaction,
                                                  Transaction luuky_transaction,
                                                  Transaction fee_transaction,
                                                  Cashin cashin){
        depositOutput.setError_code(ErrorCode.SUCCESS);

        depositOutput.setReceiver_id(receiver.getId());
        depositOutput.setReceiver_balance(accountService.getCurrentBalance(receiver));

        depositOutput.setReceipt_transaction_id(receipt_transaction.getId());
        depositOutput.setReceipt_transaction_amount(receipt_transaction.getAmount());
        depositOutput.setReceipt_transaction_receiver(receipt_transaction.getReceiver());
        depositOutput.setReceipt_transaction_sender(receipt_transaction.getSender());
        depositOutput.setReceipt_transaction_performed_time(receipt_transaction.getCreateTime());

        depositOutput.setLuuky_transaction_id(luuky_transaction.getId().toString());
        depositOutput.setLuuky_transaction_amount(luuky_transaction.getAmount().toString());
        depositOutput.setLuuky_transaction_receiver(luuky_transaction.getReceiver().toString());
        depositOutput.setLuuky_transaction_sender(luuky_transaction.getSender().toString());
        depositOutput.setLuuky_transaction_performed_time(receipt_transaction.getCreateTime().toString());

        depositOutput.setSender_fee_transaction_id(fee_transaction.getId());
        depositOutput.setSender_fee_transaction_amount(fee_transaction.getAmount());
        depositOutput.setSender_fee_transaction_receiver(fee_transaction.getReceiver());
        depositOutput.setSender_fee_transaction_sender(fee_transaction.getSender());
        depositOutput.setSender_fee_transaction_performed_time(receipt_transaction.getCreateTime());
//        depositOutput.setSender_fee_transaction_fee_config_id(fee_transaction.getPaymemtMethodCode());
//        depositOutput.setSender_fee_transaction_fee_config_type(fee_transaction.getFeeConfigType());

        depositOutput.setCashin_id(cashin.getId().toString());
        depositOutput.setCashin_luuky_transaction_id(luuky_transaction.getId().toString());
        depositOutput.setCashin_amount(cashin.getSettleAmount().toString());
        depositOutput.setCashin_receiver(cashin.getProviderReceipt().toString());
        depositOutput.setCashin_performed_time(receipt_transaction.getSettleTime().toString());

        return depositOutput;
    }

    public TransferOutput transfer_output_set_param(TransferOutput transferOutput, Account sender, Account receiver, Transaction transfer_transaction, Transaction fee_transaction){

        transferOutput.setError_code(ErrorCode.SUCCESS);

        transferOutput.setSender_id(sender.getId());
        transferOutput.setSender_balance(accountService.getCurrentBalance(sender));

        transferOutput.setReceiver_id(receiver.getId());
        transferOutput.setReceiver_balance(accountService.getCurrentBalance(receiver));

        transferOutput.setTransaction_id(transfer_transaction.getId());
        transferOutput.setTransaction_amount(transfer_transaction.getAmount());
        transferOutput.setTransaction_receiver(transfer_transaction.getReceiver());
        transferOutput.setTransaction_sender(transfer_transaction.getSender());
        transferOutput.setTransaction_ver02_trans_id(null);
        transferOutput.setTransaction_ver02_trans_performed_time(null);

        transferOutput.setSender_fee_transaction_id(null);
        transferOutput.setSender_fee_transaction_amount(null);
        transferOutput.setSender_fee_transaction_receiver(null);
        transferOutput.setSender_fee_transaction_performed_time(null);

        transferOutput.setReceiver_fee_transaction_id(fee_transaction.getConfigMerchantFeeId());
        transferOutput.setReceiver_fee_transaction_amount(fee_transaction.getAmount());
        transferOutput.setSender_fee_transaction_receiver(fee_transaction.getReceiver());
        transferOutput.setReceiver_fee_transaction_performed_time(fee_transaction.getSettleTime());

        return transferOutput;
    }

    public WithdrawOutput withdraw_output_set_param(WithdrawOutput withdrawOutput,
                                                    Account sender,
                                                    BigDecimal currentBalance,
                                                    Transaction withdraw_transaction,
                                                    Cashout cashout,
                                                    WithdrawInput withdrawInput){
        withdrawOutput.setError_code(ErrorCode.SUCCESS);

        withdrawOutput.setSender_id(sender.getId());
        withdrawOutput.setSender_balance(currentBalance.subtract(withdrawInput.getAmount()));

        withdrawOutput.setWithdraw_transaction_id(withdraw_transaction.getId());
        withdrawOutput.setWithdraw_transaction_amount(withdraw_transaction.getAmount());
        withdrawOutput.setWithdraw_transaction_receiver(withdraw_transaction.getReceiver());
        withdrawOutput.setWithdraw_transaction_sender(withdraw_transaction.getSender());
        withdrawOutput.setWithdraw_transaction_performed_time(withdraw_transaction.getCreateTime());
        withdrawOutput.setWithdraw_transaction_ver02_trans_id(null);
        withdrawOutput.setWithdraw_transaction_ver02_trans_performed_time(null);

        withdrawOutput.setCashout_request_id(cashout.getId());
        withdrawOutput.setCashout_request_amount(cashout.getAmount());
        withdrawOutput.setCashout_request_type(cashout.getType());
        withdrawOutput.setCashout_request_receiver(cashout.getReceiver());
        withdrawOutput.setCashout_request_sender(cashout.getSender());
        withdrawOutput.setCashout_request_performed_time(cashout.getPerformedTime());
        withdrawOutput.setCashout_request_ver02_cashout_id(cashout.getVer02CashoutId());
        withdrawOutput.setCashout_request_ver02_cashout_performed_time(cashout.getVer02CashoutPerformedTime());
        withdrawOutput.setCashout_request_ver02_bank_id(cashout.getVer02BankID());
        withdrawOutput.setCashout_request_bank_code(cashout.getBankCode());
        withdrawOutput.setCashout_request_bank_number(cashout.getBankNumber());
        withdrawOutput.setCashout_request_bank_account_name(cashout.getBankAccountName());

        return withdrawOutput;
    }

}
