package com.revature;

import com.revature.models.UserStatus;

public class Main {
    public static void main(String[] args) {
        UserStatus userStatus = UserStatus.Employee;

        System.out.println(userStatus.toString());
    }
}