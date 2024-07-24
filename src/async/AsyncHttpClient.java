package async;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpRequest.BodyPublishers;
import java.net.http.HttpResponse;
import java.net.http.HttpResponse.BodyHandlers;
import java.util.concurrent.CompletableFuture;

public class AsyncHttpClient {
    public static void main(String[] args) {
        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create("http://localhost:8080/data"))
                .POST(BodyPublishers.ofString("Hello, Server!"))
                .build();

        System.out.println("something else .........");

        CompletableFuture<HttpResponse<String>> response = client.sendAsync(request, BodyHandlers.ofString());

        response.thenApply(HttpResponse::body)
                .thenAccept(System.out::println)
                .join();
    }

}
