package vn.paygate.wallet.core.repository;

import vn.paygate.wallet.core.entity.BalanceChange;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BalanceChangeRepository extends JpaRepository<BalanceChange, Long> {
}