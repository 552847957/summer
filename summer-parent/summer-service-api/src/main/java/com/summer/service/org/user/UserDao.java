//package com.summer.service.impl.org.user;
//
//import com.summer.service.org.role.RoleDto;
//import com.summer.service.org.scope.ScopeDto;
//
//import org.springframework.data.jpa.repository.JpaRepository;
//import org.springframework.data.jpa.repository.Query;
//
//import java.util.Set;
//
//public interface UserDao extends JpaRepository<User, Long> {
//
//    //@EntityGraph("User.roles")
//    //UserProj findUserWithRolesById(Long id);
//
//    @Query("SELECT new com.summer.service.org.scope.ScopeDto(s.id, s.permission, s.target) " +
//            "FROM " +
//            "(SELECT DISTINCT(s) " +
//            "FROM User u JOIN FETCH u.roles r JOIN FETCH r.scopes s " +
//            "WHERE u.id = ?1) " +
//            "s")
//    Set<ScopeDto> findUserScopesById(Long id);
//
//
//    SELECT new com.summer.service.org.role.RoleDto(r.id, r.name)
//    FROM User u JOIN FETCH u.roles r
//    WHERE u.id = ?1
//    Set<RoleDto> findUserRolesById(Long id);
//
//
//
//}
