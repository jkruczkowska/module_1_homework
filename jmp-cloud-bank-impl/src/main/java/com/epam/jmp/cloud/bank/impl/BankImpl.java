package com.epam.jmp.cloud.bank.impl;

import com.epam.jmp.bank.api.Bank;
import com.epam.jmp.dto.BankCard;
import com.epam.jmp.dto.BankCardType;
import com.epam.jmp.dto.CreditBankCard;
import com.epam.jmp.dto.DebitBankCard;
import com.epam.jmp.dto.User;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;
import java.util.function.BiFunction;

/**
 * @author joanna
 */
public class BankImpl implements Bank {
    private final Map<BankCardType, BiFunction<String, User, BankCard>> cardCreators;

    public BankImpl() {
        cardCreators = new HashMap<>();
        cardCreators.put(BankCardType.CREDIT, CreditBankCard::new);
        cardCreators.put(BankCardType.DEBIT, DebitBankCard::new);
    }

    private BankCard throwIfTypeIsUnknown(String number, User user) {
        throw new IllegalArgumentException("Unknown bank card type ");
    }

    @Override
    public BankCard createBankCard(User user, BankCardType bankCardType) {
        String number = UUID.randomUUID().toString();
        return cardCreators.getOrDefault(bankCardType, this::throwIfTypeIsUnknown).apply(number, user);
    }
}
