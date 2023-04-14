package com.epam.jmp.dto;

import java.time.LocalDate;

/**
 *
 * @author joanna
 */
public record Subscription(String cardNumber, LocalDate startDate) {
}
