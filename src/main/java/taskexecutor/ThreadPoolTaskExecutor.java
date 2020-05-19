package taskexecutor;

import mythreadpool.MyTask;
import mythreadpool.MyThreadPool;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.ThreadPoolExecutor;

public class ThreadPoolTaskExecutor extends ATaskExecutor{
	@Override
	public long executeTask(int repetitions) {
		ThreadPoolExecutor pool = (ThreadPoolExecutor) Executors.newFixedThreadPool(Runtime.getRuntime().availableProcessors());
		long start = System.currentTimeMillis();

		Future<Long>[] tasks = new Future[repetitions];
		for(int i = 0; i<repetitions; ++i){
			tasks[i] = pool.submit(this::doTask);
		}

		for(int i = 0; i<repetitions; ++i){
			try {
				tasks[i].get();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

		pool.shutdown();
		return System.currentTimeMillis()-start;
	}
}
