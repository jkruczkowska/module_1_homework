package com.epam.jmp.service.api;

import com.epam.jmp.dto.BankCard;
import com.epam.jmp.dto.Subscription;
import com.epam.jmp.dto.User;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.function.Predicate;

public interface Service {

    static boolean isPayableUser(User user) {
        return user.birthday().isBefore(LocalDate.now().minusYears(18));
    }

    default double getAverageUsersAge() {
        return getAllUsers()
                .stream()
                .mapToDouble(user -> LocalDate.now().getYear() - user.birthday().getYear())
                .average()
                .orElse(0);
    }

    void subscribe(BankCard bankCard);

    Optional<Subscription> getSubscriptionByBankCardNumber(String cardNumber);

    List<Subscription> getAllSubscriptionsByCondition(Predicate<Subscription> filter);

    List<User> getAllUsers();
}
