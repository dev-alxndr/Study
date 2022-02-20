package me.alxndr.demo;

import lombok.RequiredArgsConstructor;
import me.alxndr.demo.security.jwt.JWTRequest;
import me.alxndr.demo.security.jwt.JWTResponse;
import me.alxndr.demo.security.jwt.JWTTokenUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticatedPrincipal;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;

/**
 * @author : Alexander Choi
 * @date : 2021/12/17
 */
@RestController
@CrossOrigin
@RequiredArgsConstructor
public class JWTAuthenticationController {

    private final AuthenticationManager authenticationManager;

    private final JWTTokenUtils jwtTokenUtil;

    private final JwtUserDetailsService userDetailsService;

    @GetMapping("/hello")
    public String getHello(Authentication authentication) {
        String name = authentication.getName();
        return name;
    }

    @RequestMapping(value = "/authenticate", method = RequestMethod.POST)
    public ResponseEntity<?> createAuthenticationToken(@RequestBody JWTRequest authenticationRequest) throws Exception {
        authenticate(authenticationRequest.getUsername(), authenticationRequest.getPassword());

        final UserDetails userDetails = userDetailsService
                .loadUserByUsername(authenticationRequest.getUsername());

        final String token = jwtTokenUtil.generateToken(userDetails);

        return ResponseEntity.ok(new JWTResponse(token));
    }

    private void authenticate(String username, String password) throws Exception {
        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));
        } catch (DisabledException e) {
            throw new Exception("USER_DISABLED", e);
        } catch (BadCredentialsException e) {
            throw new Exception("INVALID_CREDENTIALS", e);
        }
    }

}
