package com.leverx.govoronok.service;

import com.leverx.govoronok.model.Role;
import com.leverx.govoronok.model.User;
import com.leverx.govoronok.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

import java.util.*;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UserService implements UserDetailsService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JavaMailSender emailSender;
    private final JedisPool jedisPool;

    public void addNewUser(User user){
        UUID code = UUID.randomUUID();
        try(Jedis jedis = jedisPool.getResource()){
            jedis.set(code.toString() ,user.getEmail());
            jedis.expire(code.toString(), 86400);
            sendConfirmMessageToUserByEmail(user.getEmail(), code);
            user.setPassword(passwordEncoder.encode(user.getPassword()));
            userRepository.save(user);
        }
    }

    public void confirmUser(UUID code){
        try(Jedis jedis = jedisPool.getResource()){
            String email = jedis.get(code.toString());
            User user = findByUsername(email);
            if(user!=null){
                user.setApproved(Boolean.TRUE);
                userRepository.save(user);
                jedis.del(code.toString());
            }
            else{
                System.out.println("USER NOT FOUND");
            }
        }
    }

    public void resetPasswordForUserByEmail(String email){
        UUID code = UUID.randomUUID();
        try(Jedis jedis = jedisPool.getResource()){
            jedis.set(code.toString() , email);
            jedis.expire(code.toString(), 3600);
            sendMessageForResetPasswordToUserByEmail(email, code);
        }
    }

    public List<User> getAllUnconfirmedUsers(Role traderRole){
        return userRepository.getUsersByApprovedIsTrueAndConfirmedByAdminFalseAndRoleIs(traderRole);
    }

    public Optional<User> getUserById(Long id){
        return userRepository.findById(id);
    }

    public void deleteUserById(Long id){
        userRepository.deleteById(id);
    }

    public void setApprovedStatusToUserById(Long id){
        Optional<User> user = userRepository.findById(id);
        if (user.isPresent()) {
            user.get().setConfirmedByAdmin(Boolean.TRUE);
            userRepository.save(user.get());
        } else {
            System.out.println("ERROR WITH APPR");
        }
    }

    public void setNewPasswordWithCode(UUID code, String password){
        try(Jedis jedis = jedisPool.getResource()){
            String email = jedis.get(code.toString());
            User user = findByUsername(email);
            if(user!=null){
                user.setPassword(passwordEncoder.encode(password));
                userRepository.save(user);
                jedis.del(code.toString());
            }
            else{
                System.out.println("USER NOT FOUND");
            }
        }
    }

    public List<User> getAllTraders(Role traderRole){
        return userRepository.getUsersByApprovedIsTrueAndConfirmedByAdminTrueAndRoleIs(traderRole);
    }

    public User findByUsername(String username){
        return userRepository.getUserByEmail(username);
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        User user = findByUsername(email);
        if(user == null){
            throw new UsernameNotFoundException(String.format("User with email '%s' not found", email));
        }
        List<Role> roleList = new ArrayList<>();
        roleList.add(user.getRole());

        return new org.springframework.security.core.userdetails.User(user.getEmail(), user.getPassword(), mapRolesToAuthorities(roleList));
    }

    private Collection<? extends GrantedAuthority> mapRolesToAuthorities(Collection<Role> roles){
        return roles.stream().map(role -> new SimpleGrantedAuthority(role.getRole())).collect(Collectors.toList());
    }

    public void sendConfirmMessageToUserByEmail(String email, UUID code) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom("hovor007@gmail.com");
        message.setTo(email);
        message.setSubject("Confirm registration");
        message.setText("Follow this link to continue registration: http://localhost:8086/authentication/confirm/" + code +
                "\n Please, note if you are trader you should wait until administration confirmed you too");
        emailSender.send(message);
    }

    public void sendMessageForResetPasswordToUserByEmail(String email, UUID code) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom("hovor007@gmail.com");
        message.setTo(email);
        message.setSubject("Reset password");
        message.setText("Follow this link if you'd like to reset your password: http://localhost:8086/authentication/reset"
                + "\n Your code: " + code +
                "\n This code will expire in 1 hour" +
                "\n If you don't want to reset your password, please ignore this message and your password will not be changed");
        emailSender.send(message);
    }

}
