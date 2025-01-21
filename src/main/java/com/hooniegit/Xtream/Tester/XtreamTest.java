package com.hooniegit.Xtream.Tester;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hooniegit.Xtream.Stream.Stream;
import com.hooniegit.Xtream.Stream.DataClass.Sample;

import jakarta.annotation.PostConstruct;

/**
 * Xtream 아키텍처를 테스트하는 서비스 클래스입니다.
 */

@Service
public class XtreamTest {

    private final Stream<Sample> stream;

    @Autowired
    public XtreamTest(Stream<Sample> stream) {
        this.stream = stream;
    }

    @PostConstruct
    private void demo() {

        long start = System.nanoTime();

        for (int i=0; i<=400000; i++) {
            Sample sample = new Sample(i, "Demo Test is Running..");
            this.stream.publishInitialEvent(sample);
        }

        long end = System.nanoTime();

        long spent = end - start;
        long spentMillis = spent / 1_000_000;
        long spentSeconds = spentMillis / 1000;

        System.out.println(">> " + spent + "[ns] : " + spentMillis + "[ms] : " + spentSeconds + "[s]");

    }

}
