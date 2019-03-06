/* And the 3rd task is:
 PinPong на Java с помощью потоков. 
 ≈сть два потока, когда "м€ч" у первого потока, 
 второй поток спит, когда м€ч у второго потока 
 первый поток спит. 
 ќбеспечить "передачу" м€ча несколько раз 
 и корректно завершить программу.
 */
package dataArtsMultyThreading3;

public class Main {

	public static void main(String[] args) throws InterruptedException {
		PingPong a = new PingPong("ping");
        PingPong b = new PingPong("pong");
        a.start();
        b.start();
        try {
        b.wait();
        } catch (IllegalMonitorStateException ex) {}
        try {
            a.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
	}
}
class PingPong extends Thread  {
    private static final Object lock= new Object();
    private String toPrintOut;
    private int counter = 1;
    public PingPong(String s){
        this.toPrintOut = s;
    }
    public void run(){
        while (counter < 6){
            synchronized(lock){
                System.out.println(this.toPrintOut); 
                lock.notifyAll();
                try {
                    lock.wait();
                    if (this.toPrintOut.equals("ping")) 
                    		System.out.println(counter++);
                } catch (InterruptedException e) {}
            }
        }
    }
}