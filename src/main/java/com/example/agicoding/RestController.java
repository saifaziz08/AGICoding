package com.example.agicoding;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Objects;

@org.springframework.web.bind.annotation.RestController
public class RestController {
    @Autowired
    StringService stringService;

    @GetMapping("/hello")
    public ResponseEntity<String> healthCheck() {
        return ResponseEntity.ok("hello");
    }


    @GetMapping("/areSameCSV")
    public ResponseEntity sameCSV(
            @RequestParam("string1") String string1,
            @RequestParam("string2") String string2) {
        return stringService.areSameCSV(string1, string2);
    }


    @GetMapping("/csvUnion")
    public ResponseEntity sameUnion(
            @RequestParam("string1") String string1,
            @RequestParam("string2") String string2) {
        return stringService.csvUnion(string1, string2);
    }

    @GetMapping("/csvIntersection")
    public ResponseEntity sameIntersection(
            @RequestParam("string1") String string1,
            @RequestParam("string2") String string2) {
        return stringService.csvIntersection(string1, string2);
    }

    @GetMapping("/areAnagrams")
    public ResponseEntity areAnagrams(
            @RequestParam("string1") String string1,
            @RequestParam("string2") String string2) {
        return stringService.anagrams(string1, string2);
    }

    @GetMapping("/isPalindrome")
    public ResponseEntity isPalindrome(@RequestParam("string1") String string1) {
        return stringService.palindrome(string1);
    }

    @GetMapping("/spoonerism")
    public ResponseEntity spoonerism(
            @RequestParam("string1") String string1,
            @RequestParam("string2") String string2) {
        return stringService.spoonerism(string1, string2);
    }

    @GetMapping("/inflationary")
    public ResponseEntity inflationary(@RequestParam("string1") String string1) {
        return stringService.inflationary(string1);
    }

    @GetMapping("/fizzBuzz")
    public ResponseEntity fizzBuzz(
            @RequestParam("string1") String string1) {
        return stringService.fizzBuzz(string1);
    }

    @GetMapping("/combineResults")
    public ResponseEntity combineResults(@RequestBody String string1) {
        ObjectMapper mapper = new ObjectMapper();
        try {
            List<String> results = new ArrayList<>();
            List<Map<String, String>> operations = mapper.readValue(string1, new TypeReference<List<Map<String, String>>>(){});
            for(Map<String, String> operation : operations){
                if ("Are Same CSVs".equals(operation.get("operation"))){
                    results.add(Objects.requireNonNull(stringService.areSameCSV(operation.get("input1"), operation.get("input2")).getBody()).toString());
                }
                else if ("CSV Union".equals(operation.get("operation"))){
                    results.add(Objects.requireNonNull(stringService.csvUnion(operation.get("input1"), operation.get("input2")).getBody()).toString());
                }
                else if ("CSV Intersection".equals(operation.get("operation"))){
                    results.add(Objects.requireNonNull(stringService.csvIntersection(operation.get("input1"), operation.get("input2")).getBody()).toString());
                }
                else if ("Are anagrams".equals(operation.get("operation"))){
                    results.add(Objects.requireNonNull(stringService.anagrams(operation.get("input1"), operation.get("input2")).getBody()).toString());
                }
                else if ("Is palindrome".equals(operation.get("operation"))){
                    results.add(Objects.requireNonNull(stringService.palindrome(operation.get("input1")).getBody()).toString());
                }
                else if ("Spoonerism".equals(operation.get("operation"))){
                    results.add(Objects.requireNonNull(stringService.spoonerism(operation.get("input1"), operation.get("input2")).getBody()).toString());
                }
                else if ("Inflationary".equals(operation.get("operation"))){
                    results.add(Objects.requireNonNull(stringService.inflationary(operation.get("input1")).getBody()).toString());
                }
                else if ("Fizz Buzz".equals(operation.get("operation"))){
                    results.add(Objects.requireNonNull(stringService.inflationary(operation.get("input1")).getBody()).toString());
                }
            }

            return ResponseEntity.ok(results);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }

}
