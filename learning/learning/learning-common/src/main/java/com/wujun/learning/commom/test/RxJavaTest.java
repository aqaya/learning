package com.wujun.learning.commom.test;

import rx.Observable;
import rx.Observer;
import rx.Subscriber;
import rx.functions.Action1;
import rx.subjects.AsyncSubject;
import rx.subjects.BehaviorSubject;
import rx.subjects.PublishSubject;
import rx.subjects.ReplaySubject;

public class RxJavaTest {
	public static void main(String[] args) {

		Observable<String> sender = Observable.just("just1", "just2");

		Observable.create(new Observable.OnSubscribe<String>() {

			@Override
			public void call(Subscriber<? super String> subscriber) {

				subscriber.onNext("create1"); // 发送数据"Hi，Weavey！"

				subscriber.onNext("create2"); // 发射一个"create2"的String
				subscriber.onCompleted();// 发射完成,这种方法需要手动调用onCompleted，才会回调Observer的onCompleted方法
			}
		});

		Observer<String> receiver1 = new Observer<String>() {

			@Override
			public void onCompleted() {
				System.out.println("onCompleted1");
			}

			@Override
			public void onError(Throwable e) {
				System.out.println("onError1");
			}

			@Override
			public void onNext(String s) {
				System.out.println("onNext1:" + s);
			}
		};

		Observer<String> receiver2 = new Observer<String>() {

			@Override
			public void onCompleted() {
				System.out.println("onCompleted2");
			}

			@Override
			public void onError(Throwable e) {
				System.out.println("onError2");
			}

			@Override
			public void onNext(String s) {
				System.out.println("onNext2:" + s);
			}
		};

		sender.subscribe(receiver1);
		sender.subscribe(receiver2);

		System.out.println("------------------输出分割线------------------");
		// ------------------demo分割线------------------

		AsyncSubject<String> asyncSubject = AsyncSubject.create();

		asyncSubject.onNext("asyncSubject1");
		asyncSubject.onNext("asyncSubject2");
		asyncSubject.onNext("asyncSubject3");

		asyncSubject.onCompleted();

		asyncSubject.subscribe(new Observer<String>() {
			@Override
			public void onCompleted() {
				System.out.println("asyncSubject onCompleted"); // 输出
			}

			@Override
			public void onError(Throwable e) {
				System.out.println("asyncSubject onError"); // 不输出（异常才会输出）
			}

			@Override
			public void onNext(String s) {
				System.out.println("asyncSubject:" + s); // 输出asyncSubject:asyncSubject3
			}
		});

		System.out.println("------------------输出分割线------------------");
		// ------------------demo分割线------------------

		BehaviorSubject<String> behaviorSubject = BehaviorSubject.create("default");
		behaviorSubject.onNext("behaviorSubject1");
		behaviorSubject.onNext("behaviorSubject2");
		behaviorSubject.subscribe(new Observer<String>() {
			@Override
			public void onCompleted() {
				System.out.println("behaviorSubject:complete");
			}

			@Override
			public void onError(Throwable e) {
				System.out.println("behaviorSubject:error");
			}

			@Override
			public void onNext(String s) {
				System.out.println("behaviorSubject:" + s);
			}
		});

		behaviorSubject.onNext("behaviorSubject3");
		behaviorSubject.onNext("behaviorSubject4");

		System.out.println("------------------输出分割线------------------");
		// ------------------demo分割线------------------

		PublishSubject<String> publishSubject = PublishSubject.create();
		publishSubject.onNext("publishSubject1");
		publishSubject.onNext("publishSubject2");
		publishSubject.subscribe(new Observer<String>() {
			@Override
			public void onCompleted() {

			}

			@Override
			public void onError(Throwable e) {

			}

			@Override
			public void onNext(String s) {
				System.out.println("publishSubject observer1:" + s);
			}
		});
		publishSubject.onNext("publishSubject3");
		publishSubject.onNext("publishSubject4");

		System.out.println("------------------输出分割线------------------");
		// ------------------demo分割线------------------

		ReplaySubject<String> replaySubject = ReplaySubject.create(); // 创建默认初始缓存容量大小为16的ReplaySubject，当数据条目超过16会重新分配内存空间，使用这种方式，不论ReplaySubject何时被订阅，Observer都能接收到数据
		// replaySubject =
		// ReplaySubject.create(100);//创建指定初始缓存容量大小为100的ReplaySubject
		// replaySubject = ReplaySubject.createWithSize(2);//只缓存订阅前最后发送的2条数据
		// replaySubject=ReplaySubject.createWithTime(1,TimeUnit.SECONDS,Schedulers.computation());
		// //replaySubject被订阅前的前1秒内发送的数据才能被接收
		replaySubject.onNext("replaySubject:pre1");
		replaySubject.onNext("replaySubject:pre2");
		replaySubject.onNext("replaySubject:pre3");
		replaySubject.subscribe(new Action1<String>() {
			@Override
			public void call(String s) {
				System.out.println("replaySubject:" + s);
			}
		});
		replaySubject.onNext("replaySubject:after1");
		replaySubject.onNext("replaySubject:after2");

		System.out.println("------------------输出分割线------------------");
		// ------------------demo分割线------------------

		Observable<String> obs = Observable.create(new Observable.OnSubscribe<String>() {
			@Override
			public void call(Subscriber<? super String> subscriber) {

				subscriber.onNext("I'm Observable");
				subscriber.onCompleted();
			}
		});
		
		PublishSubject<String> publishSubject2 = PublishSubject.create();
		publishSubject2.onNext("as Observable");
		publishSubject2.onCompleted();
		
		obs.subscribe(publishSubject2);
		
		System.out.println("------------------输出分割线------------------");
		// ------------------demo分割线------------------
		Observable.just("Hello, world!").map((s) -> {return s + 1;}).subscribe((s)->{
			System.out.println(s);
		});
		
	}

}
