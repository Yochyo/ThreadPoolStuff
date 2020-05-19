package taskexecutor;

import java.util.concurrent.atomic.AtomicLong;

public class MultiThreadTaskExecutor extends ATaskExecutor {
	@Override
	public long executeTask(int repetitions) {
		long start = System.currentTimeMillis();
		Thread[] threads = new Thread[repetitions];

		for (int i = 0; i < repetitions; ++i) {
			Thread t = new Thread(this::doTask);
			threads[i] = t;
			t.start();
		}

		for (int i = 0; i < repetitions; ++i) {
			try {
				threads[i].join();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}

		return System.currentTimeMillis()-start;
	}
}
