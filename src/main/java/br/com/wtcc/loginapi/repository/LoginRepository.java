package br.com.wtcc.loginapi.repository;

import br.com.wtcc.loginapi.model.LoginModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface LoginRepository extends JpaRepository<LoginModel, Long> {
    @Query(value = "SELECT * FROM ",nativeQuery = true)
    Optional<LoginModel> findByUserName(@Param("username")String username);

    @Query(value = "SELECT * FROM TB_USER WHERE USER_EMAIL = :useremail", nativeQuery = true)
    Optional<LoginModel> findByUserEmail(@Param("useremail") String userEmail);

    @Query(value = "SELECT * FROM TB_USER WHERE USER_CPF = :usercpf", nativeQuery = true)
    Optional<LoginModel> findByUserCpf(@Param("usercpf") String userCpf);

}
