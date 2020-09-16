package com.sylarlu.account.controller;

import com.sylarlu.account.dto.*;
import com.sylarlu.account.dto.*;
import com.sylarlu.account.service.AccountService;
import com.sylarlu.account.validation.PhoneNumber;
import com.github.structlog4j.ILogger;
import com.github.structlog4j.SLoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/account")
@Validated
public class AccountController {
    static final ILogger logger = SLoggerFactory.getLogger(AccountController.class);

    @Autowired
    private AccountService accountService;

    @GetMapping(path="/hello")
    public GenericAccountResponse Hello(@RequestParam String phoneNumber){
        AccountDto account =  accountService.getAccountByPhoneNumber(phoneNumber);
        return (new GenericAccountResponse(account));
    }

    /**
     * 测试feign调用
     * @param phoneNumber
     * @return
     */
    @GetMapping(path="/test")
    public String Test(@RequestParam("phoneNumber") String phoneNumber){
        logger.info("Hello world!!"+phoneNumber);
        return "Hello world!!"+phoneNumber;
    }

    @GetMapping(path="/list")
    public ListAccountResponse list(@RequestParam int page, @RequestParam int size){
        AccountList accountList =  accountService.list(page, size);
        return (new ListAccountResponse(accountList));
    }

    @PostMapping(path="/create")
    public GenericAccountResponse createAccount(@RequestBody @Valid CreateAccountRequest request){
        AccountDto accountDto = accountService.create(request.getName(), request.getEmail(), request.getPhoneNumber());
        GenericAccountResponse genericAccountResponse = new GenericAccountResponse(accountDto);
        return genericAccountResponse;
    }

    @GetMapping(path = "/get_account_by_phonenumber")
    public GenericAccountResponse getAccountByPhoneNumber(@RequestParam @PhoneNumber String phoneNumber){
        AccountDto accountDto = accountService.getAccountByPhoneNumber(phoneNumber);
        GenericAccountResponse genericAccountResponse = new GenericAccountResponse(accountDto);
        return genericAccountResponse;
    }
}
