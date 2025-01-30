package francisco.personal.netviz;

import francisco.personal.netviz.controller.NetflixController;
import francisco.personal.netviz.entities.NetflixTitle;
import francisco.personal.netviz.repository.NetflixTitleRepository;
import francisco.personal.netviz.service.NetflixTitleService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.domain.*;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Arrays;
import java.util.List;

import static org.mockito.Mockito.*;
import static org.mockito.ArgumentMatchers.any;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.hamcrest.Matchers.*;

@WebMvcTest(NetflixController.class)
class NetflixControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private NetflixTitleService netflixTitleService;

    private NetflixTitle sampleTitle1;
    private NetflixTitle sampleTitle2;

    @BeforeEach
    void setUp() {

        sampleTitle1 = new NetflixTitle();
        sampleTitle1.setShowId("1");
        sampleTitle1.setTitle("Breaking Bad");
        sampleTitle1.setCountry("USA");
        sampleTitle1.setRating("TV-MA");
        sampleTitle1.setDirector("Vince Gilligan");

        sampleTitle2 = new NetflixTitle();
        sampleTitle2.setShowId("2");
        sampleTitle2.setTitle("Money Heist");
        sampleTitle2.setCountry("Spain");
        sampleTitle2.setRating("TV-MA");
        sampleTitle2.setDirector("Álex Pina");
    }

    @Test
    void testGetAllTitlesWithPagination() throws Exception {
        List<NetflixTitle> titles = Arrays.asList(sampleTitle1, sampleTitle2);
        Page<NetflixTitle> page = new PageImpl<>(titles, PageRequest.of(0, 2, Sort.by("title")), titles.size());

        when(netflixTitleService.getAllNetflixTitles(anyInt(), anyInt(), anyString())).thenReturn(page);

        mockMvc.perform(get("/api/netflix/titles")
                        .param("page", "0")
                        .param("size", "2")
                        .param("sortBy", "title")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.content", hasSize(2)))
                .andExpect(jsonPath("$.content[0].title", is("Breaking Bad")))
                .andExpect(jsonPath("$.content[1].title", is("Money Heist")))
                .andExpect(jsonPath("$.totalElements", is(2)))
                .andExpect(jsonPath("$.totalPages", is(1)));
    }

    @Test
    void testGetTitlesByFilter() throws Exception {
        List<NetflixTitle> filteredTitles = List.of(sampleTitle1);
        when(netflixTitleService.getFilteredProducts("Breaking Bad", "USA", "TV-MA", "Vince Gilligan"))
                .thenReturn(filteredTitles);

        mockMvc.perform(get("/api/netflix/search")
                        .param("title", "Breaking Bad")
                        .param("country", "USA")
                        .param("rating", "TV-MA")
                        .param("director", "Vince Gilligan")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(1)))
                .andExpect(jsonPath("$[0].title", is("Breaking Bad")))
                .andExpect(jsonPath("$[0].country", is("USA")));
    }

    @Test
    void testGetTitlesByFilterNoResults() throws Exception {
        when(netflixTitleService.getFilteredProducts("Unknown", "UK", "PG-13", "Some Director"))
                .thenReturn(List.of());

        mockMvc.perform(get("/api/netflix/search")
                        .param("title", "Unknown")
                        .param("country", "UK")
                        .param("rating", "PG-13")
                        .param("director", "Some Director")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(0)));  // Expecting empty list
    }
}
