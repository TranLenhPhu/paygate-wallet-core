package vn.paygate.wallet.core.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name = "PaymentMethod")
public class ConfigPaymentMethodChannel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String userId;
    private String merchantId;
    private String payment_method_code;
    private String payment_method_bank_code;
    private String method_type;
    private String method_branch;
    private String bank_code;
    private String provider_service_id;
    private String provider_service_code;
    private String provider_service_percentage;
    private String provider_fee_fix;
    private String provider_min_amount;
    private String provider_max_amount;
}
