package com.bankly.walletservice.Service;

import com.bankly.walletservice.Entity.Wallet;
import com.bankly.walletservice.Repository.WalletRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class WalletService {
    @Autowired
    private WalletRepository walletRepository;
    public void decreaseAmount(String rib,Double amount){
        System.out.println("Decrease");
        System.out.println("rib is "+rib+" amount is "+amount.toString());

        if(rib.isEmpty() || amount <0 ){
            throw new RuntimeException("rib or amount invalid");
        }

        Wallet wallet = this.walletRepository.findByRib(rib);
        System.out.println(wallet);
        if(wallet.getAmount()< amount){
            throw new RuntimeException("sold is not enough");
        }
        wallet.setAmount(wallet.getAmount() - amount);
        this.walletRepository.save(wallet);

    }
    public void increaseAmount(String rib,Double amount){
        System.out.println("Increase");
        System.out.println("rib is "+rib+" amount is "+amount.toString());

        if(rib.isEmpty() || amount <0 ){
            throw new RuntimeException("rib or amount invalid");
        }
        Wallet wallet = this.walletRepository.findByRib(rib);
        System.out.println(wallet);

        wallet.setAmount(wallet.getAmount() + amount);
        this.walletRepository.save(wallet);
    }
    public Wallet saveWallet(Wallet wallet){
        return walletRepository.save(wallet);
    }

}
