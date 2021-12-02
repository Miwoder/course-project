package com.leverx.govoronok.service;

import com.leverx.govoronok.model.Role;
import com.leverx.govoronok.model.User;
import com.leverx.govoronok.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service("userService")
public class UserService implements UserDetailsService {
    private UserRepository userRepository;
    private PasswordEncoder passwordEncoder;

    @Autowired
    public UserService(UserRepository userRepository,PasswordEncoder passwordEncoder){
        this.passwordEncoder = passwordEncoder;
        this.userRepository = userRepository;
    }

    public void addNewUser(User user){
//        Optional<User> userFromDB = Optional.of(userRepository.findById(user.getId()).get());
//
//        if(userFromDB==null) {
            user.setPassword(passwordEncoder.encode(user.getPassword()));
            userRepository.save(user);
//        }
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
        ;
        return new org.springframework.security.core.userdetails.User(user.getEmail(), user.getPassword(), mapRolesToAuthorities(roleList));
    }

    private Collection<? extends GrantedAuthority> mapRolesToAuthorities(Collection<Role> roles){
        return roles.stream().map(role -> new SimpleGrantedAuthority(role.getRole())).collect(Collectors.toList());
    }

}
