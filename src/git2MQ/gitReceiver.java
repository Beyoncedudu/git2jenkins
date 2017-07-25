package git2MQ;

import java.io.*;
import java.net.InetSocketAddress;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import com.sun.net.httpserver.HttpServer;
import com.sun.net.httpserver.spi.HttpServerProvider;


/**
 * Created by 85081 on 2017/7/20.
 */
public class gitReceiver {
    public void hook() throws IOException {
        try {
            HttpServerProvider provider = HttpServerProvider.provider();
            HttpServer hs =provider.createHttpServer(new InetSocketAddress(6666), 100);
            hs.createContext("/myApp", new MyHttpHandler());
            hs.setExecutor(null);
            hs.start();
        } catch (Exception e) {

        }
    }

    class MyHttpHandler implements HttpHandler{
        public void handle(HttpExchange exchange) throws IOException{
            String json = new String();
            try {
                if (exchange.getRequestHeaders().equals("X-Gitlab-Event: Push Hook")) {
                    InputStream is = exchange.getRequestBody();
                    DataInputStream dis = new DataInputStream(is);
                    json = dis.readUTF();
                }
             } catch (Exception e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
            Producer producer = new Producer();
            producer.produce(json);
            exchange.close();
        }
    }
}
