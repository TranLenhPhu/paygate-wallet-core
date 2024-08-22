package vn.paygate.wallet.core.controller;

import lombok.Getter;
import vn.paygate.wallet.core.entity.Account;
import vn.paygate.wallet.core.entity.ConfigMerchantFee;
import vn.paygate.wallet.core.entity.Order;
import vn.paygate.wallet.core.model.input.AccountInput;
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


import java.util.List;

@RestController
@RequestMapping("/api/admin")
public class AdminController {

    @Autowired
    private APIService apiService;

    @PostMapping("/newaccount")
    public ResponseEntity<Account> createAccount(@RequestBody AccountInput account) throws Exception {
        return ResponseEntity.ok(apiService.createAccount(account));
    }

    @PostMapping("/saveaccount")
    public ResponseEntity<Account> saveAccount(@RequestBody Account account) throws Exception {
        return ResponseEntity.ok(apiService.saveAccount(account));
    }

    @GetMapping("/getaccount/{idFirst}/{idLast}")
    public ResponseEntity<List<Account>> getAccount(@PathVariable Long idFirst, @PathVariable Long idLast) throws Exception {
        return ResponseEntity.ok(apiService.getAccount(idFirst, idLast));
    }

    @PostMapping("/deleteaccount/{id}")
    public void deleteAccount(@PathVariable Long id) throws Exception {
        apiService.deleteAccount(id);
    }
}
