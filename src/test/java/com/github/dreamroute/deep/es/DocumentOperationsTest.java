package com.github.dreamroute.deep.es;

import com.github.dreamroute.deep.domain.User;
import com.github.dreamroute.deep.repository.UserRepository;
import org.elasticsearch.action.search.SearchRequest;
import org.elasticsearch.index.query.MatchAllQueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.builder.SearchSourceBuilder;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.elasticsearch.core.ElasticsearchRestTemplate;
import org.springframework.data.elasticsearch.core.SearchHit;
import org.springframework.data.elasticsearch.core.SearchHits;
import org.springframework.data.elasticsearch.core.mapping.IndexCoordinates;
import org.springframework.data.elasticsearch.core.query.IndexQueryBuilder;
import org.springframework.data.elasticsearch.core.query.NativeSearchQuery;
import org.springframework.data.elasticsearch.core.query.NativeSearchQueryBuilder;
import org.springframework.data.elasticsearch.core.query.Query;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

@SpringBootTest
public class DocumentOperationsTest {

    @Autowired
    private ElasticsearchRestTemplate elasticsearchRestTemplate;
    @Autowired
    private UserRepository userRepository;

    private static User user() {
        return User.builder()
                .id(100L)
                .name("save")
                .password("111")
                .version(1L).build();
    }

    @Test
    public void saveTest() {
        elasticsearchRestTemplate.save(user().setId(1L).setName("save"));
    }

    @Test
    public void saveWithIndexTest() {
        User user = user().setId(2L).setName("saveWithIndex");
        elasticsearchRestTemplate.save(user, IndexCoordinates.of("user-index"));
    }

    @Test
    public void saveListTest() {
        List<User> users = Arrays.asList(user().setId(3L), user().setId(4L));
        Object result = elasticsearchRestTemplate.save(users);
        System.err.println(result);
    }

    @Test
    public void indexTest() {
        String user = elasticsearchRestTemplate.index(new IndexQueryBuilder().withId(String.valueOf(5L)).withObject(user().setId(5L).setName("555")).build(), IndexCoordinates.of("user"));
        System.err.println(user);
    }

    @Test
    public void getTest() {
        User user = elasticsearchRestTemplate.get(String.valueOf(5L), User.class);
        System.err.println(user);
    }

    @Test
    public void multiGetTest() {
        NativeSearchQuery query = new NativeSearchQuery(QueryBuilders.matchAllQuery());
//        List<User> user = elasticsearchRestTemplate.multiGet(query, User.class, IndexCoordinates.of("user"));
//        System.err.println(user);

        SearchHits<User> search = elasticsearchRestTemplate.search(query, User.class);
        long total = search.getTotalHits();
        if (total > 0) {
            List<SearchHit<User>> searchHits = search.getSearchHits();
            searchHits.forEach(su -> {
                User u = su.getContent();
                System.err.println(u);
            });
        }
    }

    @Test
    public void deleteTest() {
    }

    @Test
    public void getMappingTest() {
        Map<String, Object> mapping = elasticsearchRestTemplate.indexOps(User.class).getMapping();
        System.err.println(mapping);
    }

    @Test
    public void searchTest() {
        NativeSearchQueryBuilder builder = new NativeSearchQueryBuilder();
        MatchAllQueryBuilder matchAllQueryBuilder = QueryBuilders.matchAllQuery();
        builder.withQuery(matchAllQueryBuilder);
        SearchHits<User> search = elasticsearchRestTemplate.search(builder.build(), User.class);
        if (search.getTotalHits() > 0) {
            search.forEach(su -> System.err.println(su.getContent()));
        }
    }

}
