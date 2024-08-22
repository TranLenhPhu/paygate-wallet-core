package vn.paygate.wallet.core.service;

import vn.paygate.wallet.core.entity.Account;
import vn.paygate.wallet.core.entity.FeeConfig;
import vn.paygate.wallet.core.entity.Transaction;
import vn.paygate.wallet.core.model.input.DepositInput;
import vn.paygate.wallet.core.model.input.TransferInput;
import vn.paygate.wallet.core.model.input.WithdrawInput;
import vn.paygate.wallet.core.repository.TransactionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.sql.Date;
import java.util.List;

@Service
public class TransactionService {

    @Autowired
    private TransactionRepository transactionRepository;

    public List<Transaction> findByReceiverAndCreateTime(Long receiver, Date createTime) {
        return transactionRepository.findTransactionByReceiverAndCreateTime(receiver, createTime);
    }

    public List<Transaction> findBySenderAndCreateTime(Long sender, Date createTime) {
        return transactionRepository.findTransactionBySenderAndCreateTime(sender, createTime);
    }

    public Transaction save(Transaction transaction) {
        return transactionRepository.save(transaction);
    }

    public BigDecimal calculateFee(BigDecimal amount, BigDecimal receiverFeePercentage, BigDecimal receiverFeeFixAmount) {
        return amount.multiply(receiverFeePercentage).add(receiverFeeFixAmount);
    }

    public Transaction luuky_transaction_create(DepositInput depositInput, Account admin, Account luuky, FeeConfig feeConfig, Date now) {
        Transaction luuky_transaction = new Transaction();
        luuky_transaction.setAmount(depositInput.getAmount());
        luuky_transaction.setSender(admin.getId());
        luuky_transaction.setReceiver(luuky.getId());
        luuky_transaction.setCreateTime(now);
        luuky_transaction.setConfigMerchantFeeId(feeConfig.getId());
        luuky_transaction.setType(feeConfig.getTransactionType());
        luuky_transaction.setOrderId(null);
        luuky_transaction.setCashinId(null);
        luuky_transaction.setPaymemtMethodCode(null);
        luuky_transaction.setPaymentMethodBankCode(null);
        luuky_transaction.setSettleTime(null);
        return luuky_transaction;
    }

    public Transaction fee_transaction_create(Transaction luuky_transaction, Account luuky, Account fee, FeeConfig feeConfig, Date now) {
        Transaction fee_transaction = new Transaction();
        fee_transaction.setAmount(calculateFee(luuky_transaction.getAmount(), feeConfig.getReceiverFeePercentage(), feeConfig.getReceiverFeeFixAmount()));
        fee_transaction.setReceiver(fee.getId());
        fee_transaction.setSender(luuky.getId());
        fee_transaction.setCreateTime(now);
        fee_transaction.setConfigMerchantFeeId(feeConfig.getId());
        fee_transaction.setType(feeConfig.getTransactionType());
        fee_transaction.setOrderId(null);
        fee_transaction.setCashinId(null);
        fee_transaction.setPaymemtMethodCode(null);
        fee_transaction.setPaymentMethodBankCode(null);
        fee_transaction.setSettleTime(null);
        return fee_transaction;
    }

    public Transaction receipt_transaction_create(DepositInput depositInput, Transaction fee_transaction, Account luuky, FeeConfig feeConfig, Date now) {
        Transaction receipt_transaction = new Transaction(); //luuky -> receiver
        receipt_transaction.setAmount(depositInput.getAmount().subtract(fee_transaction.getAmount()));
        receipt_transaction.setSender(luuky.getId());
        receipt_transaction.setReceiver(depositInput.getReceiver());
        receipt_transaction.setCreateTime(now);
        receipt_transaction.setConfigMerchantFeeId(feeConfig.getId());
        receipt_transaction.setType(feeConfig.getTransactionType());
        receipt_transaction.setCashinId(null);
        receipt_transaction.setOrderId(null);
        receipt_transaction.setPaymemtMethodCode(null);
        receipt_transaction.setPaymentMethodBankCode(null);
        receipt_transaction.setSettleTime(null);
        return receipt_transaction;
    }

    public Transaction withdraw_transaction_create(WithdrawInput withdrawInput, Account sender, Account uynhiemchi, Date now) {
        Transaction withdraw_transaction = new Transaction();
        withdraw_transaction.setAmount(withdrawInput.getAmount());
        withdraw_transaction.setReceiver(uynhiemchi.getId());
        withdraw_transaction.setSender(sender.getId());
        withdraw_transaction.setCreateTime(now);
        withdraw_transaction.setCashinId(null);
        withdraw_transaction.setOrderId(null);
        withdraw_transaction.setPaymemtMethodCode(null);
        withdraw_transaction.setPaymentMethodBankCode(null);
        return withdraw_transaction;
    }

    public Transaction transfer_transaction_create(TransferInput transferInput, Account sender, Account receiver, Date now) {
        Transaction transfer_transaction = new Transaction();
        transfer_transaction.setAmount(transferInput.getAmount());
        transfer_transaction.setReceiver(receiver.getId());
        transfer_transaction.setSender(sender.getId());
        transfer_transaction.setCreateTime(now);
        transfer_transaction.setCashinId(null);
        transfer_transaction.setOrderId(null);
        transfer_transaction.setPaymentMethodBankCode(null);
        transfer_transaction.setPaymemtMethodCode(null);
        transfer_transaction.setSettleTime(null);
        return transfer_transaction;
    }
}

