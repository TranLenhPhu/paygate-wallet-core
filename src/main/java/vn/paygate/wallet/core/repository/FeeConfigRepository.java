package vn.paygate.wallet.core.repository;

import vn.paygate.wallet.core.entity.FeeConfig;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FeeConfigRepository extends JpaRepository<FeeConfig, Long> {

    FeeConfig findFeeConfigByAccountIdAndTransactionType(Long accountId, Long transactionType);

}