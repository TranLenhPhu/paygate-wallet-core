package vn.paygate.wallet.core.service;

import vn.paygate.wallet.core.entity.FeeConfig;
import vn.paygate.wallet.core.repository.FeeConfigRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class FeeConfigService {

    @Autowired
    private FeeConfigRepository feeConfigRepository;

    public FeeConfig findByAccountIdAndTransactionType(Long accountId, Long transactionType) {
        return feeConfigRepository.findFeeConfigByAccountIdAndTransactionType(accountId, transactionType);
    }

    public FeeConfig save(FeeConfig feeConfig) {
        return feeConfigRepository.save(feeConfig);
    }

}
