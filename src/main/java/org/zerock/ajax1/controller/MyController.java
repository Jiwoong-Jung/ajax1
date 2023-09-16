package org.zerock.ajax1.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.zerock.ajax1.entity.Memo;
import org.zerock.ajax1.repository.MemoRepository;

import java.util.Optional;

@RestController
@Slf4j
public class MyController {

    @Autowired
    MemoRepository memoRepository;

    @GetMapping("/memo/{id}")
    public ResponseEntity<Optional<Memo>> getList(@PathVariable("id") Long id) {
        log.info("id: {}", id);
        return new ResponseEntity<>(memoRepository.findById(id), HttpStatus.OK);
    }

    @PostMapping("/input")
    public ResponseEntity<Long> register(@RequestBody Memo memo) {
        log.info("memo: {}",memo);
        memoRepository.save(memo);
        return new ResponseEntity<>(memo.getMno(), HttpStatus.OK);
    }

}
