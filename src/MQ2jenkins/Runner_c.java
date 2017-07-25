package MQ2jenkins;

/**
 * Created by 85081 on 2017/7/25.
 */
public class Runner_c {
    public static void main(String[] args){
        String jenkinsURL=new String();
        Consumer c=new Consumer();
        c.consume(jenkinsURL);
    }
}
