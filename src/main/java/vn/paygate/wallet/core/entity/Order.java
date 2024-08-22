package vn.paygate.wallet.core.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Table(name = "Orders")
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String merchantId;
    private String userId;
    private String orderAmount;
    private String orderCode;
    private String merchantCode;
    private String orderMerchantTime;
    private String orderPaymentMethodBankCode;
    private String orderPaymentMethodCode;
    private String successRedirectUrl;
    private String cancelRedirectUrl;
    private String successCallbackUrl;
    private String status;
    private String displayRequestFirstTime;
    private String orderCreateTime;
    private String customerIpFirstTime;
    private String displayRequestLastTime;
    private String SelectedPaymentMethodCode;
    private String SelectedPaymentMethodCodeTime;
    private String SelectebPaymentMethodBankCode;
    private String SelectebPaymentMethodBankCodeTime;
    private String SelectebConfigMerchantFeeId;
    private String cashinId;
    private String transactionId;
    private String currency;
    private String notifyUrl;
}
