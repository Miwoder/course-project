package com.leverx.govoronok.repository;

import com.leverx.govoronok.model.Role;
import com.leverx.govoronok.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    List<User> getUsersByApprovedIsTrueAndConfirmedByAdminFalseAndRoleIs(Role role);

    List<User> getUsersByApprovedIsTrueAndConfirmedByAdminTrueAndRoleIs(Role role);

    User getUserByEmail(String email);

}
