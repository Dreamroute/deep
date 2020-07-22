package com.github.dreamroute.deep.es;

import com.alibaba.fastjson.JSON;
import com.github.dreamroute.deep.domain.User;
import com.github.dreamroute.deep.repository.UserRepository;
import org.elasticsearch.action.index.IndexRequest;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.common.xcontent.XContentType;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.elasticsearch.core.ElasticsearchRestTemplate;
import org.springframework.data.elasticsearch.core.mapping.IndexCoordinates;
import org.springframework.data.elasticsearch.core.query.IndexQuery;
import org.springframework.data.elasticsearch.core.query.IndexQueryBuilder;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

@SpringBootTest
public class ApiTest {

    @Autowired
    private RestHighLevelClient client;
    @Autowired
    private ElasticsearchRestTemplate elasticsearchRestTemplate;
    @Autowired
    private UserRepository userRepository;

    @Test
    public void indexTest() throws IOException {
        IndexRequest request = new IndexRequest("user");
        User user = User.builder().id(100L).name("w.dehai").build();
        String data = JSON.toJSONString(user);
        request.source(data, XContentType.JSON).id(String.valueOf(user.getId()));
        client.index(request, RequestOptions.DEFAULT);
    }

    @Test
    public void templateIndexTest() {
        User user = User.builder().id(100L).name("w.dehai").build();
        IndexQuery indexQueryBuilder = new IndexQueryBuilder()
                .withId(String.valueOf(user.getId()))
                .withObject(user)
                .build();
    }

    @Test
    public void saveTest() {
        for (int i=0; i<10; i++) {
            User user = User.builder().id(Long.valueOf(i)).name("w.dehai").build();
            User u = userRepository.save(user);
            System.err.println(u);
        }
    }

    @Test
    public void findByNameTest() {
        List<User> result = userRepository.findByName("w.dehai");
        System.err.println(result);
    }

    @Test
    public void findOneTest() {
        Optional<User> result = userRepository.findById(3L);
        result.ifPresent(System.err::println);
    }

    @Test
    public void searchTest() {
        User user = User.builder().id(100L).name("w.dehai").password("333").version(1L).build();
        IndexQuery indexQuery = new IndexQueryBuilder().withId(String.valueOf(user.getId())).withObject(user).build();
        String result = elasticsearchRestTemplate.index(indexQuery, null);
        System.err.println(result);
    }

    @Test
    public void saveMultiTest() {
        User user = User.builder().id(100L).name("w.dehai").password("333").version(1L).build();
        elasticsearchRestTemplate.save(user);
        elasticsearchRestTemplate.save(user, IndexCoordinates.of("user-m"));
    }


}














