
package robertefry.jtoolkit.timing;

import java.util.ArrayList;
import java.util.List;

/**
 * A stopwatch.
 * 
 * @author Robert E Fry
 * @date 2 Dec 2018
 * @time 20:24:20
 *
 */
public class StopWatch {
	
	private final List< LapLog > laps = new ArrayList< LapLog >();
	private volatile long starttime = 0, finishtime = 0;
	private volatile boolean running = false;
	
	private long time() {
		return System.currentTimeMillis();
	}
	
	/**
	 * Start.
	 */
	public synchronized void start() {
		if ( running ) return;
		running = true;
		starttime = time();
	}
	
	/**
	 * Stop.
	 */
	public synchronized void stop() {
		running = false;
		finishtime = time();
	}
	
	/**
	 * Logs the current running duration.
	 */
	public synchronized void lap() {
		laps.add( new LapLog( starttime, finishtime ) );
	}
	
	/**
	 * Clears all lap logs.
	 */
	public synchronized void clear() {
		laps.clear();
	}
	
	/**
	 * Returns the list of laps.
	 * 
	 * @return the list of laps.
	 */
	public synchronized List< LapLog > getLaps() {
		return laps;
	}
	
	/**
	 * Is the stopwatch running?
	 * 
	 * @return true if the stopwatch is running else false.
	 */
	public synchronized boolean isRunning() {
		return running;
	}
	
	/**
	 * A class to contain lap information.
	 * 
	 * @author Robert E Fry
	 * @date 2 Dec 2018
	 * @time 20:28:42
	 *
	 */
	public final class LapLog {
		
		private final long starttime, finishtime;
		
		public LapLog( long start, long finish ) {
			starttime = start;
			finishtime = finish;
		}
		
		/**
		 * Retruns the time the stopwatch was started for this log.
		 * 
		 * @return the time the stopwatch was started for this log.
		 */
		public synchronized long getStartTime() {
			return starttime;
		}
		
		/**
		 * Retruns the time the stopwatch was stopped or lapped for this log.
		 * 
		 * @return the time the stopwatch was stopped or lapped for this log.
		 */
		public synchronized long getFinishTime() {
			return finishtime;
		}
		
		/**
		 * Retruns the time the stopwatch was running for this log.
		 * 
		 * @return the time the stopwatch was running for this log.
		 */
		public synchronized long getRunningTime() {
			return finishtime - starttime;
		}
		
	}
	
}
