
import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class BuscaMoeda {

    public String converte(String moeda1, String moeda2) throws IOException, InterruptedException {

    String chave = "API_KEY";

        String url = "https://v6.exchangerate-api.com/v6/" + chave +
                "/pair/" + moeda1 + "/" + moeda2;

        HttpClient client = HttpClient.newHttpClient();

        HttpRequest request = HttpRequest
                .newBuilder()
                .uri(URI.create(url))
                .build();

        try {
            HttpResponse<String> response = client
                    .send(request, HttpResponse.BodyHandlers.ofString());
            return response.body();
        } catch (Exception e) {
            throw new RuntimeException("Não consegui obter os dados da API.");
        }
    }
}