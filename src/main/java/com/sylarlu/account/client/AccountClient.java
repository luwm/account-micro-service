package com.sylarlu.account.client;

import com.sylarlu.account.dto.*;
import com.sylarlu.account.validation.PhoneNumber;
import com.sylarlu.account.dto.CreateAccountRequest;
import com.sylarlu.account.dto.GenericAccountResponse;
import com.sylarlu.account.dto.ListAccountResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import javax.validation.Valid;

@FeignClient(name = "account-service", path = "/account", url = "${services.account-service-endpoint}")
@Validated
public interface AccountClient {
    @GetMapping(path="/hello")
    public GenericAccountResponse Hello(@RequestParam("phoneNumber") String phoneNumber);

    @GetMapping(path="/test")
    public String Test(@RequestParam("phoneNumber") String phoneNumber);

    @GetMapping(path="/list")
    public ListAccountResponse list(@RequestParam("page") int page, @RequestParam("size") int size);

    @PostMapping(path="/create")
    public GenericAccountResponse createAccount(@RequestBody @Valid CreateAccountRequest request);

    @GetMapping(path = "/get_account_by_phonenumber")
    public GenericAccountResponse getAccountByPhoneNumber(@RequestParam("phoneNumber") @PhoneNumber String phoneNumber);
}
