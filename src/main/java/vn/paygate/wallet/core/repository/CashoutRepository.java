package vn.paygate.wallet.core.repository;

import vn.paygate.wallet.core.entity.Cashout;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CashoutRepository extends JpaRepository<Cashout, Long> {
}
