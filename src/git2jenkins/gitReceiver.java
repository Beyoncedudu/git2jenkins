package git2jenkins;

import java.net.URL;
import java.net.URLConnection;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.BufferedReader;
import java.io.IOException;

/**
 * Created by 85081 on 2017/7/20.
 */
public class gitReceiver {
    public static void  p(String arg[]) {
        String str;
        try {
            URL url = new URL("http://www.sohu.com/");
            URLConnection uc = url.openConnection();
            InputStream is = uc.getInputStream();
            InputStreamReader isr = new InputStreamReader(is);
            BufferedReader br = new BufferedReader(isr);
            while((str = br.readLine()) != null)
                System.out.println(str);
            br.close();
        } catch(IOException e) {
            System.out.println(e);
        }
    }
}
