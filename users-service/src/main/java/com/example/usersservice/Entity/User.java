package com.example.usersservice.Entity;

import com.example.usersservice.VO.RoleType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;
import javax.persistence.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(
        name = "bookstore_user",
        schema = "service_users"
)
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequence_user_generator")
    @GenericGenerator(
            name = "sequence_user_generator",
            strategy = "org.hibernate.id.enhanced.SequenceStyleGenerator",
            parameters = {
                    @Parameter(name = "sequence_name", value = "service_users.seq_user"),
                    @Parameter(name = "initial_value", value = "1000"),
                    @Parameter(name = "increment_size", value = "1")
            }
    )
    public Long id;
    @Column(nullable = false)
    public String login;

    @Column(nullable = false)
    private String password;
    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private RoleType role;
}
