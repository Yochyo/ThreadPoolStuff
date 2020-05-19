package taskexecutor;

public class NThreadsTaskExecutor extends ATaskExecutor {
	final int n;

	public NThreadsTaskExecutor(int n) {
		this.n = n;
	}

	@Override
	public long executeTask(int repetitions) {
		long start = System.currentTimeMillis();
		int part = repetitions / n;

		Thread[] threads = new Thread[n - 1];
		try {
			for (int i = 0; i < n - 1; ++i) {
				threads[i] = new Thread(() -> {
					int i2 = 0;
					while (i2++ < part)
						doTask();
				});
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		for (int i = 0; i < part; ++i)
			doTask();

		for (Thread t : threads) {
			try {
				t.join();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		return System.currentTimeMillis() - start;
	}
}
