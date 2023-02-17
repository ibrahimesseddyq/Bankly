package com.bankly.walletservice.VO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class WalletRequest {
    private Long id;
    private Double amount;
    private String rib;
}
