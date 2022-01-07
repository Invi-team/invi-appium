package invi.http;

import invi.beans.DataBean;
import okhttp3.Response;

import java.io.IOException;

public interface ApiCaller {
    public Response call(DataBean bean) throws InterruptedException, IOException;
}
