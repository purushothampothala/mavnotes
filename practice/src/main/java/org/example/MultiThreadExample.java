package org.example;

class MyThread extends Thread {

    public MyThread(String name){
        super(name);
    }
  @Override
    public synchronized void  run(){
      for(int i=0;i<=5;i++){
          System.out.println(Thread.currentThread().getId()+" value: "+i);
      }
  }
}
//class MyThread2 implements Runnable{
//
//    @Override
//    public void run() {
//        for(int i=0;i<=10;i++){
//            System.out.println("running mythread 2 "+Thread.currentThread()+i);
//            try {
//                Thread.sleep(2000);
//            } catch (InterruptedException e) {
//                throw new RuntimeException(e);
//            }
//        }
//    }
//}
public class MultiThreadExample {
    public static void main(String args[]) throws InterruptedException {
       Thread myThread= new MyThread("mythread");

       myThread.start();
      // myThread.join();

        Thread myThread3= new MyThread("mythread3");

        myThread3.start();

//       Thread myThread2= new Thread(new MyThread2());
//       myThread2.start();
    }
}
