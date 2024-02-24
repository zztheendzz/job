package com.example.demo.config;

import org.springframework.security.crypto.password.PasswordEncoder;

// turn off passwordEncoder
public class PlainTextPassEncoder implements PasswordEncoder {
	 
	    @Override
	    public String encode(CharSequence rawPassword) {
	        return rawPassword.toString();
	    }
	 
	    @Override
	    public boolean matches(CharSequence rawPassword, String encodedPassword) {
	        return rawPassword.toString().equals(encodedPassword);
	    }
	 
	    public static PasswordEncoder getInstance() {
	        return INSTANCE;
	    }
	 
	    private static final PasswordEncoder INSTANCE = new PlainTextPassEncoder();
	 

	}


