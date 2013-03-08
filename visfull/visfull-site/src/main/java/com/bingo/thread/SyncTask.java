/**
 * 系统项目名称
 * com.bingo.thread
 * SyncTask.java
 * 
 * 2012-3-15-上午11:09:49
 *  2012XX公司-版权所有
 * 
 */
package com.bingo.thread;
/**
 *@author Administrator
 *2012-3-15 上午11:09:49
 *
 */
/**
 * 
 * SyncTask
 * 
 * Administrator
 * 2012-3-15 上午11:09:49
 * 
 * @version 1.0.0
 * 
 */
public class SyncTask implements Runnable {

    /* (non-Javadoc)
     * @see java.lang.Runnable#run()
     */
    public void run() {
        for (int i = 0; i < 10; i++) {
            System.out.println("task is dealing ...!");
            try {
                Thread.sleep(10000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        
    }

}
