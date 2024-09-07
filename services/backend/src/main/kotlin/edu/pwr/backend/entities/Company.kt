package edu.pwr.backend.entities


import jakarta.persistence.*
import java.sql.Timestamp
import java.time.Instant

@Entity
class Company(
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    var companyId: Int? = null,
    @Column(nullable = false)
    var companyName: String = "default",
    @Column(nullable = false)
    var location: String = "default",
    @Column(nullable = false)
    var industry: String = "default",
    @Column(nullable = false)
    var description: String = "default",
    @Column(nullable = false)
    var verified: Boolean = false,
    @Column(nullable = false)
    var createdAt: Timestamp = Timestamp.from(Instant.now()),
    @Column(nullable = false)
    var updatedAt: Timestamp = Timestamp.from(Instant.now()),
) {


    @PrePersist
    fun onCreate() {
        val currentTimestamp = Timestamp.from(Instant.now())
        createdAt = currentTimestamp
        updatedAt = currentTimestamp
    }

    @PreUpdate
    fun onUpdate() {
        val currentTimestamp = Timestamp.from(Instant.now())
        updatedAt = currentTimestamp
    }
}