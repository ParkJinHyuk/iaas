package jinhyuk.service.api;

import jinhyuk.dto.request.CreateNodeRequestDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.http.client.utils.URIBuilder;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.net.URI;
import java.net.URISyntaxException;


@Slf4j
@RequiredArgsConstructor
@Component
public class MyApi {

    private final RestTemplate restTemplate;

    // API 서버로 스케일 아웃 요청
    public void createNode(Long clusterId) throws URISyntaxException {
        log.info("Create Node API Request!!!");
        URIBuilder uriBuilder = new URIBuilder();
        String queryUrl = uriBuilder.setScheme(null).setHost(null).setPath("/api/v1/clusters/" + clusterId).build().toString();
        restTemplate.exchange(URI.create("http://27.96.131.225:8080" + queryUrl), HttpMethod.POST, new HttpEntity<>(new CreateNodeRequestDto(1)), String.class);
    }


    // API 서버로 노드 삭제 요청
    public void deleteNode(Long nodeId) throws URISyntaxException {
        log.info("Delete Node API Request!!!");
        URIBuilder uriBuilder = new URIBuilder();
        String queryUrl = uriBuilder.setScheme(null).setHost(null).setPath("/api/v1/nodes/" + nodeId).build().toString();
        restTemplate.delete(URI.create("http://27.96.131.225:8080" + queryUrl));
    }
}
