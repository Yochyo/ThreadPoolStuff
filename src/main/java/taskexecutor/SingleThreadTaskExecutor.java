package taskexecutor;

import taskexecutor.ATaskExecutor;

public class SingleThreadTaskExecutor extends ATaskExecutor {
	@Override
	public long executeTask(int repetitions) {
		long total = 0;
		int i = 0;

		while (i++ < repetitions)
			total += doTask();
		return total;
	}
}
