package me.alxndr.demo.security.jwt;

import java.io.Serializable;

/**
 * @author : Alexander Choi
 * @date : 2021/12/17
 */
public class JWTResponse implements Serializable {

    private static final long serialVersionUID = -8091879091924046844L;
    private final String jwttoken;

    public JWTResponse(String jwttoken) {
        this.jwttoken = jwttoken;
    }

    public String getToken() {
        return this.jwttoken;
    }
}
