package RateLimiter.LeakyBucket;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

import RateLimiter.RateLimiter;

public class LeakyBucket implements RateLimiter{

    BlockingQueue<Integer> queue;
    public LeakyBucket(int capacity){
        queue = new LinkedBlockingQueue<Integer>(capacity);
    }
    
    @Override
    public boolean grantAccess() {
        if(queue.remainingCapacity()>0){
            queue.add(1);
            return true;
        }
        return false;
    }
}