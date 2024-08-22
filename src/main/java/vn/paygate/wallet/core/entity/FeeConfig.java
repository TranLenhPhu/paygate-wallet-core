package vn.paygate.wallet.core.entity;

import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
@Setter
public class FeeConfig {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Long accountId;
    private Long methodId;
    private Long transactionType;
    private BigDecimal receiverFeePercentage;
    private BigDecimal receiverFeeFixAmount;
    private BigDecimal senderFeePercentage;
    private BigDecimal senderFeeFixAmount;

}
