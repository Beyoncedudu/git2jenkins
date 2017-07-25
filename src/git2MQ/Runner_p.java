package git2MQ;

import MQ2jenkins.Consumer;

import java.io.IOException;

/**
 * Created by 85081 on 2017/7/24.
 */
public class Runner_p {
    public static void main(String[] args){
        try {
            gitReceiver gR = new gitReceiver();
            gR.hook();
            String jenkinsURL=new String();
            Consumer c=new Consumer();
            c.consume(jenkinsURL);
        }catch(IOException e){
            e.printStackTrace();
        }
    }
}
