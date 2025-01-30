package francisco.personal.netviz.entities;

import jakarta.persistence.*;

import java.time.LocalDate;

@Entity
@Table(name = "netflix_titles")
public class NetflixTitle {

    @Id
    @Column(name = "show_id")
    private String showId;

    @Column(nullable = false)
    private String title;

    @Column(nullable = false)
    private String type;

    @Column(name = "director")
    private String director;

    @Column(name="cast_members", columnDefinition = "TEXT")
    private String castMembers;

    @Column(name = "country")
    private String country;

    @Column(name = "date_added")
    private LocalDate dateAdded;

    @Column(name = "release_year")
    private int releaseYear;

    @Column(name = "rating")
    private String rating;

    @Column(name = "duration")
    private String duration;

    @Column(name = "listed_in")
    private String listedIn;

    @Column(columnDefinition = "TEXT")
    private String description;

    public String getShowId() {
        return showId;
    }

    public void setShowId(String showId) {
        this.showId = showId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getDirector() {
        return director;
    }

    public void setDirector(String director) {
        this.director = director;
    }

    public String getCastMembers() {
        return castMembers;
    }

    public void setCastMembers(String castMembers) {
        this.castMembers = castMembers;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public LocalDate getDateAdded() {
        return dateAdded;
    }

    public void setDateAdded(LocalDate dateAdded) {
        this.dateAdded = dateAdded;
    }

    public int getReleaseYear() {
        return releaseYear;
    }

    public void setReleaseYear(int releaseYear) {
        this.releaseYear = releaseYear;
    }

    public String getRating() {
        return rating;
    }

    public void setRating(String rating) {
        this.rating = rating;
    }

    public String getDuration() {
        return duration;
    }

    public void setDuration(String duration) {
        this.duration = duration;
    }

    public String getListedIn() {
        return listedIn;
    }

    public void setListedIn(String listedIn) {
        this.listedIn = listedIn;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
