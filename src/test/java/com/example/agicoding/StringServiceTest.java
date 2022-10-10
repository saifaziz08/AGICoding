package com.example.agicoding;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.*;

class StringServiceTest {
    StringService stringService = new StringService();

    @Test
    void areSameCSV() {
        ResponseEntity result = stringService.areSameCSV("a,b,c","c,b,a");
        assertTrue((Boolean) result.getBody());
    }

    @Test
    void csvUnion() {
        ResponseEntity result = stringService.csvUnion("a,b,c","a,b,d");
        assertEquals("a,b,c,d", result.getBody());
    }

    @Test
    void csvIntersection() {
        ResponseEntity result = stringService.csvIntersection("a,b,c","a,b,d");
        assertEquals("a,b", result.getBody());
    }

    @Test
    void anagrams() {
        ResponseEntity result = stringService.anagrams("A decimal point","I'm a dot in place");
        assertTrue((Boolean) result.getBody());
    }

    @Test
    void palindrome() {
        ResponseEntity result = stringService.palindrome("A man, a plan, a canal, Panama!");
        assertTrue((Boolean) result.getBody());
    }

    @Test
    void spoonerism() {
        ResponseEntity result = stringService.spoonerism("pig", "sick");
        assertEquals("sig pick", result.getBody());
    }

    @Test
    void inflationary() {
    }

    @Test
    void fizzBuzz() {
        ResponseEntity result = stringService.fizzBuzz("16");
        assertEquals("1 2 fizz 4 buzz fizz 7 8 fizz buzz 11 fizz 13 14 fizzbuzz 16", result.getBody());
    }
}