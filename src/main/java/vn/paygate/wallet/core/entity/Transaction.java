package vn.paygate.wallet.core.entity;

import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.sql.Date;


@Entity
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
@Setter
public class Transaction {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private BigDecimal amount;
    private Long sender;
    private Long receiver;
    private Long orderId;
    private Long CashinId;
    private Long type;
    private String paymemtMethodCode;
    private String paymentMethodBankCode;
    private Long configMerchantFeeId;
    private String status;
    private Date createTime;
    private Date settleTime;

}
