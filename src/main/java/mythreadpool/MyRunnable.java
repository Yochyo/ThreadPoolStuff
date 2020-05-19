package mythreadpool;

public interface MyRunnable<E> {
	E run() throws InterruptedException;
}
