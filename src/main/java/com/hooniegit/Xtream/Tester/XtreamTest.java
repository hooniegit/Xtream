package com.hooniegit.Xtream.Tester;

import java.util.concurrent.CompletableFuture;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hooniegit.Xtream.Stream.StreamManager;
import com.hooniegit.Xtream.Stream.Configuration.DataClass.Sample;

import jakarta.annotation.PostConstruct;

/**
 * Xtream 아키텍처를 테스트하는 서비스 클래스입니다.
 */

@Service
public class XtreamTest {

    private final StreamManager<Sample> manager;

    @Autowired
    public XtreamTest(StreamManager<Sample> manager) {
        this.manager = manager;
    }

    @PostConstruct
    private void demo() {

        long start = System.nanoTime();

        for (int i=0; i<=4000000; i++) {
            CompletableFuture.runAsync(() -> {
                Sample sample = new Sample("Demo Test is Running..");
                this.manager.getNextStream().publishInitialEvent(sample);
            });

            try {
                Thread.sleep(0, 2);
            } catch (Exception ex) {

            }
        }

        long end = System.nanoTime();

        long spent = end - start;
        long spentMillis = spent / 1_000_000;
        long spentSeconds = spentMillis / 1000;

        System.out.println(">> " + spent + "[ns] : " + spentMillis + "[ms] : " + spentSeconds + "[s]");

    }

}
