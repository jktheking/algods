package edu.ds.queue;

public class App {
	public static void main(String[] args) {
				
				//queue(new BoundedArrayQueue<>(5));
				//queue(new UnBoundedArrayQueue<>(10));
				//queue(new UnBoundedLinkedListQueue<>());
				
				deque(new UnBoundedArrayDeque<>(10));
	}
	
	public static void queue( Queue<Integer> q) {
        q.offer(10);
        q.offer(20);
        q.offer(30);
        q.offer(40);
        q.offer(50);
        System.out.println("original queue: "+ q + " size:"+q.size() );
        System.out.println("peek:"+q.peek());
        System.out.println("--polling: "+  q.poll());
       
        System.out.println("--offer 100--");
        q.offer(100);
        System.out.println("queue post offer:"+q + " size:"+q.size());
      
        System.out.println("--polled twice--:"+  q.poll() + ","+  q.poll());
        q.offer(200);
        System.out.println("--offer 200--");
        System.out.println("queue post offer:"+q + " size:"+q.size());
        System.out.println("peek:"+q.peek());
       
        System.out.println("--polled twice--:"+  q.poll() + ","+  q.poll());
        
        System.out.println("queue post poll:"+q + " size:"+q.size());
        System.out.println("peek:"+q.peek());
       
        System.out.println("--offer 300 and 400--");
        q.offer(300);
        q.offer(400);
        System.out.println("queue post offer:"+q + " size:"+q.size());
        System.out.println("isEmpty:"+q.isEmpty());
        
    
        System.out.println("--polled 4 times:"+ q.poll()+","+q.poll() + ","+ q.poll()+","+q.poll());
        System.out.println("queue post polled:"+q + " size:"+q.size());
        
        System.out.println("isEmpty:"+q.isEmpty());
        System.out.println("peek:"+q.peek());
    }
	
	
	public static void deque(Deque<Integer> q) {
        q.offer(10);
        q.offer(20);
        q.offer(30);
        q.offer(40);
        q.offer(50);
        System.out.println("original queue: "+ q + " size:"+q.size() );
        System.out.println("peek:"+q.peek());
        System.out.println("--polling: "+  q.poll());
       
        System.out.println("--offer 100--");
        q.offer(100);
        System.out.println("queue post offer:"+q + " size:"+q.size());
      
        System.out.println("--polled twice--:"+  q.poll() + ","+  q.poll());
        q.offer(200);
        System.out.println("--offer 200--");
        System.out.println("queue post offer:"+q + " size:"+q.size());
        System.out.println("peek:"+q.peek());
       
        System.out.println("--polled twice--:"+  q.poll() + ","+  q.poll());
        
        System.out.println("queue post poll:"+q + " size:"+q.size());
        System.out.println("peek:"+q.peek());
       
        System.out.println("--offer 300 and 400--");
        q.offer(300);
        q.offer(400);
        System.out.println("queue post offer:"+q + " size:"+q.size());
        System.out.println("isEmpty:"+q.isEmpty());
        
    
        System.out.println("--polled 4 times:"+ q.poll()+","+q.poll() + ","+ q.poll()+","+q.poll());
        System.out.println("queue post polled:"+q + " size:"+q.size());
        
        System.out.println("isEmpty:"+q.isEmpty());
        System.out.println("peek:"+q.peek());
        
        System.out.println("--offerLast 10 and 20--");
        q.offerLast(10);
        q.offerLast(20);
        System.out.println("queue post offer:"+q + " size:"+q.size());
        System.out.println("peek last:" + q.peekLast());
        System.out.println("--polling Last: " + q.pollLast());
        System.out.println("size: " + q.size());
       
        System.out.println("--offerFirst 700 and offerLast 200--");
        q.offerFirst(700);
        q.offerLast(200);
        System.out.println("queue post offer:" + q);
        System.out.println("peekFirst:" + q.peekFirst());
        System.out.println("--polling First: " + q.pollFirst());
        System.out.println("size: " + q.size());
    }
	
	
}
