package com.barisgngr14.services.impl;

import com.barisgngr14.dto.DtoLoginResponse;
import com.barisgngr14.dto.DtoUserLogin;
import com.barisgngr14.dto.DtoUserSignUp;
import com.barisgngr14.entities.User;
import com.barisgngr14.repositories.UserRepository;
import com.barisgngr14.services.IAuthService;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.server.ResponseStatusException;

import javax.crypto.SecretKey;
import java.util.Date;
import java.util.Optional;
import java.util.UUID;

@Service
public class AuthServiceImpl implements IAuthService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private HttpServletRequest request;

    @Override
    public String getUserToken(DtoUserLogin userLoginData) {
        Optional<User> optional = userRepository.findByEmail(userLoginData.getEmail());
        if(optional.isPresent()){
            User dbUser = optional.get();
            if(checkPassword(userLoginData.getPassword(), dbUser.getHashedPassword())){
                return createToken(dbUser.getUsername());
            }
        }
        throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "Invalid credentials");
    }

    public boolean checkPassword(String rawPassword, String hashedPassword) {
        return passwordEncoder.matches(rawPassword, hashedPassword);
    }

    @Override
    public boolean handleSignUp(DtoUserSignUp signupData) {
        User user = new User();
        user.setUserId(UUID.randomUUID().toString());
        user.setName(signupData.getName());
        user.setSurname(signupData.getSurname());
        user.setEmail(signupData.getEmail());
        user.setUsername(signupData.getUsername());
        String hashedPassword = passwordEncoder.encode(signupData.getPassword());
        user.setHashedPassword(hashedPassword);
        user.setRole(User.Role.USER);
        try {
            User savedUser = userRepository.save(user);
            return savedUser.getUserId() != null;
        } catch (Exception e) {
            return false;
        }
    }

    @Override
    public DtoLoginResponse getUserDetail(){
        DtoLoginResponse userDetail = new DtoLoginResponse();
        String token = request.getHeader("Authorization");
        if(token != null){
            if(extractUsername(token) != null){
                Optional<User> optional = userRepository.findByUsername(extractUsername(token));
                User dbUser = optional.get();
                if(dbUser.getRole().name().equals("ADMIN")){
                    userDetail.setRedirectPath("/admin/dashboard");
                }else{
                    userDetail.setRedirectPath("/app/dashboard");
                }
                BeanUtils.copyProperties(dbUser, userDetail);
                return userDetail;
            }
        }
        return null;
    }

    @Value("${jwt.secret}")
    private String secret;

    private SecretKey getKey() {
        byte[] keyBytes = Decoders.BASE64.decode(secret);
        return Keys.hmacShaKeyFor(keyBytes);
    }

    public String createToken(String username) {
        return Jwts.builder()
                .setSubject(username)
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + 3600000))
                .signWith(getKey())
                .compact();
    }

    public String extractUsername(String token) {
        try {
            return Jwts.parser()
                    .verifyWith(getKey())
                    .build()
                    .parseSignedClaims(token)
                    .getPayload()
                    .getSubject();
        } catch (JwtException e) {
            System.out.println("JWT parse hatası: " + e.getMessage());
            return null;
        }
    }

}
