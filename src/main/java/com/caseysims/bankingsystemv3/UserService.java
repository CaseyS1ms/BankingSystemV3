package com.caseysims.bankingsystemv3;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Objects;
import java.util.Optional;
import java.util.Random;
@Service
public class UserService
{
    @Autowired
    UserRepository userRepository;
    @Autowired
    BCryptPasswordEncoder bCryptPasswordEncoder;
    @Autowired
    private PasswordEncoder passwordEncoder;

    String createUser(String firstName,String secondName)
    {
        Random random = new Random();
        User user = new User(firstName,secondName);
        int pin = 100000 + random.nextInt(900000);
        String pinHashed = passwordEncoder.encode(String.valueOf(pin));
        user.setPin(pinHashed);
        userRepository.save(user);
        return String.valueOf(pin);
    }


    Optional <User> getUser(long id)
    {
        return userRepository.findById(id);
    }

    Boolean loginUser(long user_id, int pin)
    {
        Optional<User> temp = getUser(user_id);
        if(temp.isEmpty())
        {
            throw new AccountNotFoundException("Account with ID: " + user_id + " cannot be found");
        }
        User user = temp.get();


        return passwordEncoder.matches(String.valueOf(pin), user.getPin());
    }

}
