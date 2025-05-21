package RateLimiter.SlidingWindowAgain;

import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;

import RateLimiter.RateLimiter;

class SlidingWindow implements RateLimiter{
	int timeInSeconds;
	int capacity;
	Queue<Long> queue;

	public SlidingWindow(int timeInSeconds, int capacity){
		this.timeInSeconds = timeInSeconds;
		this.capacity = capacity;
		this.queue = new ConcurrentLinkedQueue<>();
	}

	@Override
	public boolean grantAccess(){
		long currentTime = System.currentTimeMillis();
		updateAndCheckQueue(currentTime);
		if(this.queue.size()<capacity){
			this.queue.add(currentTime);
			return true;
		}
		return false;
	}

	private void updateAndCheckQueue(long currentTime){
		if(this.queue.isEmpty()){
			return;
		}
		int calculatedTime = (int)((currentTime - this.queue.peek())/1000);
		while(calculatedTime>=this.timeInSeconds){
			this.queue.poll();
			if(this.queue.isEmpty()) return;
			calculatedTime = (int)((currentTime - this.queue.peek())/1000);
		}
		
	}
}