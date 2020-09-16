package com.sylarlu.account.service;

import com.sylarlu.account.client.AccountClient;
import com.sylarlu.account.dto.AccountDto;
import com.sylarlu.account.dto.AccountList;
import com.sylarlu.account.error.ServiceException;
import com.sylarlu.account.model.Account;
import com.sylarlu.account.repo.AccountRepo;
import com.github.structlog4j.ILogger;
import com.github.structlog4j.SLoggerFactory;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.time.Instant;
import java.util.List;

import static java.util.stream.Collectors.toList;

@Service
@RequiredArgsConstructor
public class AccountService {
    static ILogger logger = SLoggerFactory.getLogger(AccountService.class);

    public final AccountRepo accountRepo;

    private final ModelMapper modelMapper;

    private final AccountClient accountClient;

    public AccountDto getAccountByPhoneNumber(String phoneNumber){
        Account account = accountRepo.findAccountByPhoneNumber(phoneNumber);
        if (account == null) {
            logger.info(String.format("User with phone_number %s not found", phoneNumber));
            throw new ServiceException(phoneNumber);
        }
        accountClient.Test(phoneNumber);
        return this.convertToDto(account);
    }

    public AccountList list(int page, int limit){
        int offset = (page-1) * limit;
        if(limit <=0){
            limit = 10;
        }
        Pageable pageRequest = PageRequest.of(offset, limit);
        Page<Account> accountPage = accountRepo.findAll(pageRequest);
        List<AccountDto> accountDtoList = accountPage.getContent().stream().map(account -> convertToDto(account)).collect(toList());
        return AccountList.builder()
                .limit(limit)
                .offset(page)
                .items(accountDtoList)
                .build();
    }

    private AccountDto convertToDto(Account account) {
        return modelMapper.map(account, AccountDto.class);
    }

    public AccountDto create(String name, String email, String phoneNumber){
        if(StringUtils.hasText(email)){
            Account foundAccount = accountRepo.findAccountByEmail(email);
            if (foundAccount != null){
                throw new ServiceException("A user with that email already exists. Try a password reset");
            }
        }
        if(StringUtils.hasText(phoneNumber)){
            Account foundAccount = accountRepo.findAccountByPhoneNumber(phoneNumber);
            if (foundAccount != null){
                throw new ServiceException("A user with that phoneNumber already exists. Try a password reset");
            }
        }

        if(name == null){
            name = "";
        }
        if(email == null){
            email = "";
        }
        if(phoneNumber== null){
            phoneNumber="";
        }
        Account account = Account.builder()
                .name(name)
                .email(email)
                .phoneNumber(phoneNumber)
                .photoUrl("")
                .memberSince(Instant.now())
                .build();

        try{
            accountRepo.save(account);
        }catch(Exception ex){
            String errMsg = "Could not create user account";
            throw new ServiceException(errMsg, ex);
        }
        AccountDto accountDto = this.convertToDto(account);
        return accountDto;
    }
}
