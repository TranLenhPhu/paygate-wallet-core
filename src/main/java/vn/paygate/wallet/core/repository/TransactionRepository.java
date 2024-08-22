package vn.paygate.wallet.core.repository;

import vn.paygate.wallet.core.entity.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.sql.Date;
import java.util.List;

@Repository
public interface TransactionRepository extends JpaRepository<Transaction, Long> {

    List<Transaction> findTransactionByReceiverAndCreateTime(Long receiver, Date createTime);

    List<Transaction> findTransactionBySenderAndCreateTime(Long sender, Date createTime);
}