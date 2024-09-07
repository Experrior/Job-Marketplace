package edu.pwr.backend.entities

import jakarta.persistence.*
import org.hibernate.annotations.OnDelete
import org.hibernate.annotations.OnDeleteAction
import java.sql.Timestamp
import java.time.Instant


@Entity(name = "app_users")
class User(
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    var userId: Int? = null,

    @ManyToOne(cascade = [(CascadeType.ALL)])
    @JoinColumn(name = "company_id", nullable = true)
    @OnDelete(action = OnDeleteAction.CASCADE)
    var company: Company = Company(),

    @Column(nullable = false)
    var email: String = "",
    @Column(nullable = false)
    var firstName: String = "",
    @Column(nullable = false)
    var lastName: String = "",
    @Column(nullable = true)
    var phone: String = "",
    @Column(nullable = false)
    var role: String = "applicant",
    @Column(nullable = false)
    var isBlocked: Boolean = false,
    @Column(nullable = false)
    var emailVerified: Boolean = false,
    @Column(nullable = false)
    var employeeVerified: Boolean = false,
    @Column(nullable = false)
    var createdAt: Timestamp = Timestamp(0),
    @Column(nullable = false)
    var passwordHash: String = "",

    ) {

    @PrePersist
    fun onCreate() {
        val currentTimestamp = Timestamp.from(Instant.now())
        createdAt = currentTimestamp
    }


}