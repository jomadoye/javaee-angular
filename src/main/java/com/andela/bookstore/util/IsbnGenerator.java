package com.andela.bookstore.util;

import java.util.Random;

public class IsbnGenerator implements NumberGenerator {

    @Override
    public String generateNumber() {
        return "13" + Math.abs(new Random().nextInt());
    }
}
