package com.example.atishay.event_lister;

import org.junit.Test;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static org.junit.Assert.*;

public class LoginPageChapterTest {
    String email="500055748@gmail.com";
    String password="123456";

    @Test
    public void mylogin() {
        String emailPattern = "[a-zA-Z0-9._-]+@stu.upes.ac.in";
        Pattern p=Pattern.compile(emailPattern);
        Matcher m=p.matcher(email);
        boolean b=m.matches();
        assertFalse(b);     //produces false as an output which passes the test case
    }
}