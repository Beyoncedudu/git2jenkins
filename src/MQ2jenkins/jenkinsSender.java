package MQ2jenkins;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.URL;
import java.net.URLConnection;

/**
 * Created by 85081 on 2017/7/20.
 */
public class jenkinsSender {
		/**
		 * 向jenkins发送POST方法的请求
		 *
		 * @param url
		 *            发送请求的 URL
		 * @param json
		 *            请求参数。
		 * @return 所代表远程资源的响应结果
		 */
		public static String sendPost(String url, JSONObject json) {
			PrintWriter out = null;
			BufferedReader in = null;
			String result = "";
			try {
				URL realUrl = new URL(url);
				URLConnection conn = realUrl.openConnection();

				conn.addRequestProperty("X-Gitlab-Event:", "Push Hook");
				conn.setDoOutput(true);
				conn.setDoInput(true);
				conn.connect();
			} catch (Exception e) {
				e.printStackTrace();
			}
			finally{
				try{
					if(out!=null){
						out.close();
					}
					if(in!=null){
						in.close();
					}
				}
				catch(IOException ex){
					ex.printStackTrace();
				}
			}
			return result;
		}

}
