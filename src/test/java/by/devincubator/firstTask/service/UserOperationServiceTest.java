package by.devincubator.firstTask.service;

import by.devincubator.firstTask.bean.Account;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

public class UserOperationServiceTest {

    @Test
    public void tstCountSum(){
        List<Account> accounts = new ArrayList<>();
        for (int i = 1; i < 5; i++){
            accounts.add(new Account(i * 1000));
        }

        UserOperationsService userOperationsService = (UserOperationsService) ServiceFactory
                .getService(ServiceType.USER_OPERATIONS);
        assertTrue("Sum counting is wrong",10000 == userOperationsService
                .countSum
        (accounts) );
    }

}
