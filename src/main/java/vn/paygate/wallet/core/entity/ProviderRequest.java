package vn.paygate.wallet.core.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class ProviderRequest {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String requestType;
    private String providerId;
    private String providerServiceId;
    private String cashinId;
    private String requestTime;
    private String requestContext;
    private String responseContent;
    private String responseTime;
    private String status;
    private String settleAmount;
    private String bankResponseAmount;
}
