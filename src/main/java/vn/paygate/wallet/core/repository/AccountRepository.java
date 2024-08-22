package vn.paygate.wallet.core.repository;

import vn.paygate.wallet.core.entity.Account;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.List;

@Repository
public interface AccountRepository extends JpaRepository<Account, Long> {

    Account findAccountById(Long id);

    @Query(value = "SELECT ((SELECT COALESCE(SUM(t.amount), 0) FROM Transaction t WHERE t.receiver = :accountId AND t.performed_time = CURRENT_DATE) - " +
            "(SELECT COALESCE(SUM(t.amount), 0) FROM Transaction t WHERE t.sender = :accountId AND t.performed_time = CURRENT_DATE)) + " +
            "(SELECT a.end_date_balance FROM Account a WHERE a.id = :accountId) " +
            "FROM Account a WHERE a.id = :accountId", nativeQuery = true)
    BigDecimal getCurrentBalance(@Param("accountId") Long accountId);

    @Query(value = "SELECT a FROM Account a WHERE a.id >= :idFirst AND a.id <= :idLast")
    List<Account> findByIdFirstAndIdLast(@Param("idFirst") Long idFirst, @Param("idLast") Long idLast);

    @Query(value = "SELECT MAX(a.id) FROM Account a")
    Long getMaxId();

}