package http_client;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class SimpleHttpGet {
    public static void main(String[] args) throws Exception {
        // 创建 HttpClient
        HttpClient client = HttpClient.newHttpClient();
        
        // 创建 HttpRequest
        HttpRequest request = HttpRequest.newBuilder()
                .uri(new URI("http://example.com"))
                .build();
        
        // 发送请求并接收响应
        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
        
        // 输出响应状态码和响应体
        System.out.println("Status Code: " + response.statusCode());
        System.out.println("Response Body: " + response.body());
    }
}
