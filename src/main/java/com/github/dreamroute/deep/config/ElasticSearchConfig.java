package com.github.dreamroute.deep.config;

import org.elasticsearch.client.RestHighLevelClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.elasticsearch.client.ClientConfiguration;
import org.springframework.data.elasticsearch.client.RestClients;
import org.springframework.data.elasticsearch.config.AbstractElasticsearchConfiguration;
import org.springframework.data.elasticsearch.core.ElasticsearchRestTemplate;

@Configuration
public class ElasticSearchConfig extends AbstractElasticsearchConfiguration {

    @Bean
    @Override
    public RestHighLevelClient elasticsearchClient() {
        final ClientConfiguration clientConfiguration = ClientConfiguration.builder()
                .connectedTo("10.82.12.67:9200")
                .build();
        return RestClients.create(clientConfiguration).rest();
    }

    @Bean
    public ElasticsearchRestTemplate elasticsearchRestTemplate(@Autowired RestHighLevelClient highLevelClient) {
        return new ElasticsearchRestTemplate(highLevelClient);
    }
}
