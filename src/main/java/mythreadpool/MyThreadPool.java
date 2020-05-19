package mythreadpool;

import java.util.concurrent.LinkedBlockingQueue;

public class MyThreadPool {
	private volatile boolean enabled = true;
	private final Thread[] threads;
	private final LinkedBlockingQueue<MyTask> queue = new LinkedBlockingQueue<>();


	public MyThreadPool(int size) {
		if(size < 1) throw new IndexOutOfBoundsException("parameter size if not in 1..Int.MAX_VALUE");
		threads = new Thread[size];
		for (int i = 0; i < size; ++i) {
			Thread thread = createThread();
			threads[i] = thread;
			thread.start();
		}
	}

	public <E> MyTask<E> addTask(MyRunnable<E> run) {
		MyTask<E> task = new MyTask<>(run);
		queue.add(task);
		return task;
	}

	public void close() {
		enabled = false;
		try {
			for (Thread t : threads)
				t.interrupt();
		} catch (Exception e) {
		}
	}

	private Thread createThread() {
		return new Thread(() -> {
			while (enabled) {
				try {
					queue.take().run();
				} catch (Exception e) {
				}
			}
		});
	}
}
