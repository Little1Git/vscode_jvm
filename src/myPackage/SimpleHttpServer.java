package myPackage;

import java.io.IOException;
import java.io.OutputStream;
import java.net.InetSocketAddress;

import com.sun.net.httpserver.HttpServer;
import com.sun.net.httpserver.HttpHandler;
import com.sun.net.httpserver.HttpExchange;

public class SimpleHttpServer {
    public static void main(String[] args) throws IOException {
        // 创建一个HttpServer实例，并绑定到指定的端口
        
        HttpServer server = HttpServer.create(new InetSocketAddress(8000), 0);

        // 设置根上下文的处理器
        server.createContext("/", new MyHandler());

        // 启动服务器
        server.setExecutor(null); // 使用默认的执行器
        server.start();
        
        System.out.println("Server is running on port 8000...");
    }

    // 自定义处理器，处理HTTP请求并发送响应
    static class MyHandler implements HttpHandler {
        @Override
        public void handle(HttpExchange exchange) throws IOException {
            // 获取请求方法
            String requestMethod = exchange.getRequestMethod();
            
            // 生成响应内容
            String response = "Hello, World! You requested: " + requestMethod;
            
            // 设置响应头
            exchange.sendResponseHeaders(200, response.getBytes().length);
            
            // 获取响应输出流并写入响应内容
            OutputStream os = exchange.getResponseBody();
            os.write(response.getBytes());
            os.close();
        }
    }

}
