package vn.paygate.wallet.core.service;

import vn.paygate.wallet.core.entity.Account;
import vn.paygate.wallet.core.entity.BalanceChange;
import vn.paygate.wallet.core.entity.Transaction;
import vn.paygate.wallet.core.repository.BalanceChangeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.sql.Date;
import java.time.LocalDate;
import java.util.List;

@Service
public class BalanceChangeService {

    @Autowired
    private BalanceChangeRepository balanceChangeRepository;

    @Autowired
    private TransactionService transactionService;

    @Autowired
    private AccountService accountService;

    public BalanceChange save(BalanceChange balanceChange) {
        return balanceChangeRepository.save(balanceChange);
    }

    public BalanceChange settleBalance(Account account) {
        LocalDate localDate = LocalDate.now();
        Date yesterday = Date.valueOf(localDate.minusDays(1));

        List<Transaction> transactions_in = transactionService.findByReceiverAndCreateTime(account.getId(), yesterday);
        BigDecimal transactionInTotal = BigDecimal.valueOf(0);
        Long transactionInCount = 0L;
        for (Transaction transaction : transactions_in) {
            transactionInTotal = transactionInTotal.add(transaction.getAmount());
            transactionInCount++;
        }

        List<Transaction> transactions_out = transactionService.findBySenderAndCreateTime(account.getId(), yesterday);
        BigDecimal transactionOutTotal = BigDecimal.valueOf(0);
        Long transactionOutCount = 0L;
        for (Transaction transaction : transactions_out) {
            transactionOutTotal = transactionOutTotal.add(transaction.getAmount());
            transactionOutCount++;
        }

        account.setEndDateBalance(account.getEndDateBalance().add(transactionInTotal).subtract(transactionOutTotal));
        account.setEndDate(yesterday);
        account = accountService.save(account);

        BalanceChange balanceChange = new BalanceChange();
        balanceChange.setAccountId(account.getId());
        balanceChange.setTransactionInCount(transactionInCount);
        balanceChange.setTransactionInTotal(transactionInTotal);
        balanceChange.setTransactionOutCount(transactionOutCount);
        balanceChange.setTransactionOutTotal(transactionOutTotal);
        balanceChange.setEndDateBalance(account.getEndDateBalance());
        balanceChange.setEndDate(yesterday);
        balanceChange = save(balanceChange);

        return balanceChange;
    }
}
