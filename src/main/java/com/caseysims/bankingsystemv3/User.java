package com.caseysims.bankingsystemv3;

import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "users")
public class User
{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String nameFirst;
    private String nameSecond;
    private String pin;

    @OneToMany(mappedBy = "user")
    private List<Account> accounts;

    public User(String nameFirst,String nameSecond)
    {
        this.nameFirst = nameFirst;
        this.nameSecond = nameSecond;
    }

    public User()
    {

    }

    //GETTERS
    public String getNameFirst()
    {
        return nameFirst;
    }

    public String getNameSecond() {
        return nameSecond;
    }

    public String getPin() {
        return pin;
    }

    public long getId() {
        return id;
    }
    //GETTERS
    //SETTERS


    public void setPin(String pin) {
        this.pin = pin;
    }
}
