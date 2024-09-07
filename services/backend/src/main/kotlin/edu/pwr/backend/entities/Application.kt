package edu.pwr.backend.entities

import jakarta.persistence.*
import java.sql.Timestamp
import java.time.Instant
import lombok.AllArgsConstructor
import lombok.NoArgsConstructor
import org.hibernate.annotations.OnDelete
import org.hibernate.annotations.OnDeleteAction

@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "applications")
class Application(
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    var applicationId: Int? = null,

    @ManyToOne
    @JoinColumn(name = "userId", referencedColumnName = "userId")
    var userId: User = User(),

    @ManyToOne(cascade = [(CascadeType.ALL)])
    @JoinColumn(name = "job_id", nullable = true)
    @OnDelete(action = OnDeleteAction.CASCADE)
    var job: Job = Job(),


    @Column(nullable = false)
    var status: String = "",
    @Column(nullable = false)
    var applicationDate: Timestamp = Timestamp(0),
) {
    @PrePersist
    fun onCreate() {
        val currentTimestamp = Timestamp.from(Instant.now())
        status = "PENDING"
        applicationDate = currentTimestamp
    }

}