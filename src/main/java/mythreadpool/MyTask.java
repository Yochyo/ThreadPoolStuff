package mythreadpool;

public class MyTask<E> {
	private volatile Boolean locked = true;

	private Exception e = null;

	private E result = null;
	private final MyRunnable<E> run;

	public MyTask(MyRunnable<E> run) {
		this.run = run;
	}

	public synchronized void run() {
		try {
			try {
				result = run.run();
			} catch (Exception e) {
				this.e = e;
			}
			locked = false;
			notifyAll();
		} catch (IllegalMonitorStateException e) {
			e.printStackTrace();
		}

	}

	public synchronized E join() throws Exception {
		while (locked) {
			try {
				wait();
			} catch (InterruptedException e) {
				e.printStackTrace();
				Thread.currentThread().interrupt();
			}
		}
		if (e != null) throw e;
		else return result;
	}
}
