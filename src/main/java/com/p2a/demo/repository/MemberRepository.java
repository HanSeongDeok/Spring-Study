package com.p2a.demo.repository;

import com.p2a.demo.model.Member;

import java.util.List;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicLong;
import java.util.concurrent.atomic.AtomicReference;

public interface MemberRepository {
    abstract List<AtomicReference<Member>> findAll();
    abstract Optional<AtomicReference<Member>> findIdByName(AtomicReference<String> name);
    abstract Optional<AtomicReference<Member>> findNameById(AtomicLong id);
    abstract AtomicReference<Member> save(AtomicReference<Member> member);
}
