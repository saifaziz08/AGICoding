package com.example.agicoding;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service
public class StringService {

    @Autowired
    NumberConversionService numberConversionService;

    private String preconditions(String string1) {
        return (string1 == null || "".equals(string1)) ?
                "Please provide two non-empty, non-null strings": null;
    }

    private String preconditions(String string1, String string2) {
        return (string1 == null || "".equals(string1) || string2 == null || "".equals(string2)) ?
                "Please provide two non-empty, non-null strings": null;
    }

    public ResponseEntity areSameCSV(String string1, String string2) {
        String conditionCheck = preconditions(string1, string2);
        if (conditionCheck != null)
            return ResponseEntity
                    .status(HttpStatus.PARTIAL_CONTENT)
                    .body(conditionCheck);

        boolean retval = true;
        String modifiedString1 = string1.replace(" ", "");
        String modifiedString2 = string2.replace(" ", "");
        String[] str1Split = modifiedString1.split(",");
        String[] str2Split = modifiedString2.split(",");

        Map<String, Long> frequenciesMap1 = Stream.of(str1Split).collect(Collectors.groupingBy(Function.identity(), Collectors.counting()));
        Map<String, Long> frequenciesMap2 = Stream.of(str2Split).collect(Collectors.groupingBy(Function.identity(), Collectors.counting()));

        for (Map.Entry<String, Long> entry : frequenciesMap1.entrySet()) {
            if (!frequenciesMap2.containsKey(entry.getKey()) ||
                    !Objects.equals(frequenciesMap2.get(entry.getKey()), entry.getValue()))
                retval = false;
        }

        if (frequenciesMap2.size() != frequenciesMap1.size())
            retval = false;

        return ResponseEntity.ok(retval);
    }

    public ResponseEntity csvUnion(String string1, String string2) {
        String conditionCheck = preconditions(string1, string2);
        if (conditionCheck != null)
            return ResponseEntity
                    .status(HttpStatus.PARTIAL_CONTENT)
                    .body(conditionCheck);

        String modifiedString1 = string1.replace(" ", "");
        String modifiedString2 = string2.replace(" ", "");
        String[] str1Split = modifiedString1.split(",");
        String[] str2Split = modifiedString2.split(",");
        Set<String> distinctSet = new TreeSet<>(Arrays.asList(str1Split));
        distinctSet.addAll(Arrays.asList(str2Split));
        StringBuilder result = new StringBuilder();
        int i = 0;
        for (String s : distinctSet) {
            if (i+1 == distinctSet.size())
                result.append(s);
            else
                result.append(s).append(",");
            i++;
        }
        return ResponseEntity.ok(result.toString());
    }

    public ResponseEntity csvIntersection(String string1, String string2) {
        String conditionCheck = preconditions(string1, string2);
        if (conditionCheck != null)
            return ResponseEntity
                    .status(HttpStatus.PARTIAL_CONTENT)
                    .body(conditionCheck);
        String modifiedString1 = string1.replace(" ", "");
        String modifiedString2 = string2.replace(" ", "");
        String[] str1Split = modifiedString1.split(",");
        String[] str2Split = modifiedString2.split(",");

        Set<String> firstSet = new TreeSet<>(Arrays.asList(str1Split));
        Set<String> intersectionSet = new TreeSet<>();
        for (String s : str2Split) {
            if (firstSet.contains(s))
                intersectionSet.add(s);
        }

        StringBuilder result = new StringBuilder();
        int i = 0;
        for (String s : intersectionSet) {
            if (i+1 == intersectionSet.size())
                result.append(s);
            else
                result.append(s).append(",");
            i++;
        }
        return ResponseEntity.ok(result.toString());
    }

    public ResponseEntity anagrams(String string1, String string2) {
        String conditionCheck = preconditions(string1, string2);
        if (conditionCheck != null)
            return ResponseEntity
                    .status(HttpStatus.PARTIAL_CONTENT)
                    .body(conditionCheck);
        StringBuilder res = new StringBuilder();
        StringBuilder res2 = new StringBuilder();
        for (char c : string1.toCharArray()) {
            if (Character.isLetter(c) || Character.isDigit(c))
                res.append(Character.toLowerCase(c));
        }

        for (char c : string2.toCharArray()) {
            if (Character.isLetter(c) || Character.isDigit(c))
                res2.append(Character.toLowerCase(c));
        }

        String[] c1 = res.toString().split("");
        String[] c2 = res2.toString().split("");

        Map<String, Long> frequenciesMap1 = Stream.of(c1).collect(Collectors.groupingBy(Function.identity(), Collectors.counting()));
        Map<String, Long> frequenciesMap2 = Stream.of(c2).collect(Collectors.groupingBy(Function.identity(), Collectors.counting()));

        boolean retval = true;
        for (Map.Entry<String, Long> entry : frequenciesMap1.entrySet()) {
            if (!frequenciesMap2.containsKey(entry.getKey()) ||
                    !Objects.equals(frequenciesMap2.get(entry.getKey()), entry.getValue()))
                retval = false;
        }

        if (frequenciesMap1.size() != frequenciesMap2.size())
            retval = false;

        return ResponseEntity.ok(retval);
    }

    public ResponseEntity palindrome(String string1) {
        String conditionCheck = preconditions(string1);
        if (conditionCheck != null)
            return ResponseEntity
                    .status(HttpStatus.PARTIAL_CONTENT)
                    .body(conditionCheck);

        StringBuilder res = new StringBuilder();
        for (char c : string1.toCharArray()) {
            if (Character.isLetter(c) || Character.isDigit(c))
                res.append(Character.toLowerCase(c));
        }
        int i = 0, j = res.length() - 1;
        String modifiedString = res.toString();
        boolean result = true;
        while (i < j) {
            if (modifiedString.charAt(i) != modifiedString.charAt(j)) {
                result = false;
                break;
            }
            i++;
            j--;
        }

        return ResponseEntity.ok(result);
    }

    public ResponseEntity spoonerism(String string1, String string2) {
        String conditionCheck = preconditions(string1, string2);
        if (conditionCheck != null) {
            return ResponseEntity
                    .status(HttpStatus.PARTIAL_CONTENT)
                    .body(conditionCheck);
        }
        String body;
        if (string1.length() == 1 && string2.length() == 1)
            body = string2 + " " + string1;
        else if (string1.length() == 1)
            body = string2.charAt(0) + " " + string1 + string2.substring(1);
        else if (string2.length() == 1)
            body = string2 + string1.substring(1) + " " + string1.charAt(0);
        else
            body = string2.charAt(0) + string1.substring(1) + " " + string1.charAt(0) + string2.substring(1);

        return ResponseEntity.ok(body);
    }

    public ResponseEntity inflationary(String string1) {
        String conditionCheck = preconditions(string1);
        if (conditionCheck != null)
            return ResponseEntity
                    .status(HttpStatus.PARTIAL_CONTENT)
                    .body(conditionCheck);

        String[] s1 = string1.split(" ");
        StringBuilder res = new StringBuilder();
        for(String s : s1) {
            for(int i = 0 ; i < s.length() ; i++) {
                for (int j = i+1; j <= s.length(); j++) {
                    String number = numberConversionService.wordToNumberAndIncrement(s.substring(i, j));
                    if (number != null) {
                        s = (j == s.length()) ?
                                s.substring(0, i) + number :
                                s.substring(0, i) + number + s.substring(j);
                        i = j;
                    }
                }
            }
            res.append(s).append(" ");
        }

        return ResponseEntity.ok(res.toString());
    }

    public ResponseEntity fizzBuzz(String string1) {
        String conditionCheck = preconditions(string1);
        if (conditionCheck != null)
            return ResponseEntity
                    .status(HttpStatus.PARTIAL_CONTENT)
                    .body(conditionCheck);
        try {
            StringBuilder res = new StringBuilder() ;
            int parsedInt = Integer.parseInt(string1);
            for (int i = 1 ; i <= parsedInt ; i++){
                if (i % 3 == 0 && i % 5 == 0)
                    res.append("fizzbuzz").append(" ");
                else if(i % 3 == 0)
                    res.append("fizz").append(" ");
                else if (i % 5 == 0)
                    res.append("buzz").append(" ");
                else
                    res.append(i).append(" ");
            }
            return ResponseEntity.ok(res.toString().trim());
        }

        catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity
                    .status(HttpStatus.PARTIAL_CONTENT)
                    .body("Could not convert : " + string1);
        }
    }
}
