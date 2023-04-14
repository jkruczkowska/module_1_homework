package com.epam.jmp.app;

import com.epam.jmp.bank.api.Bank;
import com.epam.jmp.cloud.bank.impl.BankImpl;
import com.epam.jmp.cloud.service.impl.ServiceImpl;
import com.epam.jmp.dto.BankCard;
import com.epam.jmp.dto.BankCardType;
import com.epam.jmp.dto.Subscription;
import com.epam.jmp.dto.User;
import com.epam.jmp.service.api.Service;

import java.time.LocalDate;
import java.util.Optional;

public class Main {
    public static void main(String[] args) {

        User user = new User("Ted", "Right", LocalDate.of(2002, 12, 12));
        User user1 = new User("Bob", "Left", LocalDate.of(1990, 11, 11));
        Bank bank = new BankImpl();
        Service service = new ServiceImpl();

        BankCard creditCard = bank.createBankCard(user, BankCardType.CREDIT);
        BankCard debitCard = bank.createBankCard(user1, BankCardType.DEBIT);
        service.subscribe(creditCard);
        service.subscribe(debitCard);

        Optional<Subscription> subscriptionByBankCardNumber = service.getSubscriptionByBankCardNumber(creditCard.getNumber());
        subscriptionByBankCardNumber.ifPresent(System.out::println);

        System.out.println(service.getAllUsers());
        System.out.println("Average users age is: " + service.getAverageUsersAge());

        for (User u : service.getAllUsers()) {
            if (Service.isPayableUser(u)) {
                System.out.println("User: " + u + " is payable");
            }
        }
    }
}