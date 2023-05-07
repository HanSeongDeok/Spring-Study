package com.p2a.demo.repository;

import com.p2a.demo.model.Member;
import org.springframework.stereotype.Repository;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;
import java.util.concurrent.atomic.AtomicReference;

public class MemoryMemberRepository implements MemberRepository{
    private ConcurrentHashMap<AtomicLong, AtomicReference<Member>> store =new ConcurrentHashMap<>();
    private static AtomicLong sequence = new AtomicLong();


    @Override
    public AtomicReference<Member> save(AtomicReference<Member> member) {
        member.get().setId(new AtomicLong(sequence.incrementAndGet()));
        store.put(member.get().getId(), member);
        return member;
    }

    // 이름이 중복이 없다는 가정하의 로직
    @Override
    public Optional<AtomicReference<Member>> findIdByName(AtomicReference<String> name) {
        return store.values().stream()
                .filter(v2->v2.get().getName().get().equals(name.get()))
                .findAny();
    }

    @Override
    public Optional<AtomicReference<Member>> findNameById(AtomicLong id) {
        Optional.ofNullable(store.get(id))
                .orElseThrow(()->new NullPointerException("Empty Store List"));
        return Optional.ofNullable(store.get(id));
    }
    @Override
    public List<AtomicReference<Member>> findAll() {
        return new ArrayList<>(store.values());
    }

    public void clearStore(){
        store.clear();
    }
}
