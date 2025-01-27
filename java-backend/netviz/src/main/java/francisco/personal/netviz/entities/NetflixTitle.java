package francisco.personal.netviz.entities;

import jakarta.persistence.*;
import lombok.Data;
import lombok.Setter;

import java.time.LocalDate;

@Data
@Entity
@Table(name = "netflix_titles")
public class NetflixTitle {

    @Id
    private String show_id;

    @Setter
    @Column(nullable = false)
    private String title;

    @Column(nullable = false)
    private String type;

    private String director;

    @Column(columnDefinition = "TEXT")
    private String cast_members;

    private String country;

    private LocalDate dateAdded;

    private int releaseYear;

    private String rating;

    private String duration;

    @Column(columnDefinition = "TEXT")
    private String listed_in;

    @Column(columnDefinition = "TEXT")
    private String description;

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getListed_in() {
        return listed_in;
    }

    public void setListed_in(String listed_in) {
        this.listed_in = listed_in;
    }

    public String getDuration() {
        return duration;
    }

    public void setDuration(String duration) {
        this.duration = duration;
    }

    public String getRating() {
        return rating;
    }

    public void setRating(String rating) {
        this.rating = rating;
    }

    public int getReleaseYear() {
        return releaseYear;
    }

    public void setReleaseYear(int releaseYear) {
        this.releaseYear = releaseYear;
    }

    public LocalDate getDateAdded() {
        return dateAdded;
    }

    public void setDateAdded(LocalDate dateAdded) {
        this.dateAdded = dateAdded;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getCast_members() {
        return cast_members;
    }

    public void setCast_members(String cast_members) {
        this.cast_members = cast_members;
    }

    public String getDirector() {
        return director;
    }

    public void setDirector(String director) {
        this.director = director;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getTitle() {
        return title;
    }

    public String getShowId() {
        return show_id;
    }

    public void setShowId(String show_id) {
        this.show_id = show_id;
    }


}
