package domein;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.value.ChangeListener;

public class TellerThread implements Runnable {
	
	private IntegerProperty teller = new SimpleIntegerProperty();
	private Lock accessLock = new ReentrantLock();
	private Condition kanDoorgaan = accessLock.newCondition();
	private boolean pauze = false;
	private boolean going = true;
	
	public void addObserver(ChangeListener<? super Number> listener) {
		teller.addListener(listener);
	}
	
	public void suspend() {
		accessLock.lock();
		try {
			pauze=true;
		} finally {
			accessLock.unlock();
		}
	}
	
	public void resume() {
		accessLock.lock();
		try {
			pauze=false;
			kanDoorgaan.signal();
		} finally {
			accessLock.unlock();
		}
	}
	
	public void stop() {
		accessLock.lock();
		try {
			going=false;
		} finally {
			accessLock.unlock();
		}
	}
	
	@Override
	public void run() {
		while (going) {
			teller.set(teller.get()+1);
			try {
				Thread.sleep(100);
			//TODO nagaan of er moet gepauzeerd worden
		    // en indien zo Thread in wait toestand brengen
				accessLock.lock();
				while(pauze) {
					kanDoorgaan.await();
				}
			} catch (InterruptedException e) {
				e.printStackTrace();
				Thread.currentThread().interrupt();
			} 
			finally {
				accessLock.unlock();
			}
							
		}
	}
}
