package RateLimiter.SlidingWindow;
import java.util.HashMap;
import java.util.Map;

public class UserBucketCreator {
    Map<Integer, SlidingWindow> bucket;
    public UserBucketCreator(int id){
        bucket = new HashMap<>();
        bucket.put(id, new SlidingWindow(1, 5 ));
    }

    public void accessApplication(int id){
        if(bucket.get(id).grantAccess()){
            System.out.println(Thread.currentThread().getName()+ " Hurray!! Able to access");
        }
        else{
            System.out.println(Thread.currentThread().getName()+ " :( Request code - 429 Too Many Requests!!");
        }
    }
}
