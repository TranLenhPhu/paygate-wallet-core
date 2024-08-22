package vn.paygate.wallet.core.service;


import vn.paygate.wallet.core.model.input.DepositInput;
import vn.paygate.wallet.core.model.input.WithdrawInput;
import vn.paygate.wallet.core.repository.CashinRepository;
import vn.paygate.wallet.core.repository.CashoutRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import vn.paygate.wallet.core.entity.*;

import java.sql.Date;

@Service
public class CashService {

    @Autowired
    private CashinRepository cashinRepository;

    @Autowired
    private CashoutRepository cashoutRepository;

    public Cashin save(Cashin cashin) {
        return cashinRepository.save(cashin);
    }

    public Cashout save(Cashout cashout){
        return cashoutRepository.save(cashout);
    }

    public Cashin cashin_create(DepositInput depositInput, Transaction luuky_transaction, FeeConfig feeConfig, Date now) {
        Cashin cashin = new Cashin();
        cashin.setOrderId(luuky_transaction.getId());
        cashin.setSettleAmount(depositInput.getAmount());
        cashin.setProviderReceipt(depositInput.getReceiver());
        cashin.setCreateTime(now);
        cashin.setCashinCode(depositInput.getCashin_receipt());
        if(depositInput.getVer02_cashin_id() != null ){
            cashin.setProviderCallbackTime(Date.valueOf(depositInput.getVer02_cashin_id()));
        }
        cashin.setProviderId(feeConfig.getId());
//        cashin.setFeeConfigType(feeConfig.getTransactionType());
        return cashin;
    }

    public Cashout cashout_create(WithdrawInput withdrawInput, Transaction fee_transaction, Account uynhiemchi, Account sender, Date now) {
        Cashout cashout = new Cashout();
        cashout.setAmount(withdrawInput.getAmount().subtract(fee_transaction.getAmount()));
        cashout.setType(1L);
        cashout.setReceiver(uynhiemchi.getId());
        cashout.setSender(sender.getId());
        //cashout.setSettleTime(now);
        cashout.setVer02CashoutId(null);
        cashout.setVer02CashoutPerformedTime(null);
        cashout.setVer02BankID(null);
        cashout.setBankCode(null);
        cashout.setBankNumber(null);
        cashout.setBankAccountName(null);
        return cashout;
    }
}
