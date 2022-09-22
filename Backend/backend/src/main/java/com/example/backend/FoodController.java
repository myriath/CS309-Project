package com.example.backend;

import org.springframework.web.bind.annotation.*;

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
    private int userIDSeed = 1;
    //Methods to delete
    @PostMapping("/pop")
    public void populate() {

        userInfo first = new userInfo();
        first.username = "Charles";
        first.Something = "Chernobyl";
        first.userID = userIDSeed;
        userIDSeed++;
        check.add(first);

        userInfo Second = new userInfo();
        Second.username = "steve";
        Second.Something = "minecraft";
        Second.userID = userIDSeed;
        userIDSeed++;
        check.add(Second);

        userInfo Third = new userInfo();
        Third.username = "Artyom";
        Third.Something = "Dark_Ones";
        Third.userID = userIDSeed;
        userIDSeed++;
        check.add(Second);
    }


    @GetMapping("/")
    public String test() {
        return"Hello world! test test";
    }

    @GetMapping("/userList")
    public String list(){

        return check.toString();
    }


    //Methods to keep?
    @PutMapping("/newUser/{name},{something}")
        public String newUser(@PathVariable String name, @PathVariable String something){
            userInfo newUser = new userInfo();
            newUser.username = name;
            newUser.Something = something;
            newUser.userID = userIDSeed;
            userIDSeed++;
            check.add(newUser);
        return "User Added";
    }



    @PutMapping("/NameChange/{old},{news}")  //could be used for the password
    public String UsernameChange(@PathVariable String old, @PathVariable String news){
        userInfo user = searchName(check, old);
        user.username = news;


        return "Username Changed to:" + user.username;
    }



    @GetMapping("/username/{something}")  //could be switched to use an email address to find the user
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