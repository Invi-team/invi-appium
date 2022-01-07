package invi.http;

import com.fasterxml.jackson.databind.ObjectMapper;
import invi.beans.DataBean;
import invi.utils.Urls;
import okhttp3.*;

import java.io.IOException;

import java.util.logging.Logger;

public class RegisterCaller implements ApiCaller {
    private static final Logger LOGGER = Logger.getLogger(RegisterCaller.class.getName());

    public Response call(DataBean registerBean) throws InterruptedException, IOException {
        String requestBody = new ObjectMapper().writeValueAsString(registerBean);
        OkHttpClient client = new OkHttpClient();

        Request request = new Request.Builder()
                .url(Urls.register())
                .post(RequestBody.create(MediaType.parse("application/json"), requestBody))
                .build();

        Response response = client.newCall(request).execute();
        LOGGER.info(this.getClass().getName() + " user register status code: " + response.code());

        return response;
    }
}
