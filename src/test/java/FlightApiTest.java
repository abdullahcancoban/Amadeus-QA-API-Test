import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.apache.http.Header;

public class FlightApiTest {

    public static void main(String[] args) {
        String apiUrl = "https://flights-api.buraky.workers.dev/";

        try (CloseableHttpClient httpClient = HttpClients.createDefault()) {
            // GET Request
            HttpGet request = new HttpGet(apiUrl);
            HttpResponse response = httpClient.execute(request);

            // Checking HTTP Status
            int statusCode = response.getStatusLine().getStatusCode();
            if (statusCode == 200) {
                System.out.println("HTTP Durum Kodu: 200 OK");
            } else {
                System.out.println("HTTP Durum Kodu: " + statusCode);
            }

            // Checking Content-Type Header
            Header contentTypeHeader = response.getFirstHeader("Content-Type");
            if (contentTypeHeader != null && "application/json".equals(contentTypeHeader.getValue())) {
                System.out.println("Content-Type: application/json");
            } else {
                System.out.println("Content-Type: " + (contentTypeHeader != null ? contentTypeHeader.getValue() : "BULUNAMADI"));
            }


            String responseBody = EntityUtils.toString(response.getEntity());
            System.out.println("Cevap: " + responseBody);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
