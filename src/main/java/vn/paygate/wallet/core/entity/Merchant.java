package vn.paygate.wallet.core.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Merchant {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String merchantName;
    private String userId;
    private String checkoutDisplayName;
    private String address;
    private String checkoutDisplayAddress;
    private String phone;
    private String checkoutDisplayPhone;
    private String checkoutDisplayLogoUrl;
    private String merchantType;
    private String merchantTags;
    private String integrationMerchantCode;
    private String integrationSecureCode;
    private String integrationNotifyUrl;
    private String ỉnegrationSecurePassMassedpan;

}