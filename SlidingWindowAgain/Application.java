package RateLimiter.SlidingWindowAgain;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

class Application{
	public static void main(String []args){
		UserBucketCreator userBucketCreator = new UserBucketCreator(1);
		int maxThreads = 12;
		ExecutorService executors = Executors.newFixedThreadPool(maxThreads);
		for(int i=0;i<maxThreads;i++){
			executors.execute(()->userBucketCreator.accessApplication(1));
		}
	}
}