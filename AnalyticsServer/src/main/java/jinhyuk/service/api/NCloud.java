package jinhyuk.service.api;

import jinhyuk.dto.data.*;
import jinhyuk.dto.response.*;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.IOUtils;
import org.apache.http.client.utils.URIBuilder;
import org.apache.tomcat.util.codec.binary.Base64;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Component;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URI;
import java.net.URISyntaxException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Objects;

@Slf4j
@RequiredArgsConstructor
@Component
public class NCloud {

    private final RestTemplate restTemplate;

    private static final String accessKey = "tD5tJ26iniKfkPlwFOzZ";
    private static final String secretKey = "KGhVk9vwc4V1QesvB6oNfqua5qRHRxR3pocQMpsc";
    private static final String gatewayUrl = "https://ncloud.apigw.ntruss.com";

    private final SimpleDateFormat simpleDateFormat = new SimpleDateFormat ( "yyyy-MM-dd'T'HH:mm:ssZ");
    private final Resource resource = new ClassPathResource("authkey.txt");


    public CreateServerInstancesResponse createServerInstances(int serverCreateCount) throws URISyntaxException {
        URIBuilder uriBuilder = new URIBuilder();
        String queryUrl = uriBuilder.setScheme(null).setHost(null)
                .setPath("/server/v2/createServerInstances")
                .setParameter("serverImageProductCode", "SPSW0LINUX000075")
                .setParameter("serverProductCode", "SPSVRSSD00000003")
                .setParameter("serverCreateCount", String.valueOf(serverCreateCount))
                .setParameter("responseFormatType", "json")
                .build().toString();
        return restTemplate.exchange(URI.create(gatewayUrl + queryUrl), HttpMethod.GET, setHttpHeaders(queryUrl), CreateServerInstancesResponseDto.class).getBody().getCreateServerInstancesResponse();
    }


    private HttpEntity<String> setHttpHeaders(String queryUrl) {
        String timestamp = String.valueOf(System.currentTimeMillis());
        String signature = makeSignature(queryUrl, timestamp);

        HttpHeaders httpheaders = new HttpHeaders();
        httpheaders.set("x-ncp-apigw-timestamp", timestamp);
        httpheaders.set("x-ncp-iam-access-key", accessKey);
        httpheaders.set("x-ncp-apigw-signature-v2", signature);

        return new HttpEntity<>(httpheaders);
    }

}