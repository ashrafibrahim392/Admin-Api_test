package com.santechture.api.util;

import com.santechture.api.dto.Token;

import java.util.HashMap;
import java.util.Map;

public class InMemoryForToken {

    private static Map<Integer, Token> userTokens  = new HashMap<>();


    public static void addNewToken (Token token){
        userTokens.put(token.getAdminId(),token);
    }

    public static void removeToken (Integer adminId){
        userTokens.remove(adminId);
    }

    public static Token getToken (Integer adminId){
       return userTokens.get(adminId);
    }
}
