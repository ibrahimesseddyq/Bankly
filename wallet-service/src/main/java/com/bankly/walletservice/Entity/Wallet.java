package com.bankly.walletservice.Entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document
@NoArgsConstructor
@Data
@AllArgsConstructor
public class Wallet {
    @Id
    private Long id;
    private String user_id;
    private Double amount;
    private Boolean isActive;
    private String rib;
}
