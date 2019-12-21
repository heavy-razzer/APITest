package managers;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;
import org.json.JSONObject;

/*
HTTP commands manager
 */
public class HttpManager {

    public static JSONObject getResponse(String path) {

        try {
            HttpClient httpClient = HttpClientBuilder.create().build();
            HttpGet request = new HttpGet(path);
            HttpResponse response = httpClient.execute(request);
            if (response.getStatusLine().getStatusCode() != 200) {
                return null;
            }
            String json_string = EntityUtils.toString(response.getEntity());
            return new JSONObject(json_string);
        } catch (Exception e) {
            return null;
        }
    }
}
