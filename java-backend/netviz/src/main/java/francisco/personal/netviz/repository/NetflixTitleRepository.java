package francisco.personal.netviz.repository;

import francisco.personal.netviz.entities.NetflixTitle;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface NetflixTitleRepository extends JpaRepository<NetflixTitle, String>{


    @Query("SELECT nt FROM NetflixTitle nt " +
            "WHERE (LOWER(nt.title) LIKE LOWER(CONCAT('%', :title, '%'))) "+
            "AND (LOWER(nt.country) LIKE LOWER(CONCAT('%', :country, '%'))) " +
            "AND (LOWER(nt.rating) LIKE LOWER(CONCAT('%', :rating, '%')))" +
            "AND (LOWER(nt.director) LIKE LOWER(CONCAT('%', :director, '%')))")
    List<NetflixTitle> findByFilters(@Param("name") String title,
                                @Param("minStock") String country,
                                @Param("minPrice") String rating,
                                @Param("maxPrice") String director);
}
