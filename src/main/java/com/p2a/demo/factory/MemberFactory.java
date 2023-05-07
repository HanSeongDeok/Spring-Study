package com.p2a.demo.factory;

import com.p2a.demo.model.Member;

import java.util.concurrent.Semaphore;
import java.util.concurrent.atomic.AtomicLong;
import java.util.concurrent.atomic.AtomicReference;

public class MemberFactory {
   private Semaphore semaphore;
   private MemberFactory(int concurrentLimit){
      semaphore = new Semaphore(concurrentLimit);
   }

   /**
    * 싱글턴 패턴을 적용 해당 클래스의 인스턴스를 하나만 사용하도록 함
    * @param concurrentLimit
    * @return
    */
   public static MemberFactory getInstance(int concurrentLimit){
      return new MemberFactory(concurrentLimit);
   }

   /**
    * 세마포어 동기화 알고리즘 적용
    * @param name
    * @return
    * @throws InterruptedException
    */
   private Member createMember(AtomicReference<String> name)throws InterruptedException{
      semaphore.acquire();
      try{
         return new Member(name);
      } finally {
         semaphore.release();
      }
   }
}
