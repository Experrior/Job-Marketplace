package edu.pwr.backend.entities


import jakarta.persistence.*
import java.sql.Timestamp
import java.time.Instant


@Entity(name = "experiences")
class Experience(
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    var experienceId: Int? = null,

    @ManyToOne
    @JoinColumn(name = "profileId", referencedColumnName = "profileId")
    var profileId: UserProfile = UserProfile(),

    @Column(nullable = false)
    var companyName: String = "",
    @Column(nullable = false)
    var role: String = "",
    @Column(nullable = false)
    var startDate: Timestamp = Timestamp(0),
    @Column(nullable = false)
    var endDate: Timestamp = Timestamp(0),

    @Column(nullable = false)
    var updatedAt: Timestamp = Timestamp(0),
) {

    @PreUpdate
    fun onUpdate() {
        val currentTimestamp = Timestamp.from(Instant.now())
        updatedAt = currentTimestamp
    }



}