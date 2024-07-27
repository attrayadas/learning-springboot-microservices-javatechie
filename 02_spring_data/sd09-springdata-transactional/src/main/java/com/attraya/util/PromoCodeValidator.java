package com.attraya.util;

import java.util.List;

public class PromoCodeValidator {

    public static void validatePromoCode(String promocode){
        List<String> validPromoCodes = List.of("PRACTO10", "PRACTO20", "PRACTO30", "PRACTO40", "PRACTO50");
        if (!validPromoCodes.contains(promocode)){
            throw new RuntimeException("Invalid Promo code! Please enter a valid promo code");
        }
    }
}
