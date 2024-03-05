package com.ra.model.repository;

import com.ra.model.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User,Long> {
    Optional<User> findByUsername(String username);
    boolean existsByUsername(String userName);
    @Query(value = "select u.* from user u join user_role ur on u.id=ur.user_id where role_id=2",nativeQuery = true)
    List<User> getAllUser();

}
