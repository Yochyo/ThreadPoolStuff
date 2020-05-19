import taskexecutor.*;


public class Main {
	public static void main(String[] args) {
		SingleThreadTaskExecutor singleThreadTaskExecutor = new SingleThreadTaskExecutor();
		NThreadsTaskExecutor nThreadsTaskExecutor = new NThreadsTaskExecutor(Runtime.getRuntime().availableProcessors());
		MultiThreadTaskExecutor multiThreadTaskExecutor = new MultiThreadTaskExecutor();
		MyThreadPoolTaskExecutor myThreadPoolTaskExecutor = new MyThreadPoolTaskExecutor();
		ThreadPoolTaskExecutor threadPoolTaskExecutor = new ThreadPoolTaskExecutor();

		//Erledigt die aufgaben nacheinander im Single Thread
		System.out.println(singleThreadTaskExecutor.executeTask(100)); //ca 300ms

		//Erstellt n Threads (n = Anzahl von CPU Kernen) und teilt die Aufgabe auf
		System.out.println(nThreadsTaskExecutor.executeTask(100)); //ca 50ms

		//Erstellt n (100) Threads um löst jede Aufgabe in einem einzelnen Thread
		System.out.println(multiThreadTaskExecutor.executeTask(100)); //ca 2600ms

		//Die Aufgaben werden an den selbst erstellten Threadpool aufgegeben
		System.out.println(myThreadPoolTaskExecutor.executeTask(100)); //ca 2500ms

		//Die Aufgaben werden an den Java Threadpool aufgegeben
		System.out.println(threadPoolTaskExecutor.executeTask(100)); //cs 2500mm
	}

	private static long startEmptyThreads(int n) {
		long t = System.currentTimeMillis();
		for (int i = 0; i < n; ++i) {
			new Thread(() -> {
			}).start();
		}
		return System.currentTimeMillis() - t;
	}
}
