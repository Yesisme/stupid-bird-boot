package com.epsoft.demo.thread;

public class SynchronizedTicketDemo {

    static int ticket =10;

      public void getTicket() {
          synchronized (SynchronizedTicketDemo.class) {
              if (ticket <= 0) {
                  return;
              }
              System.out.println(Thread.currentThread().getName() + "抢到一张票," + ticket);
              ticket--;
          }

      }

    public static void main(String[] args) {
        SynchronizedTicketDemo demo = new SynchronizedTicketDemo();
        for(int i=0;i<20;i++){
            new Thread(demo::getTicket).start();
        }
    }

}
