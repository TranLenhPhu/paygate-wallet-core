package vn.paygate.wallet.core.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.sql.Date;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Cashin {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Long orderId;
    private Long cashinCode;
    private String transactionId;
    private BigDecimal settleAmount;
    private String settleTime;
    private Date createTime;
    private Long providerId;
    private String providerServiceId;
    private String providerRequestTime;
    private Date providerCallbackTime;
    private String providerCallbackAmount;
    private Long providerReceipt;
}
