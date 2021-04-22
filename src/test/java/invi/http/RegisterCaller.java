package invi.http;

import com.fasterxml.jackson.databind.ObjectMapper;
import invi.beans.DataBean;
import invi.utils.Urls;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.logging.Logger;

public class RegisterCaller implements ApiCaller {
    private final Logger LOGGER = Logger.getLogger(RegisterCaller.class.getName());

    public HttpResponse call(DataBean registerBean) throws InterruptedException, IOException {
        Urls urls = new Urls();
        String requestBody = new ObjectMapper().writeValueAsString(registerBean);

        HttpClient client = HttpClient.newBuilder()
                .version(HttpClient.Version.HTTP_2)
                .build();
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(urls.register()))
                .header("Content-Type", "application/json")
                .POST(HttpRequest.BodyPublishers.ofString(requestBody))
                .build();
        HttpResponse<String> response =
                client.send(request, HttpResponse.BodyHandlers.ofString());

        LOGGER.info(this.getClass().getName() + "user register status code: " + response.statusCode());

        return response;
    }
}
