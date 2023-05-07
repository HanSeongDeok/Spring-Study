package com.p2a.demo.repository;

import com.p2a.demo.model.Member;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.util.Assert;

import javax.print.attribute.standard.PresentationDirection;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicLong;
import java.util.concurrent.atomic.AtomicReference;
import java.util.stream.IntStream;

public class MemoryMemberRepositoryTest {
    MemoryMemberRepository repository = new MemoryMemberRepository();

    @AfterEach
    public void afterEach() {
        repository.clearStore();
    }

    @Test
    public void save(){
        AtomicReference<Member> member = new AtomicReference<>();
        AtomicReference<String> text_Spring1= new AtomicReference<>("Spring1");
        member.set(new Member(text_Spring1));
        repository.save(member);

        AtomicReference<Member> result = repository.findNameById(member.get().getId()).get();
        Assertions.assertThat(member).isEqualTo(result);
    }

    @Test
    public void findIdByName(){
        AtomicReference<Member> member1 = new AtomicReference<>();
        AtomicReference<String>test_Spring2 = new AtomicReference<>("Good");
        member1.set(new Member(test_Spring2));
        repository.save(member1);

        AtomicReference<Member> member2 = new AtomicReference<>();
        AtomicReference<String>test_Spring3 = new AtomicReference<>("Nice");
        member2.set(new Member(test_Spring3));
        repository.save(member2);

        AtomicReference<Member> result = repository.findIdByName(test_Spring2).orElseThrow(NullPointerException::new);
        System.out.println(result.get().getId());
        //Assertions.assertThat(result).isEqualTo(member1);
    }

    @Test
    public void findAll(){
        List<AtomicReference<Member>> test = new ArrayList<>();
        IntStream.range(1,12).forEach(i -> {
            AtomicReference<String> text_TEST = new AtomicReference<>("TEST" + i);
            AtomicReference<Member> member = new AtomicReference<>(new Member(text_TEST));
            test.add(member);
        });
        test.stream().forEach(member -> repository.save(member));

        List<AtomicReference<Member>> result = repository.findAll();
        Assertions.assertThat(result.size()).isEqualTo(11);
    }
}
