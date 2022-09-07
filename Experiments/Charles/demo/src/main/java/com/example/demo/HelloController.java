package com.example.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.expression.spel.ast.NullLiteral;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

import static java.lang.Integer.parseInt;

/*
C - create - post
R - read - Get
U - update - put
D - Delete - delete
L - list - get
 */

// create a list that could be used for the test, may be necessary


@RestController
public class HelloController {

    ArrayList<String> Fox = new ArrayList<String>();

    @GetMapping("/")
    public String index() {
        return "Hello World!";
    }

    @GetMapping("/getHelp")
    public String getHelp() {
        return "Well good luck, I have no clue whats going on";
    }

    @DeleteMapping("/deleteHelp2")
    public String NoneHelp() {
        return "Good call, No clue why this exist";
    }

    @PostMapping("/PostHelp/{id}")
    public String PostHelp(@PathVariable String id) {

        return "Your Input: " + id;
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

    @GetMapping("/GetHelpParam")
    @ResponseBody
    public String wooloo(@RequestParam(required = false) String id) {
        return "Id: " +id;
    }


    //Fox Playground


    @PostMapping("/PostFox/{fox}")  //create
    public String PostFox(@RequestBody String fox) {

        if(Fox.add(fox)) {
            return "Added";
        }
        return "Failed";
    }

    @GetMapping("/getFoxSpec/{id}")  //Read
    public String getFoxSpec(@PathVariable String id) {
        int index = parseInt(id);
        String out = Fox.get(index);
        return out;
    }

    @PutMapping("/putFox/{id},{Fox}") //Update
    public String PutFox(@PathVariable String id, @PathVariable String Fox) {
        this.Fox.set(parseInt(id), Fox);
        return "Updated";

    }

    @DeleteMapping("/deleteFox") //delete
    @ResponseBody
    public String deleteFox(@RequestParam(required = false) String index) {
        Fox.remove(parseInt(index));
        return "Fox removed, You monster";
    }

    @GetMapping("/getFox")  //List
    public ArrayList<String> getFox() {
        return Fox;
    }






}

