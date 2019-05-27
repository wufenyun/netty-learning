/**
 * Package: com.app.concurrent
 * Description: 
 */
package com.learning.concurrent.blockqueue;

/**
 * Description:  
 * Date: 2017年8月4日 下午4:42:06
 * @author wufenyun 
 */
public interface BlockQueue<E> {
    
    void put(E e) throws InterruptedException;

    E take() throws InterruptedException;
}
