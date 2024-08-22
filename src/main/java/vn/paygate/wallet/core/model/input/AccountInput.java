package vn.paygate.wallet.core.model.input;

import jakarta.persistence.Column;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.sql.Date;

@Setter
@Getter
public class AccountInput {

    private String email;
    private String mobile;
    private String fullName;

}
