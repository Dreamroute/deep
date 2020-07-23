package com.github.dreamroute.deep.es;

import com.github.dreamroute.deep.domain.User;
import com.github.dreamroute.deep.repository.UserRepository;
import org.elasticsearch.index.query.QueryBuilders;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.elasticsearch.core.ElasticsearchRestTemplate;
import org.springframework.data.elasticsearch.core.SearchHit;
import org.springframework.data.elasticsearch.core.SearchHits;
import org.springframework.data.elasticsearch.core.mapping.IndexCoordinates;
import org.springframework.data.elasticsearch.core.query.Criteria;
import org.springframework.data.elasticsearch.core.query.CriteriaQuery;
import org.springframework.data.elasticsearch.core.query.IndexQueryBuilder;
import org.springframework.data.elasticsearch.core.query.NativeSearchQuery;
import org.springframework.data.elasticsearch.core.query.NativeSearchQueryBuilder;
import org.springframework.data.elasticsearch.core.query.StringQuery;

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
        elasticsearchRestTemplate.save(user().setId(8L).setName("我是个中国人"));
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
        builder.withQuery(QueryBuilders.matchAllQuery())
                .withPageable(PageRequest.of(0, 2));
        SearchHits<User> result = elasticsearchRestTemplate.search(builder.build(), User.class);
        if (result.getTotalHits() > 0) {
            result.forEach(userSearchHit -> System.err.println(userSearchHit.getContent()));
        }
    }

    @Test
    public void stringQueryTest() {
        SearchHits<User> result = elasticsearchRestTemplate.search(new StringQuery(QueryBuilders.matchAllQuery().toString()), User.class);
        System.err.println(result);
    }

    @Test
    public void criteriaQueryTest() {
        CriteriaQuery criteriaQuery = new CriteriaQuery(new Criteria("name").is("save")).setPageable(PageRequest.of(0, 2));
        SearchHits<User> result = elasticsearchRestTemplate.search(criteriaQuery, User.class);
        if (result.getTotalHits() > 0) {
            result.forEach(userSearchHit -> System.err.println(userSearchHit.getContent()));
        }
    }

    @Test
    public void searchOneTest() {
        NativeSearchQuery query = new NativeSearchQueryBuilder().withQuery(QueryBuilders.termQuery("name", "555")).build();
        SearchHit<Object> result = elasticsearchRestTemplate.searchOne(query, Object.class, IndexCoordinates.of("user", "user-index"));
        System.err.println(result);
    }

    @Test
    public void moreLikeThisTest() {
    }

    @Test
    public void suggestTest() {
    }

    @Test
    public void fuzzyTest() {
        SearchHits<User> result = elasticsearchRestTemplate.search(new NativeSearchQueryBuilder().withQuery(QueryBuilders.fuzzyQuery("name", "中国人")).build(), User.class);
        if (result.getTotalHits() > 0) {
            result.forEach(userSearchHit -> System.err.println(userSearchHit.getContent()));
        }
    }

    @Test
    public void comlexTest() {
        SearchHits<User> search = elasticsearchRestTemplate.search(new NativeSearchQueryBuilder().withQuery(QueryBuilders.termQuery("name", "我是个中国人")).build(), User.class);
        print(search);

        search = elasticsearchRestTemplate.search(new NativeSearchQueryBuilder().withQuery(QueryBuilders.matchPhraseQuery("name", "中国人")).build(), User.class);
        print(search);

    }

    private static void print(SearchHits<User> result) {
        if (result.getTotalHits() > 0) {
            result.forEach(userSearchHit -> System.err.println(userSearchHit.getContent()));
        }
    }

}

























