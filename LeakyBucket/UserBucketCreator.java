package RateLimiter.LeakyBucket;
import java.util.HashMap;
import java.util.Map;

public class UserBucketCreator {
    Map<Integer, LeakyBucket> bucket;
    public UserBucketCreator(int id){
        bucket = new HashMap<>();
        bucket.put(id, new LeakyBucket(10));
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
