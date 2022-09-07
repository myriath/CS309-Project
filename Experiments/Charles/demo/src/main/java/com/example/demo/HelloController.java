package com.example.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.expression.spel.ast.NullLiteral;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import static java.lang.Integer.parseInt;


@RestController
public class HelloController {
    int hellp = 99;

    @GetMapping("/")
    public String index() {
        return "Hello World!";
    }


    @GetMapping("/getHelp")
    public String getHelp() {
        return "Well good luck, I have no clue whats going on";
    }

    @DeleteMapping("/deleteHelp2")
    public int NoneHelp() {
        return 0;
    }

    @PostMapping("/PostHelp/{id}")
    public String PostHelp(@PathVariable String id) {

        return id;
    }

    @PostMapping("/PostHelp")
    public String PostHelp2(@RequestBody String y) {

        return y;
    }

    @PutMapping("/putHelp/{id}")
    public String PutHelp(@PathVariable String id) {
        String out = id + " :Nice";

        return out;
    }





/*
    @RequestMapping(value = "/deleteHelp", method = {RequestMethod.GET, RequestMethod.DELETE})
    public void noHelp() {
        hellp = 0;
    }

    @RequestMapping(value = "/post/{id}", method = {RequestMethod.GET, RequestMethod.POST})
    public void postHelp(@PathVariable String id) {
        hellp = parseInt(id);
    }

    @RequestMapping(value = "/putHelp", method = {RequestMethod.GET, RequestMethod.PUT})
    public void putHelp() {
        hellp = 9;
    }

*/

}

