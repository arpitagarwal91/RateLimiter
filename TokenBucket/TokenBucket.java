package RateLimiter.TokenBucket;

import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicLong;

import RateLimiter.RateLimiter;

public class TokenBucket implements RateLimiter {
    private int capacity;
    private int refreshRate;
    private AtomicInteger currentCapacity;
    private AtomicLong lastUpdateTime;

    public TokenBucket(int capacity, int refreshRate){
        this.capacity = capacity;
        this.refreshRate = refreshRate;
        this.currentCapacity = new AtomicInteger(capacity);
        this.lastUpdateTime = new AtomicLong(System.currentTimeMillis());
    }

    @Override
    public boolean grantAccess() {
        refreshBucket();
        if(this.currentCapacity.get()>0){
            this.currentCapacity.decrementAndGet();
            return true;
        }
        return false;
    }

    public void refreshBucket(){
        int additionalTokens = (int)((System.currentTimeMillis()-this.lastUpdateTime.get())/1000)*refreshRate;
        int currCapacity = Math.min(this.currentCapacity.get()+additionalTokens, this.capacity);
        this.currentCapacity.getAndSet(currCapacity);
        this.lastUpdateTime.getAndSet(System.currentTimeMillis());
    }
}
