package com.tt.async;

import java.util.Random;
import java.util.UUID;

import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

@Service
public class AsyncTask {
    @Async
	public void f1() {
		System.out.println(Thread.currentThread().getName()+UUID.randomUUID().toString());
		 try {  
             Thread.sleep(new Random().nextInt(100));  
         } catch (InterruptedException e) {  
             e.printStackTrace();  
         }  
	}
    @Async  
    public void f2() {  
         System.out.println("f2 : " + Thread.currentThread().getName() + "   " + UUID.randomUUID().toString());  
         try {  
             Thread.sleep(100);  
         } catch (InterruptedException e) {  
             e.printStackTrace();  
         }  
    }  
}
