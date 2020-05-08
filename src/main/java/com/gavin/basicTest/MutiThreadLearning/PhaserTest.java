package com.gavin.basicTest.MutiThreadLearning;

import java.util.Random;
import java.util.concurrent.Phaser;
import java.util.concurrent.TimeUnit;

/**
 * 多阶段栅栏
 */
@SuppressWarnings("all")
public class PhaserTest {
    MarriagePhaser phaser = new MarriagePhaser();
    Random r = new Random();

    public static void main(String[] args) {
        new PhaserTest().startProgram();
    }
    public void startProgram(){
        phaser.bulkRegister(7);//放入7个
        for(int i=1;i<=5;i++){
            new Thread(new PersonRunnable("客人"+i)).start();
        }
        new Thread(new PersonRunnable("新娘")).start();
        new Thread(new PersonRunnable("新郎")).start();
    }
    private void millisSecondsSleep(int milli){
        try {
            TimeUnit.MILLISECONDS.sleep(milli);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
    class  PersonRunnable implements Runnable{
        String name;
        private PersonRunnable(String name){
            this.name=name;
        }
        private void arrive(){
            millisSecondsSleep(r.nextInt(2000));
            System.out.printf("%s 到达现场\n",name);
            phaser.arriveAndAwaitAdvance();
        }
        private void eat(){
            millisSecondsSleep(r.nextInt(2000));
            System.out.printf("%s 吃完\n",name);
            phaser.arriveAndAwaitAdvance();
        }
        private   void leave(){
            millisSecondsSleep(r.nextInt(2000));
            System.out.printf("%s 离开\n",name);
            phaser.arriveAndAwaitAdvance();
        }
        private void hug(){
            if(name.equals("新娘") || name.equals("新郎")){
                millisSecondsSleep(r.nextInt(2000));
                System.out.printf("%s 入洞房\n",name);
            }
            phaser.arriveAndAwaitAdvance();
        }

        @Override
        public void run() {
            arrive();
            eat();
            leave();
            hug();
        }
    }
    class MarriagePhaser extends Phaser {
        @Override
        protected  boolean onAdvance(int phase, int registeredParties) {

            return registeredParties == 0;
        }
    }
}