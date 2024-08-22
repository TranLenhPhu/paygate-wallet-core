package vn.paygate.wallet.core.entity;

import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.sql.Date;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Cashout {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private BigDecimal amount;
    private Long type;
    private Long receiver;
    private Long sender;
    private Date performedTime;
    private Long ver02CashoutId;
    private Date ver02CashoutPerformedTime;
    @Column(name = "bank_id")
    private Long ver02BankID;
    @Column(name = "bank_code")
    private String bankCode;
    @Column(name = "bank_number")
    private String bankNumber;
    @Column(name = "bank_account_name")
    private String bankAccountName;

}
