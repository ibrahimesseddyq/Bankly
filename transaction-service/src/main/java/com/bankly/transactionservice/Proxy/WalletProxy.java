package com.bankly.transactionservice.Proxy;

import com.bankly.transactionservice.VO.WalletRequest;
//import org.springframework.cloud.netflix.ribbon.RibbonClient;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(name="wallet-service")
//@RibbonClient(name="wallet-service")
public interface WalletProxy {
    @PostMapping(value ="/decrease",produces = "application/json")
    public ResponseEntity decreaseAmount(@RequestBody WalletRequest walletRequest);
    @PostMapping(value = "/increase",produces = "application/json")
    public ResponseEntity increaseAmount(@RequestBody WalletRequest walletRequest);

}
