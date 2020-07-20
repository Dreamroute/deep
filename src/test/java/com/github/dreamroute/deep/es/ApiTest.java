package com.github.dreamroute.deep.es;

import com.alibaba.fastjson.JSON;
import com.github.dreamroute.deep.domain.User;
import org.elasticsearch.action.index.IndexRequest;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.common.xcontent.XContentType;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.elasticsearch.core.ElasticsearchOperations;
import org.springframework.data.elasticsearch.core.query.IndexQuery;
import org.springframework.data.elasticsearch.core.query.IndexQueryBuilder;

import java.io.IOException;

@SpringBootTest
public class ApiTest {

    @Autowired
    private RestHighLevelClient client;
    @Autowired
    private ElasticsearchOperations elasticsearchOperations;

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

}
