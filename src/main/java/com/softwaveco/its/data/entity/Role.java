package com.softwaveco.its.data.entity;

import com.softwaveco.its.enums.UserRole;
import lombok.*;

import javax.persistence.*;

@Entity
@Table(name = "roles", schema = "traffic_schema")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder(toBuilder = true)
public class Role {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Enumerated(EnumType.STRING)
    @Column(length = 20)
    private UserRole name;
}