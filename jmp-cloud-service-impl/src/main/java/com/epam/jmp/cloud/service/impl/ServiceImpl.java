package com.epam.jmp.cloud.service.impl;

import com.epam.jmp.dto.BankCard;
import com.epam.jmp.dto.Subscription;
import com.epam.jmp.dto.User;
import com.epam.jmp.service.api.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.function.Predicate;
import java.util.stream.Collectors;

/**
 *
 * @author joanna
 */
public class ServiceImpl implements Service {

    private final Map<User, List<Subscription>> storage = new HashMap<>();

    @Override
    public void subscribe(BankCard bankCard) {

        User user = bankCard.getUser();
        String number = bankCard.getNumber();
        Subscription subscription = new Subscription(number, LocalDate.now());
        storage.computeIfAbsent(user, s -> new LinkedList<>()).add(subscription);
    }

    @Override
    public Optional<Subscription> getSubscriptionByBankCardNumber(String cardNumber) {

//        for (List<Subscription> userStorage : storage.values()) {
//            for (Subscription subscription : userStorage) {
//                return Optional.of(subscription);
//            }
//        }
//        return Optional.empty();

        Predicate<Subscription> subscriptionPredicate = s -> s.cardNumber().equals(cardNumber);
        return getAllSubscriptionsByCondition(subscriptionPredicate).stream().findFirst();
    }

    @Override
    public List<Subscription> getAllSubscriptionsByCondition(Predicate<Subscription> filter) {
        return storage.values()
                .stream()
                .flatMap(userStorage -> userStorage.stream())
                .filter(subscription -> filter.test(subscription))
                .collect(Collectors.toList());
    }

    @Override
    public List<User> getAllUsers() {
        return new ArrayList<>(storage.keySet());
    }
}
