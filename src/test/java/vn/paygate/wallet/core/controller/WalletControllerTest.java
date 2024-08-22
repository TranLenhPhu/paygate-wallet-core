package vn.paygate.wallet.core.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import vn.paygate.wallet.core.entity.Account;
import vn.paygate.wallet.core.entity.FeeConfig;
import vn.paygate.wallet.core.global_variable.GlobalAccounts;
import vn.paygate.wallet.core.model.output.DepositOutput;
import vn.paygate.wallet.core.service.AccountService;
import vn.paygate.wallet.core.service.FeeConfigService;
import vn.paygate.wallet.core.model.input.DepositInput;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.ResponseEntity;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.math.BigDecimal;
import java.nio.file.Files;
import java.nio.file.Path;
import java.sql.Date;

@SpringBootTest
public class WalletControllerTest {

    @Autowired
    private WalletController walletController;

    @Autowired
    private AccountService accountService;

    @Autowired
    private FeeConfigService feeConfigService;

    private Account admin;
    private Account luuky;
    private Account fee;
    private Account receiver;
    private Account sender;
    private Account uynhiemchi;

    private FeeConfig feeConfigDefault;

    @BeforeEach
    public void setUp() {
        fee = GlobalAccounts.FEE;
        admin = GlobalAccounts.ADMIN;
        luuky = GlobalAccounts.LUUKY;
        uynhiemchi = GlobalAccounts.UYNHIEMCHI;

        receiver = new Account();
        receiver.setId(101L);
        receiver.setFullName("Receiver");
        receiver.setMobile("101");
        receiver.setEmail("receiver@gmail.com");
        receiver.setEndDate(Date.valueOf("2021-12-31"));
        receiver.setEndDateBalance(BigDecimal.valueOf(0));

        sender = new Account();
        sender.setId(102L);
        sender.setFullName("Sender");
        sender.setMobile("102");
        sender.setEmail("sender@gmail.com");
        sender.setEndDate(Date.valueOf("2021-12-31"));
        sender.setEndDateBalance(BigDecimal.valueOf(0));

        if (accountService.findById(admin.getId()) == null) {
            admin = accountService.save(admin);
        }
        if (accountService.findById(luuky.getId()) == null) {
            luuky = accountService.save(luuky);
        }
        if (accountService.findById(fee.getId()) == null) {
            fee = accountService.save(fee);
        }
        if (accountService.findById(uynhiemchi.getId()) == null) {
            uynhiemchi = accountService.save(uynhiemchi);
        }
        if (accountService.findById(receiver.getId()) == null) {
            receiver = accountService.save(receiver);
        }
        if (accountService.findById(sender.getId()) == null) {
            sender = accountService.save(sender);
        }

        feeConfigDefault = new FeeConfig();
        feeConfigDefault.setAccountId(null);
        feeConfigDefault.setMethodId(1L);
        feeConfigDefault.setTransactionType(1L);
        feeConfigDefault.setReceiverFeePercentage(BigDecimal.valueOf(0.01));
        feeConfigDefault.setReceiverFeeFixAmount(BigDecimal.valueOf(1000));
        feeConfigDefault.setSenderFeePercentage(BigDecimal.valueOf(0.01));
        feeConfigDefault.setSenderFeeFixAmount(BigDecimal.valueOf(1000));

        if (feeConfigService.findByAccountIdAndTransactionType(feeConfigDefault.getAccountId(), feeConfigDefault.getTransactionType()) == null) {
            feeConfigDefault = feeConfigService.save(feeConfigDefault);
        }
    }

    @Test
    @Order(1)
    public void DepositCase01() throws Exception {
        DepositInput depositInput = new DepositInput();
        depositInput.setReceiver(101L);
        depositInput.setAmount(BigDecimal.valueOf(100000));
//        depositInput.setCashin_receipt("TestCase01");
        depositInput.setVer02_cashin_id("1");
        depositInput.setVer02_cashin_performed_time("Hom nay");

        ResponseEntity<DepositOutput> response = walletController.deposit(depositInput);

        Path path = Path.of("src/test/resources/deposit/case01");
        if (!Files.exists(path)) {
            Files.createDirectories(path);
        }

        ObjectMapper objectMapper = new ObjectMapper();
        File jsonFile = new File(path.toString(), "output.json");
        objectMapper.writerWithDefaultPrettyPrinter().writeValue(jsonFile, response.getBody());
    }

    @Test
    @Order(2)
    public void DepositCase02() throws Exception {
        FeeConfig feeConfigAccount = new FeeConfig();
        feeConfigAccount.setAccountId(receiver.getId());
        feeConfigAccount.setMethodId(1L);
        feeConfigAccount.setTransactionType(1L);
        feeConfigAccount.setReceiverFeePercentage(BigDecimal.valueOf(0.5));
        feeConfigAccount.setReceiverFeeFixAmount(BigDecimal.valueOf(1000));
        feeConfigAccount.setSenderFeePercentage(BigDecimal.valueOf(0.5));
        feeConfigAccount.setSenderFeeFixAmount(BigDecimal.valueOf(1000));
        feeConfigAccount = feeConfigService.save(feeConfigAccount);

        DepositInput depositInput = new DepositInput();
        depositInput.setReceiver(101L);
        depositInput.setAmount(BigDecimal.valueOf(100000));
//        depositInput.setCashin_receipt("TestCase02");
        depositInput.setVer02_cashin_id("2");
        depositInput.setVer02_cashin_performed_time("Hom nay");

        ResponseEntity<DepositOutput> response = walletController.deposit(depositInput);

        Path path = Path.of("src/test/resources/deposit/case02");
        if (!Files.exists(path)) {
            Files.createDirectories(path);
        }

        ObjectMapper objectMapper = new ObjectMapper();
        File jsonFile = new File(path.toString(), "output.json");
        objectMapper.writerWithDefaultPrettyPrinter().writeValue(jsonFile, response.getBody());
    }

    @Test
    @Order(3)
    public void DepositCase03() throws Exception {
        DepositInput depositInput = new DepositInput();
        depositInput.setReceiver(103L);
        depositInput.setAmount(BigDecimal.valueOf(100000));
//        depositInput.setCashin_receipt("TestCase03");
        depositInput.setVer02_cashin_id("3");
        depositInput.setVer02_cashin_performed_time("Hom nay");

        ResponseEntity<DepositOutput> response = walletController.deposit(depositInput);

        Path path = Path.of("src/test/resources/deposit/case03");
        if (!Files.exists(path)) {
            Files.createDirectories(path);
        }

        ObjectMapper objectMapper = new ObjectMapper();
        File jsonFile = new File(path.toString(), "output.json");
        objectMapper.writerWithDefaultPrettyPrinter().writeValue(jsonFile, response.getBody());
    }

    @Test
    @Order(4)
    public void DepositCase04() throws Exception {
        DepositInput depositInput = null;

        ResponseEntity<DepositOutput> response = walletController.deposit(depositInput);

        Path path = Path.of("src/test/resources/deposit/case04");
        if (!Files.exists(path)) {
            Files.createDirectories(path);
        }

        ObjectMapper objectMapper = new ObjectMapper();
        File jsonFile = new File(path.toString(), "output.json");
        objectMapper.writerWithDefaultPrettyPrinter().writeValue(jsonFile, response.getBody());
    }

    @Test
    @Order(5)
    public void GetCurrentBalanceTest() throws Exception {
        Path path = Path.of("src/test/resources/get_current_balance");
        if (!Files.exists(path)) {
            Files.createDirectories(path);
        }

        File file = new File(path.toFile(), "balance.txt");
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(file))) {
            writer.write(accountService.getCurrentBalance(receiver).toString());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
