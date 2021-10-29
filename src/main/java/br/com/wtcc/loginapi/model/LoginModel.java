package br.com.wtcc.loginapi.model;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Entity
@Table(name = "TB_USER")
public class LoginModel implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private Integer id;

    @Column(name = "USER_NAME")
    private String userName;

    @Column(name = "USER_PASSWORD")
    private String userPassword;

    @Column(name = "USER_CREATED")
    private Date dateCreated;

    @Column(name = "USER_CPF")
    private String userCpf;

    @Column(name = "FIRST_NAME")
    private String firstName;

    @Column(name = "LAST_NAME")
    private String lastName;

    @Column(name = "PERMISSION")
    private String userPermission;

    @Column(name = "USER_EMAIL")
    private String userEmail;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserPassword() {
        return userPassword;
    }

    public void setUserPassword(String userPassword) {
        this.userPassword = userPassword;
    }

    public Date getDateCreated() {
        return dateCreated;
    }

    public void setDateCreated(Date dateCreated) {
        this.dateCreated = dateCreated;
    }

    public String getUserCpf() {
        return userCpf;
    }

    public void setUserCpf(String userCpf) {
        this.userCpf = userCpf;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getPermission() {
        return userPermission;
    }

    public void setPermission(String permission) {
        this.userPermission = permission;
    }

    public String getUserEmail() {
        return userEmail;
    }

    public void setUserEmail(String userEmail) {
        this.userEmail = userEmail;
    }

    public LoginModel(Integer id, String userName, String userPassword, Date dateCreated, String userCpf, String firstName, String lastName, String userPermission, String userEmail) {
        this.id = id;
        this.userName = userName;
        this.userPassword = userPassword;
        this.dateCreated = dateCreated;
        this.userCpf = userCpf;
        this.firstName = firstName;
        this.lastName = lastName;
        this.userPermission = userPermission;
        this.userEmail = userEmail;
    }

    public LoginModel() {
    }

}
