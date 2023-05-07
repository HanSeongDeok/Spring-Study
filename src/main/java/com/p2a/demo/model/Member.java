package com.p2a.demo.model;

import com.p2a.demo.factory.MemberFactory;

import java.util.concurrent.atomic.AtomicLong;
import java.util.concurrent.atomic.AtomicReference;

public class Member {
    private AtomicLong id;
    private AtomicReference<String> name;

    public Member(AtomicReference<String> name){
        this.name = name;
    }

    public AtomicLong getId() {
        return id;
    }

    public void setId(AtomicLong id) {
        this.id = id;
    }

    public AtomicReference<String> getName() {
        return name;
    }

    public void setName(AtomicReference<String> name) {
        this.name = name;
    }
}
