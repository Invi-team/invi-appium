package invi.http;

import invi.beans.DataBean;

import java.io.IOException;
import java.net.http.HttpResponse;

public interface ApiCaller {
    public HttpResponse<String> call(DataBean bean) throws InterruptedException, IOException;
}
