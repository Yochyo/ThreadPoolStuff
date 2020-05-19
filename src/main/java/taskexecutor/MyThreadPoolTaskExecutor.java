package taskexecutor;

import mythreadpool.MyTask;
import mythreadpool.MyThreadPool;

public class MyThreadPoolTaskExecutor extends ATaskExecutor{
	@Override
	public long executeTask(int repetitions) {
		MyThreadPool pool = new MyThreadPool(Runtime.getRuntime().availableProcessors());
		long start = System.currentTimeMillis();
		MyTask<Long>[] tasks = new MyTask[repetitions];
		for(int i = 0; i<repetitions; ++i){
			tasks[i] = pool.addTask(this::doTask);
		}

		for(MyTask<Long> t : tasks){
			try {
				t.join();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		pool.close();
		return System.currentTimeMillis()-start;
	}

}
