package com.sylarlu.account.service.helper;

import com.github.structlog4j.ILogger;
import com.github.structlog4j.SLoggerFactory;
import com.sylarlu.account.config.AppConfig;
import com.sylarlu.account.model.Account;
import com.sylarlu.account.repo.AccountRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;


@RequiredArgsConstructor
@Component
public class ServiceHelper {
    static final ILogger logger = SLoggerFactory.getLogger(ServiceHelper.class);

    private final AccountRepo accountRepo;

    @Async(AppConfig.ASYNC_EXECUTOR_NAME)
    public void aSyncTest(String userId){
        try{
            logger.info("start aSyncTest userId:"+userId);
            Thread.sleep(5000);
            Account account = accountRepo.findAccountById(userId);
            logger.info(account.toString());
        } catch (InterruptedException e){
            logger.warn(e.toString());
        }

    }
}
