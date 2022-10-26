package com.spring.service.test.client.service;

import com.spring.service.test.client.data.Account;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.UUID;
import java.util.concurrent.ExecutionException;

@Component
public class ModellingClientRequest {
    private ApplicationContext context;
    private List<UserService> users = new ArrayList<>();
    private DataSource dataSource;

    private List<Account> accounts = new ArrayList<>();

    @Autowired
    public ModellingClientRequest(DataSource dataSource, ApplicationContext context,
                                  @Value("${webclient.count}") int countClient) throws SQLException, ExecutionException, InterruptedException {
        this.dataSource = dataSource;
        this.context = context;
        dataInit();
        serviceInit(countClient);
        startDoRequests();
    }

    private void serviceInit(int countClient) {
        for (int i = 0; i < accounts.size(); i++) {
            WebClient webClient = context.getBean(WebClient.class);
            UserService userService = new UserService(webClient);
            users.add(userService);
        }
    }

    private void dataInit() throws SQLException {
        Connection connection = dataSource.getConnection();
        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery("SELECT * FROM account");
        while (resultSet.next()) {
            Account account = new Account(UUID.fromString(resultSet.getString("id")),
                    resultSet.getLong("balance"));
            accounts.add(account);
        }
        statement.close();
        connection.close();
    }

    public void startDoRequests() {
        List<Thread> threadPool = new ArrayList<>();
        for (UserService user :
                users) {
            threadPool.add(new Thread(() -> {
                while (true) {
                    try {
                        Thread.sleep(new Random().nextInt(1000) + 100);
                        Account account = accounts.get(new Random().nextInt(users.size()));
                        if (new Random().nextBoolean()) {
                            System.out.println("Request Get Method " + "id = " + account.getId() + "; balance = " + user.getAccount(account.getId()));
                        } else {
                            int value = new Random().nextInt(Integer.MAX_VALUE);
                            user.postAccount(account.getId(),value);
                            System.out.println("Request Post Method with value = " + value + "; id = " + account.getId());
                        }
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                }
            }));
        }
        for (Thread thread : threadPool) {
            thread.start();
        }
    }
}



