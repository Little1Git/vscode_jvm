// package http_client;

// import java.io.IOException;
// import java.io.OutputStream;
// import java.net.InetSocketAddress;

// public class SimpleHttpServer {
//     public static void main(String[] args) throws IOException {
//         // 创建 HttpServer 并绑定到端口8000
//         HttpServer server = HttpServer.create(new InetSocketAddress(8000), 0);
        
//         // 创建上下文，并设置处理程序
//         server.createContext("/test", new MyHandler());
        
//         // 启动服务器
//         server.setExecutor(null); // 默认执行器
//         server.start();
        
//         System.out.println("Server is listening on port 8000");
//     }
    
//     static class MyHandler implements HttpHandler {
//         @Override
//         public void handle(HttpExchange exchange) throws IOException {
//             String response = "Hello, this is the response from the server!";
//             exchange.sendResponseHeaders(200, response.length());
//             OutputStream os = exchange.getResponseBody();
//             os.write(response.getBytes());
//             os.close();
//         }
//     }
// }
