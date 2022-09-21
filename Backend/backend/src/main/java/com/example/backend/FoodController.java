package com.example.backend;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;

import static java.lang.Integer.parseInt;


class userInfo{
    public String username;
    public int userID;
    public String Something;
}


@RestController
class FoodController {

    ArrayList<userInfo> check = new ArrayList<userInfo>();

    //Methods to delete
    @PostMapping("/pop")
    public void populate() {

        userInfo first = new userInfo();
        first.username = "Charles";
        first.Something = "Chernobyl";
        first.userID = 1;

        check.add(first);

        userInfo Second = new userInfo();
        Second.username = "steve";
        Second.Something = "minecraft";
        Second.userID = 2;

        check.add(Second);

        userInfo Third = new userInfo();
        Third.username = "Artyom";
        Third.Something = "Dark_Ones";
        Third.userID = 3;

        check.add(Second);
    }


    @GetMapping("/")
    public String test() {
        return"Hello world! test test";
    }



    //Methods to keep
    @GetMapping("/username/{something}")
    public String Username(@PathVariable String something) {
        userInfo username_output = searchSomething(check, something);
        return username_output.username;
    }

    public userInfo searchSomething(ArrayList<userInfo> arr, String locate) {
        int n = arr.size();

        for(int i = 0; i < n; i++){
            if(arr.get(i).Something.compareTo(locate) == 0) {
                return arr.get(i);
            }
        }
        return null;
    }


    @GetMapping("/userID/{Username}")
    public int getID(@PathVariable String Username){
        userInfo id_output = searchName(check, Username);
        return id_output.userID;
    }

    public userInfo searchName(ArrayList<userInfo> arr, String locate) {
        int n = arr.size();

        for(int i = 0; i < n; i++){
            if(arr.get(i).username.compareTo(locate) == 0) {
                return arr.get(i);
            }
        }
        return null;
    }


}