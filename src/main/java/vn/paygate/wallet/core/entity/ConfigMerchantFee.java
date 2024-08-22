package vn.paygate.wallet.core.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name = "MerchantFee")
public class ConfigMerchantFee {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Boolean is_default;
    private String merchantId;
    private String user_id;
    private String payment_method_id;
    private String payment_method_code;
    private String payment_method_bank_id;
    private String payment_method_bank_code;
    private String sender_fee_percentage;
    private String sender_fee_fix;
    private String receiver_fee_fix;
    private String min_amount;
    private String max_amount;
}
