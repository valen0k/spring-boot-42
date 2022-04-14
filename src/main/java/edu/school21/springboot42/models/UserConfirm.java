package edu.school21.springboot42.models;

import lombok.*;

import javax.persistence.*;

@Entity
@Data
@NoArgsConstructor
@RequiredArgsConstructor
@Table(name = "users_confirm")
public class UserConfirm {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @NonNull
    @Column(name = "user_email")
    private String email;

    @NonNull
    @Column(name = "user_uuid")
    private String userUuid;

}
