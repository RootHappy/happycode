package com.wang.test;

import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CopyOnWriteArrayList;

public class ObservableSet<E> extends ForwardingSet<E> {

	public ObservableSet(Set<E> set) {
		super(set);
	}

//	 private final List<SetObserver<E>> observers = new ArrayList<SetObserver<E>>();

	private final List<SetObserver<E>> observers = new CopyOnWriteArrayList<SetObserver<E>>();

	 public void addObserver(SetObserver<E> observer) {
		 synchronized(observer) {
			 observers.add(observer);
		 }
	 }

	 public boolean removeObserver(SetObserver<E> observer) {
		 synchronized(observers) {
			 return observers.remove(observer);
		 }
	 }

	 private void notifyElementAdded(E element) {

//		 List<SetObserver<E>> snapshot = null;
//		 synchronized(observers) {
//			 snapshot = new ArrayList<SetObserver<E>>(observers);
//		 }
//		 for(SetObserver<E> observer : snapshot)
//			 observer.added(this, element);

		 synchronized (observers) {
			 for(SetObserver<E> observer : observers)
				 observer.added(this, element);
		}
	 }

	 @Override
	 public boolean add(E element) {
		 boolean added = super.add(element);
		 if(added)
			 notifyElementAdded(element);
		 return added;
	 }

	 @Override
	 public boolean addAll(Collection<? extends E> c) {
		 boolean result = false;
		 for(E element : c)
			 result |= add(element);
		 return result;
	 }


	 public static void main(String[] args) {
		 ObservableSet<Integer> set = new ObservableSet<Integer>(new HashSet<Integer>());

		 set.addObserver(new SetObserver<Integer>() {

			@Override
			public void added(final ObservableSet<Integer> set, Integer element) {
				if(element == 23) {
//					ExecutorService executor = Executors.newSingleThreadExecutor();
//					final SetObserver<Integer> observer = this;
//					try {
//						executor.submit(new Runnable(){
//							public void run() {
//								set.removeObserver(observer);
//							}
//						}).get();
//					} catch (InterruptedException e) {
//						throw new AssertionError(e.getCause());
//					} catch (ExecutionException e) {
//						throw new AssertionError(e.getCause());
//					}finally {
//						executor.shutdown();
//					}
					set.removeObserver(this);
				}
				System.out.println(element);
			}
		});

		 for(int i = 0; i < 100; i++) {
			 set.add(i);
		 }
	 }


}
