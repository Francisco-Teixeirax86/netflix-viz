package francisco.personal.netviz.controller;

import francisco.personal.netviz.entities.NetflixTitle;
import francisco.personal.netviz.repository.NetflixTitleRepository;
import francisco.personal.netviz.service.NetflixTitleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/netflix")
public class NetflixController {

    @Autowired
    private NetflixTitleService netflixTitleService;

    @GetMapping("/titles")
    public List<NetflixTitle> getAllTitles() {
        return netflixTitleService.getAllNetflixTitles();
    }

    @GetMapping("/search")
    public ResponseEntity<List<NetflixTitle>> getTitlesByFilter(
            @RequestParam(required = false) String title,
            @RequestParam(required = false) String country,
            @RequestParam(required = false) String rating,
            @RequestParam(required = false) String director
    ) {
        List<NetflixTitle> netflixTitles = netflixTitleService.getFilteredProducts(title, country, rating, director);
        return new ResponseEntity<>(netflixTitles, HttpStatus.OK);
    }
}
