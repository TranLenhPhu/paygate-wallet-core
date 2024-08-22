package vn.paygate.wallet.core.global_variable;

import vn.paygate.wallet.core.entity.Account;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.math.BigDecimal;

@Component
public class GlobalAccounts {

    public static Account ADMIN;
    public static Account LUUKY;
    public static Account FEE;
    public static Account UYNHIEMCHI;

    @PostConstruct
    public void init() {
        ADMIN = new Account();
        ADMIN.setId(1L);
        ADMIN.setEndDate(null);
        ADMIN.setFullName("admin");
        ADMIN.setEmail("admin@gmail.com");
        ADMIN.setEndDateBalance(BigDecimal.valueOf(0));

        LUUKY = new Account();
        LUUKY.setId(2L);
        LUUKY.setEndDate(null);
        LUUKY.setFullName("luuky");
        LUUKY.setEmail("luuly@gmail.com");
        LUUKY.setEndDateBalance(BigDecimal.valueOf(0));

        FEE = new Account();
        FEE.setId(3L);
        FEE.setEndDate(null);
        FEE.setFullName("fee");
        FEE.setEmail("fee@gmail.com");
        FEE.setEndDateBalance(BigDecimal.valueOf(0));

        UYNHIEMCHI = new Account();
        UYNHIEMCHI.setId(4L);
        UYNHIEMCHI.setEndDate(null);
        UYNHIEMCHI.setFullName("uynhiemchi");
        UYNHIEMCHI.setEmail("uynhiemchi@gmail.com");
        UYNHIEMCHI.setEndDateBalance(BigDecimal.valueOf(0));
    }
}