package taskexecutor;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Random;

public abstract class ATaskExecutor {
	private static final Random random = new Random();

	public abstract long executeTask(int repetitions);

	public long doTask(){
		long time = System.currentTimeMillis();
		try {

			for(int i = 0; i< Integer.MAX_VALUE/10000;++i){
				random.nextInt();
			}

			//Thread.sleep(random.nextInt(50));
		} catch (Exception e) {
		}
		return System.currentTimeMillis()-time;
	}
}
