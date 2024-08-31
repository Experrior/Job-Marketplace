package edu.pwr.backend.entities

import edu.pwr.backend.dto.UserProfileDTO
import jakarta.persistence.*
import java.sql.Timestamp
import java.time.Instant

@Entity(name = "user_profiles")
class UserProfile(
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    var profileId: Int? = null,

    @ManyToOne
    @JoinColumn(name = "userId", referencedColumnName = "userId")
    var userId: User = User(),

    @Column(nullable = false)
    var resumePath: String = "",

    @Column(nullable = false)
    var profilePicturePath: String = "",

    @Column(nullable = false)
    var updatedAt: Timestamp = Timestamp(0),

    ) {

    @PrePersist
    fun onCreate() {
        val currentTimestamp = Timestamp.from(Instant.now())
        updatedAt = currentTimestamp
    }

    fun toDTO(): UserProfileDTO {
        return UserProfileDTO(
            profileId = this.profileId,
            userId = this.userId,
            resumePath = this.resumePath,
            profilePicturePath = this.profilePicturePath,
            updatedAt = this.updatedAt
        )
    }
}