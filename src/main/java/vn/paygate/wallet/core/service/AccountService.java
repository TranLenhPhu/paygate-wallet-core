package vn.paygate.wallet.core.service;

import vn.paygate.wallet.core.entity.Account;
import vn.paygate.wallet.core.repository.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

@Service
public class AccountService {

    @Autowired
    private AccountRepository accountRepository;

    @Autowired
    private TransactionService transactionService;

    public Account findById(Long id) {
        return accountRepository.findAccountById(id);
    }

    public List<Account> findByIdFirstAndIdLast(Long idFirst, Long idLast) {
        return accountRepository.findByIdFirstAndIdLast(idFirst, idLast);
    }

    public BigDecimal getCurrentBalance(Account account) {
        //TODO: refactor phần này
        // - Chuyển về 1-2 sql tự tính tiền 
        // - db trả về core giá trị đã tính

//        LocalDate localDate = LocalDate.now();
//        Date now = Date.valueOf(localDate);
//
//        List<Transaction> transactions_in = transactionService.findByReceiverAndPerformedTime(account.getId(), now);
//        List<Transaction> transactions_out = transactionService.findBySenderAndPerformedTime(account.getId(), now);
//
//        BigDecimal balance = account.getEndDateBalance();
//        for (Transaction transaction : transactions_in) {
//            balance = balance.add(transaction.getAmount());
//        }
//        for (Transaction transaction : transactions_out) {
//            balance = balance.subtract(transaction.getAmount());
//        }
        return accountRepository.getCurrentBalance(account.getId());
    }

    public Account save(Account account) {
        return accountRepository.save(account);
    }

    public Long getMaxId() {
        return accountRepository.getMaxId();
    }

    public void deleteAccount(Long id) {
        accountRepository.deleteById(id);
    }
}
