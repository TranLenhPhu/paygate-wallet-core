package vn.paygate.wallet.core.controller;

import vn.paygate.wallet.core.model.input.DepositInput;
import vn.paygate.wallet.core.model.input.TransferInput;
import vn.paygate.wallet.core.model.input.WithdrawInput;
import vn.paygate.wallet.core.model.output.DepositOutput;
import vn.paygate.wallet.core.model.output.TransferOutput;
import vn.paygate.wallet.core.model.output.WithdrawOutput;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import vn.paygate.wallet.core.service.APIService;
//import vn.paygate.wallet.core.service.MerchantFeeService;

import java.util.List;

@RestController
@RequestMapping("/api")
public class WalletController {

    @Autowired
    private APIService apiService;


    // TODO: sử dụng APIService để xử lý các request tương ứng

    @PostMapping("/deposit")
    public ResponseEntity<DepositOutput> deposit(@RequestBody @Valid DepositInput depositInput) {
        return ResponseEntity.ok(apiService.deposit(depositInput));
    }

    @PostMapping("/withdraw")
    public ResponseEntity<WithdrawOutput> withdraw(@RequestBody @Valid WithdrawInput withdrawInput) {
        return ResponseEntity.ok(apiService.withdraw(withdrawInput));
    }

    @PostMapping("/transfer")
    public ResponseEntity<TransferOutput> transfer(@RequestBody @Valid TransferInput transferInput) {
        return ResponseEntity.ok(apiService.transfer(transferInput));
    }

}
