package com.caseysims.bankingsystemv3;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController
{

    @Autowired
    UserService userService;
    @Autowired
    UserRepository userRepository;

    @PostMapping("createUser")
    public String createUser(@RequestBody User user)
    {
        return userService.createUser(user.getNameFirst(),user.getNameSecond());
    }

    @PostMapping("login")
    public String login(@RequestParam long user_id,@RequestParam int pin)
    {
        if(userService.loginUser(user_id,pin))
        {
            return "Successful Login";
        }
        else
        {
            return "Unsuccessful Login";
        }
    }


}

