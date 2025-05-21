package RateLimiter.TokenBucket;

import java.util.HashMap;
import java.util.Map;

public class UserBucketCreator {
    Map<Integer, TokenBucket> bucket;
    public UserBucketCreator(int id){
        this.bucket = new HashMap<>();
        this.bucket.put(id, new TokenBucket(10, 10));
    }

    public void accessApplication(int id){
        if(this.bucket.get(id).grantAccess()){
            System.out.println(Thread.currentThread().getName() + " Hurray!! Able to access the application.");
        }
        else{
            System.out.println(Thread.currentThread().getName() + " :( Request Code - 429 Too many Requests!!");
        }
    }
}
