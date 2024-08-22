package vn.paygate.wallet.core.service;
import vn.paygate.wallet.core.global_variable.ErrorCode;
import vn.paygate.wallet.core.global_variable.FeeConfigVariable;
import vn.paygate.wallet.core.global_variable.GlobalAccounts;
import vn.paygate.wallet.core.model.input.AccountInput;
import vn.paygate.wallet.core.model.input.DepositInput;
import vn.paygate.wallet.core.model.input.TransferInput;
import vn.paygate.wallet.core.model.input.WithdrawInput;
import vn.paygate.wallet.core.model.output.DepositOutput;
import vn.paygate.wallet.core.model.output.TransferOutput;
import vn.paygate.wallet.core.model.output.WithdrawOutput;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import vn.paygate.wallet.core.entity.*;

import java.math.BigDecimal;
import java.sql.Date;
import java.time.LocalDate;
import java.util.List;

@Service
public class APIService {

    @Autowired
    private AccountService accountService; // TODO: sử dụng AccountService để lấy thông tin tài khoản
    @Autowired
    private FeeConfigService feeConfigService; // TODO: sử dụng FeeConfigService để lấy thông tin cấu hình phí
    @Autowired
    private TransactionService transactionService;  // TODO: sử dụng TransactionService để tạo các giao dịch
    @Autowired
    private CashService cashService;  // TODO: sử dụng CashService để tạo các phiếu thu và phiếu chi
    @Autowired
    private OutputService outputService; // TODO: sử dụng OutputService để đưa ra output phù hợp từng request

    @Transactional
    public DepositOutput deposit(DepositInput depositInput) {
        DepositOutput depositOutput = new DepositOutput();
        if(depositInput == null){
            depositOutput.setError_code(ErrorCode.INVALID_INPUT);
            return depositOutput;
        }
        Account fee = GlobalAccounts.FEE;
        Account admin = GlobalAccounts.ADMIN;
        Account luuky = GlobalAccounts.LUUKY;
        Account receiver = accountService.findById(depositInput.getReceiver());

        if (receiver == null) {
            depositOutput.setError_code(ErrorCode.INVALID_RECEIVER);
            return depositOutput;
        }

        LocalDate localDate = LocalDate.now();
        Date now = Date.valueOf(localDate);

        FeeConfig feeConfig = feeConfigService.findByAccountIdAndTransactionType(receiver.getId(), FeeConfigVariable.TRANSACTION_TYPE_DEPOSIT);
        if (feeConfig == null) {
            feeConfig = feeConfigService.findByAccountIdAndTransactionType(null, FeeConfigVariable.TRANSACTION_TYPE_DEPOSIT); // Cau hinh phi mac dinh
            depositOutput.setCashin_fee_config_type(FeeConfigVariable.TYPE_DEFAULT);
        }
        else {
            depositOutput.setCashin_fee_config_type(FeeConfigVariable.TYPE_CUSTOM);
        }
        depositOutput.setCashin_fee_config_id(feeConfig.getId());

        // admin -> luuky
        Transaction luuky_transaction = transactionService.luuky_transaction_create(depositInput, admin, luuky, feeConfig, now);

        // luuky -> fee
        Transaction fee_transaction = transactionService.fee_transaction_create(luuky_transaction, luuky, fee, feeConfig, now);

        //luuky -> receiver
        Transaction receipt_transaction = transactionService.receipt_transaction_create(depositInput, fee_transaction, luuky, feeConfig, now);

        // Phieu thu
        Cashin cashin = cashService.cashin_create(depositInput, luuky_transaction, feeConfig, now);

        luuky_transaction = transactionService.save(luuky_transaction);
        receipt_transaction = transactionService.save(receipt_transaction);
        fee_transaction = transactionService.save(fee_transaction);

        cashin.setCashinCode(luuky_transaction.getId());
        cashin = cashService.save(cashin);
        luuky_transaction.setCashinId(cashin.getId());
        luuky_transaction = transactionService.save(luuky_transaction);

        depositOutput = outputService.deposit_output_set_param(depositOutput, receiver, receipt_transaction, luuky_transaction, fee_transaction, cashin);

        return depositOutput;
    }

    @Transactional
    public WithdrawOutput withdraw(WithdrawInput withdrawInput) {
        WithdrawOutput withdrawOutput = new WithdrawOutput();

        if (withdrawInput == null) {
            withdrawOutput.setError_code(ErrorCode.INVALID_INPUT);
            return withdrawOutput;
        }

        Account fee = GlobalAccounts.FEE;
        Account uynhiemchi = GlobalAccounts.UYNHIEMCHI;
        Account sender = accountService.findById(withdrawInput.getSender());


        if (sender == null || sender.getId() <= 100){
            withdrawOutput.setError_code(ErrorCode.INVALID_SENDER);
            return withdrawOutput; // Khong tim thay nguoi dung hoac la tai khoan he thong
        }
        BigDecimal currentBalance = accountService.getCurrentBalance(sender);
        if (currentBalance.compareTo(withdrawInput.getAmount()) < 0) {
            withdrawOutput.setError_code(ErrorCode.INVALID_AMOUNT);
            return withdrawOutput; // Khong du tien
        }

        LocalDate localDate = LocalDate.now();
        Date now = Date.valueOf(localDate);

        FeeConfig feeConfig = feeConfigService.findByAccountIdAndTransactionType(sender.getId(), FeeConfigVariable.TRANSACTION_TYPE_WITHDRAW);
        if (feeConfig == null) {
            feeConfig = feeConfigService.findByAccountIdAndTransactionType(null, FeeConfigVariable.TRANSACTION_TYPE_WITHDRAW); // Cau hinh phi mac dinh
            withdrawOutput.setFee_config_type(FeeConfigVariable.TYPE_DEFAULT);
        }
        else {
            withdrawOutput.setFee_config_type(FeeConfigVariable.TYPE_CUSTOM);
        }
        withdrawOutput.setFee_config_id(feeConfig.getId());

        //sender -> uynhiemchi
        Transaction withdraw_transaction = transactionService.withdraw_transaction_create(withdrawInput, sender, uynhiemchi, now);

        //uynhiemchi -> fee
        Transaction fee_transaction = transactionService.fee_transaction_create(withdraw_transaction, uynhiemchi, fee, feeConfig, now);

        // Phieu chi
        Cashout cashout = cashService.cashout_create(withdrawInput, fee_transaction, uynhiemchi, sender, now);

        withdraw_transaction = transactionService.save(withdraw_transaction);
        fee_transaction = transactionService.save(fee_transaction);
        cashout = cashService.save(cashout);

        withdrawOutput = outputService.withdraw_output_set_param(withdrawOutput, sender, currentBalance, withdraw_transaction, cashout, withdrawInput);

        return withdrawOutput;
    }

    @Transactional
    public TransferOutput transfer(TransferInput transferInput) {

        TransferOutput transferOutput = new TransferOutput();

       if (transferInput == null) {
           transferOutput.setError_code(ErrorCode.INVALID_INPUT);
           return transferOutput;
       }

        Account fee = GlobalAccounts.FEE;
        Account sender = accountService.findById(transferInput.getSender());
        Account receiver = accountService.findById(transferInput.getReceiver());

        if (sender == null ||sender.getId() <=100) {
            transferOutput.setError_code(ErrorCode.INVALID_SENDER);
            return transferOutput; // Không tìm thấy người dùng
        }
        if (receiver == null||receiver.getId()<=100) {
            transferOutput.setError_code(ErrorCode.INVALID_RECEIVER);
            return transferOutput; // Không tìm thấy người nhận
        }

        FeeConfig feeConfig = feeConfigService.findByAccountIdAndTransactionType(sender.getId(),FeeConfigVariable.TRANSACTION_TYPE_TRANSFER);
        if (feeConfig == null) {
            feeConfig = feeConfigService.findByAccountIdAndTransactionType(null, FeeConfigVariable.TRANSACTION_TYPE_TRANSFER);
            transferOutput.setFee_config_type(FeeConfigVariable.TYPE_DEFAULT);
        } else {
            transferOutput.setFee_config_type(FeeConfigVariable.TYPE_CUSTOM);
        }
        transferOutput.setFee_config_id(feeConfig.getId());

        LocalDate localDate = LocalDate.now();
        Date now = Date.valueOf(localDate);

        // sender -> receiver
        Transaction transfer_transaction = transactionService.transfer_transaction_create(transferInput, sender, receiver, now);

        // receiver -> fee
        Transaction fee_transaction = transactionService.fee_transaction_create(transfer_transaction, receiver, fee, feeConfig, now);

        transfer_transaction = transactionService.save(transfer_transaction);
        fee_transaction = transactionService.save(fee_transaction);

        transferOutput = outputService.transfer_output_set_param(transferOutput, sender, receiver, transfer_transaction, fee_transaction);
        return transferOutput;
    }

    public List<Account> getAccount(Long idFirst, Long idLast) {
        return accountService.findByIdFirstAndIdLast(idFirst, idLast);
    }

    public Account createAccount(AccountInput account) {
        Account saveAccount = new Account();
        saveAccount.setId(accountService.getMaxId() + 1);
        saveAccount.setEmail(account.getEmail());
        saveAccount.setMobile(account.getMobile());
        saveAccount.setFullName(account.getFullName());
        saveAccount.setEndDateBalance(BigDecimal.ZERO);
        saveAccount.setEndDate(Date.valueOf(LocalDate.now()));
        return accountService.save(saveAccount);
    }

    public Account saveAccount(Account account) {
        return accountService.save(account);
    }

    public void deleteAccount(Long id) {
        accountService.deleteAccount(id);
    }
}
