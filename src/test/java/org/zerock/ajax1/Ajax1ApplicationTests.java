package org.zerock.ajax1;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.test.annotation.Commit;
import org.springframework.transaction.annotation.Transactional;
import org.zerock.ajax1.entity.Memo;
import org.zerock.ajax1.repository.MemoRepository;

import java.util.List;
import java.util.Optional;
import java.util.stream.IntStream;

@SpringBootTest
class Ajax1ApplicationTests {

    @Autowired
    MemoRepository memoRepository;

    @Test
    void contextLoads() {
        System.out.println("테스트 공간");
    }
    @Test
    public void testInsertDummies() {
        IntStream.rangeClosed(1,100).forEach(i-> {
            Memo memo = Memo.builder()
                    .memoText("샘플자료..." + i)
                    .build();
            memoRepository.save(memo);
        });
    }

    @Test
    public void testSelect() {
        Long mno = 101L;
        Optional<Memo> result = memoRepository.findById(mno);
        System.out.println("=====================");
        if (result.isPresent()) {
            Memo memo = result.get();
            System.out.println(memo);
        }
    }

    @Test
    public void testUpdate() {
        Memo memo = Memo.builder()
                .mno(100L)
                .memoText("Udate Text").
                build();
        memoRepository.save(memo);
    }

    @Test
    public void testDelete() {
        Long mno = 100L;
        memoRepository.deleteById(mno);
    }

    @Test
    public void testPageDefault() {
        Sort sort1 = Sort.by("mno").descending();
        Sort sort2 = Sort.by("memoText").ascending();
        Sort sortAll = sort1.and(sort2);

        Pageable pageable = PageRequest.of(0, 10, sortAll);
        Page<Memo> result = memoRepository.findAll(pageable);
        System.out.println(result);
        System.out.println("-------------------------");
        System.out.println("Total page: "+result.getTotalPages());
        System.out.println("Total Count: "+result.getTotalElements());
        System.out.println("Page Number : "+result.getNumber());
        System.out.println("Page Size : "+result.getSize());
        System.out.println("has next page?: "+result.hasNext());
        System.out.println("first page?: "+result.isFirst());
        System.out.println("-----------------------------");
        for (Memo memo : result.getContent()) {
            System.out.println(memo);

        }
    }

    @Test
    public void testQueryMethods() {
        List<Memo> list
                = memoRepository.findByMnoBetweenOrderByMnoDesc(70L, 80L);
        for (Memo memo : list) {
            System.out.println(memo);
        }
    }

    @Test
    public void testQueryMethodWithPagable() {
        Pageable pageable
                = PageRequest.of(0, 10, Sort.by("mno")
                                                            .descending());
        Page<Memo> result = memoRepository
                            .findByMnoBetween(10L, 50L, pageable);
        //result.get().forEach(memo-> System.out.println(memo));
        for (Memo memo : result.getContent()) {
            System.out.println(memo);
        }
    }

    @Test
    public void testShotCut() {
//        shift-enter (아래로 새 줄 시작)
        Memo memo = new Memo();//ctrl-shift-enter (세미콜론)
        //ctrl-alt-v (변수 선언)
        System.out.println();
        System.out.println();
        System.out.println();
        System.out.println(); //sout

//ctrl-y (라인 삭제)
//ctrl-d (라인 복사)
//iter, itar
//ifn, inn

    }

    @Commit
    @Transactional
    @Test
    public void testDeleteQueryMethods() {
        memoRepository.deleteMemoByMnoLessThan(203L);
    }

    @Test
    public void testJPQL1() {
        List<Memo> list = memoRepository.getListDesc();
        list.stream().forEach(m-> System.out.println(m));
    }

    @Test
    public void testJPQL2() {
        memoRepository.updateMemoText(204L, "업데이트된 자료");
    }

}
