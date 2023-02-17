package com.bankly.walletservice.Repository;

import com.bankly.walletservice.Entity.Wallet;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface WalletRepository extends MongoRepository<Wallet,Long> {

    Wallet findByRib(String rib);
}
