package RateLimiter.SlidingWindow;

import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;

import RateLimiter.RateLimiter;

public class SlidingWindow implements RateLimiter{
    Queue<Long> slidingWindow;
    private int timeWindowInSeconds;
    private int capacity;

    public SlidingWindow(int timeWindowInSeconds, int capacity){
        this.slidingWindow = new ConcurrentLinkedQueue<>();
        this.capacity = capacity;
        this.timeWindowInSeconds = timeWindowInSeconds;
    }
    
    @Override
    public boolean grantAccess() {
        long currentTime = System.currentTimeMillis();
        checkAndUpdateQueue(currentTime);
        if(this.slidingWindow.size()<capacity){
            this.slidingWindow.offer(currentTime);
            return true;
        }
        return false;
    }

    private void checkAndUpdateQueue(long currentTime){
        if(this.slidingWindow.isEmpty()) return;
        long calculatedTime = (currentTime - this.slidingWindow.peek())/1000;
        while(calculatedTime >= this.timeWindowInSeconds){
            this.slidingWindow.poll();
            if(this.slidingWindow.isEmpty()) break;
            calculatedTime = (currentTime - this.slidingWindow.peek())/1000;
        }
    }
    
}
