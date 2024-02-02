package com.zian.travelo.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Builder
@Table(name = "users")
public class User {
    @Id
    private String email;
    private String password;
    private Boolean active;

    @ManyToOne
    @JoinColumn(name = "role_id")
    private Role role;
}

