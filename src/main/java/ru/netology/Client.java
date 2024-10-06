package ru.netology;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.http.HttpHeaders;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.entity.ContentType;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;

import static ru.netology.Main.*;

public class Client {
    private final CloseableHttpClient httpClient;
    private CloseableHttpResponse response;
    private HttpGet request;

    public Client() {
        httpClient = HttpClientBuilder.create()
                .setDefaultRequestConfig(RequestConfig.custom()
                        .setConnectTimeout(5000)
                        .setSocketTimeout(30000)
                        .setRedirectsEnabled(false)
                        .build())
                .build();
    }

    public Post sendRequestToGetClass(String remoteServer) throws IOException {
        request = new HttpGet(remoteServer);
        request.setHeader(HttpHeaders.ACCEPT, ContentType.APPLICATION_JSON.getMimeType());
        response = httpClient.execute(request);
        ObjectMapper objectMapper = new ObjectMapper();
        return objectMapper.readValue(response.getEntity().getContent(), Post.class);
    }

    public void sendRequestToGetFile(Post post) throws IOException, URISyntaxException {
        String url = post.hdurl();
        String fileName = url.substring(url.lastIndexOf('/'));
        String fileBaseName = fileName.substring(1);

        request = new HttpGet(url);
        request.setHeader(HttpHeaders.ACCEPT, ContentType.IMAGE_JPEG.getMimeType());
        response = httpClient.execute(request);

        BufferedImage img = ImageIO.read(new URI(url).toURL());
        ImageIO.write(img, "jpg", new File(fileBaseName));
    }
}