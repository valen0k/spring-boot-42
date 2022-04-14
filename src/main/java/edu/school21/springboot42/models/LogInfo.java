package edu.school21.springboot42.models;

import lombok.*;

import javax.persistence.*;
import java.util.Date;


@Data
@RequiredArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "loginfo")
public class LogInfo {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "loginfo_generator")
    @SequenceGenerator(name="loginfo_generator", sequenceName = "loginfo_seq", allocationSize = 1)
    @Column(name = "id", updatable = false, nullable = false)
    private long id;

    @NonNull
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    @NonNull
    @Column(name = "ip")
    private String ip;

    @Column(name = "sign_time")
    private Date signTime;

    @PrePersist
    protected void onCreate() {
        signTime = new Date();
    }

}
