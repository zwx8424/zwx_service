package com.zwx.demo.asyn;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.Future;

import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.AsyncResult;
import org.springframework.stereotype.Component;

@Component
public class AsyncDemo {

	@Async
	public void asyncInvokeSimplest() {
		try {

			Thread.sleep(1000 * 2);
			String u = "name1";
			String u2 = "name2";
			List<String> list1 = Arrays.asList(u, u2);
			list1.forEach(x -> {
				System.out.println(x); //输出每个元素的长度
				});

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Async
	public void asyncInvokeWithParameter(String s) {
		System.out.println("asyncInvokeWithParameter, parementer={}");
	}

	/**
	 * 异常调用返回Future
	 * 
	 * @param i
	 * @return
	 */
	@Async
	public Future<String> asyncInvokeReturnFuture(int i) {
		System.out.println("asyncInvokeWithParameter, parementer={}");
		Future<String> future;
		try {
			Thread.sleep(1000 * 1);
			future = new AsyncResult<String>("success:" + i);
		} catch (InterruptedException e) {
			future = new AsyncResult<String>("error");
		}
		return future;
	}
}
