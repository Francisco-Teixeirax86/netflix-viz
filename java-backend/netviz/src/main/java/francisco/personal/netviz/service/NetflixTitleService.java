package francisco.personal.netviz.service;

import francisco.personal.netviz.entities.NetflixTitle;
import francisco.personal.netviz.repository.NetflixTitleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class NetflixTitleService {

    @Autowired
    private NetflixTitleRepository netflixTitleRepository;


    public List<NetflixTitle> getAllNetflixTitles() {
        return netflixTitleRepository.findAll();
    }

    public NetflixTitle getProductById(String show_id) {
        return netflixTitleRepository.findById(show_id).orElseThrow(() -> new RuntimeException("Not Found"));
    }

    public List<NetflixTitle> getFilteredProducts(String title, String country, String rating, String director) {
        return netflixTitleRepository.findByFilters(title, country, rating, director);
    }
}
