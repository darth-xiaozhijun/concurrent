/**
 * synchronized关键字
 * 锁对象。synchronized(this)和synchronized方法都是锁当前对象。
 */
package concurrent.t01;

import java.util.concurrent.TimeUnit;

public class Test_01 {
	private int count = 0;
	private Object o = new Object();
	
	//锁的是临界资源对象（多个线程都能访问、都能找到的对象）
	public void testSync1(){
		synchronized(o){
			System.out.println(Thread.currentThread().getName() 
					+ " count = " + count++);
		}
	}
	
	//锁的是当前对象
	public void testSync2(){
		synchronized(this){
			System.out.println(Thread.currentThread().getName() 
					+ " count = " + count++);
		}
	}
	
	//锁的是当前对象
	public synchronized void testSync3(){
		System.out.println(Thread.currentThread().getName() 
				+ " count = " + count++);
		try {
			TimeUnit.SECONDS.sleep(3);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		final Test_01 t = new Test_01();
		new Thread(new Runnable() {
			@Override
			public void run() {
				t.testSync3();
			}
		}).start();
		new Thread(new Runnable() {
			@Override
			public void run() {
				t.testSync3();
			}
		}).start();
	}
	
}
