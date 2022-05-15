package com;

import com.domains.CreateAccountDomain;
import com.domains.ForgotPasswordDomain;
import com.domains.LoginDomain;
import com.io.Input;

public class Main {
    public static void main(String[] args) {
        String[] options = {
                "1 - Login",
                "2 - Create Account",
                "3 - Forgot Password",
                "4 - Exit"
        };

        int option;

        while (true) {
            printOptions(options);
            option = Input.readInt();

            switch (option) {
                case 1 -> LoginDomain.load();
                case 2 -> CreateAccountDomain.load();
                case 3 -> ForgotPasswordDomain.load();
                case 4 -> System.exit(0);
            }
        }
    }

    private static void printOptions(String[] options) {
        for (String option : options) {
            System.out.println(option);
        }
        System.out.println("What would you like to do?");
    }
}
