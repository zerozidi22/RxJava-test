package rxJava.executor;

import org.reactivestreams.Subscriber;
import org.reactivestreams.Subscription;

import io.reactivex.Flowable;
import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;

public class MethodChain {

	public void makeFlowableAndReturnSubscribe() {
		Flowable<Integer> flowable = Flowable.range(1, 10).filter(data -> data % 2 == 0).map(data -> data * 100);

		flowable.subscribe(data -> System.out.println("data = " + data));
	}

	public void makeObservableWithDisposed() {

		Observable.create(new ObservableOnSubscribe<String>() {

			@Override
			public void subscribe(ObservableEmitter<String> e) throws Exception {
				// TODO Auto-generated method stub

				String[] datas = { "Hello, World!", "안녕, RxJava" };

				for (String data : datas) {
					System.out.println("data = " + data);

					if (e.isDisposed()) {
						return;
					}

					e.onNext(data);
				}

				e.onComplete();

			}

		});

	}

	public void violatedReactiveStreams() {

		Flowable.range(1, 3).
			subscribe(new Subscriber<Integer>() {

				@Override
				public void onSubscribe(Subscription s) {
					System.out.println("onSubscribe : start");
					s.request(Long.MAX_VALUE);
					System.out.println("onSubscribe: end");
					// TODO Auto-generated method stub
	
				}
	
				@Override
				public void onError(Throwable t) {
					// TODO Auto-generated method stub
					System.out.println("에러");
	
				}
	
				@Override
				public void onComplete() {
					// TODO Auto-generated method stub
					System.out.println("완료");
				}
	
				@Override
				public void onNext(Integer t) {
					// TODO Auto-generated method stub
					System.out.println(t);
				}

		});

	}

}
