
package com.wilma.entity.users;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.wilma.entity.docs.UserDocument;
import lombok.*;

import javax.persistence.*;
import java.util.Collection;
import java.util.LinkedList;
import java.util.Set;

/**
 * User super class
 */
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "users")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
public class UserAccount {

    @Id
    @GeneratedValue
    @Column(name = "id", nullable = false)
    private Integer userId;

    @Column(name = "username", unique = true)
    private String username;

    @Column(name = "password")
    private String password;

    @Column(name = "email")
    private String email;
    
    @Lob
    @Column(name = "bio")
    private String bio;

    @Column(name = "credentials_non_expired")
    public boolean credentialsNonExpired;

    @Column(name = "account_non_locked")
    public boolean accountNonLocked;

    @Column(name = "account_non_expired")
    public boolean accountNonExpired;

    @Column(name = "active")
    private boolean enabled;

    @JsonIgnore
    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name = "user_roles",
            joinColumns = @JoinColumn(name = "user_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "role_id", referencedColumnName = "id"))
    private Set<Role> roles = new java.util.LinkedHashSet<>();

    @JsonIgnore
    @OneToMany(mappedBy = "userAccount", cascade = CascadeType.ALL)
    @ToString.Exclude
    private Collection<UserDocument> userDocuments = new java.util.ArrayList<>();

    public UserAccount(String username) {
        this.username = username;
    }


    public UserAccount(Integer userId, String username, String password, String email, String bio, boolean credentialsNonExpired, boolean accountNonLocked, boolean accountNonExpired, boolean enabled, Set<Role> roles) {
        this.userId = userId;
        this.username = username;
        this.password = password;
        this.email = email;
        this.bio = bio;
        this.credentialsNonExpired = credentialsNonExpired;
        this.accountNonLocked = accountNonLocked;
        this.accountNonExpired = accountNonExpired;
        this.enabled = enabled;
        this.roles = roles;
    }
}
