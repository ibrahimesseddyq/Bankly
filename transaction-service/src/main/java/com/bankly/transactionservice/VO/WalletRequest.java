package com.bankly.transactionservice.VO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class WalletRequest {
    private Double amount;
    private String rib;
}
