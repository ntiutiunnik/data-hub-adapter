package com.sandbox.lamadatahubadapter.config;

import org.apache.http.conn.ssl.NoopHostnameVerifier;
import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.ssl.TrustStrategy;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.web.client.RestTemplate;

import javax.net.ssl.SSLContext;
import java.security.KeyManagementException;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.cert.X509Certificate;

@Configuration
@PropertySource("classpath:lama_api.properties")
public class RestTemplateConfig {

  private final String basicAuthUsername;
  private final String basicAuthPassword;
  private final Logger logger = LoggerFactory.getLogger(RestTemplateConfig.class);

  public RestTemplateConfig(@Value("${lama.basic_auth_name}") String basicAuthUsername,
      @Value("${lama.basic_auth_password}") String basicAuthPassword) {

    this.basicAuthUsername = basicAuthUsername;
    this.basicAuthPassword = basicAuthPassword;
  }

  @Bean
  public RestTemplate basicRestTemplate() {

    return new RestTemplateBuilder()
        .basicAuthentication(basicAuthUsername, basicAuthPassword)
        .requestFactory(this::createIgnoreSSLRequestFactory)
        .build();
  }

  private HttpComponentsClientHttpRequestFactory createIgnoreSSLRequestFactory() {

    HttpComponentsClientHttpRequestFactory requestFactory = new HttpComponentsClientHttpRequestFactory();

    try {
      TrustStrategy acceptAllTrustStrategy = (X509Certificate[] chain, String authType) -> true;

      SSLContext sslContext = org.apache.http.ssl.SSLContexts.custom()
          .loadTrustMaterial(null, acceptAllTrustStrategy)
          .build();

      SSLConnectionSocketFactory connectionSocketFactory = new SSLConnectionSocketFactory(sslContext,
          new NoopHostnameVerifier());

      CloseableHttpClient httpClient = HttpClients.custom()
          .setSSLSocketFactory(connectionSocketFactory)
          .build();

      requestFactory.setHttpClient(httpClient);

    } catch (NoSuchAlgorithmException | KeyManagementException | KeyStoreException e) {
      logger.warn("RestTemplate configuration not bypasses self signed ssl certificates", e);
    }

    return requestFactory;
  }
}
