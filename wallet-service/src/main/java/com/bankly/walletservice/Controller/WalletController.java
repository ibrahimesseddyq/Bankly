package com.bankly.walletservice.Controller;


import com.bankly.walletservice.Entity.Wallet;
import com.bankly.walletservice.Service.WalletService;
import com.bankly.walletservice.VO.WalletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class WalletController {
    @Autowired
    private WalletService walletService;
    @PostMapping("/decrease")
    public ResponseEntity decreaseAmount(@RequestBody WalletRequest walletRequest){
        System.out.println(walletRequest);
        try {
            walletService.decreaseAmount(walletRequest.getRib(),walletRequest.getAmount());
            return ResponseEntity.ok().build();
        }catch (Exception e){
            return ResponseEntity.badRequest().build();
        }

    }
    @PostMapping("/increase")
    public ResponseEntity increaseAmount(@RequestBody WalletRequest walletRequest){
        System.out.println(walletRequest);
        try {
            walletService.increaseAmount(walletRequest.getRib(),walletRequest.getAmount());
            return ResponseEntity.ok().build();
        }catch (Exception e){
            return ResponseEntity.badRequest().build();
        }

    }

    @PostMapping("/save")
    public Wallet save(@RequestBody  Wallet wallet){
        return walletService.saveWallet(wallet);
    }
}
