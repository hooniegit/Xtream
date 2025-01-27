package com.hooniegit.Xtream.Stream.Configuration;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.hooniegit.Xtream.Stream.Handler;
import com.hooniegit.Xtream.Stream.Stream;
import com.hooniegit.Xtream.Stream.StreamBuilder;
import com.hooniegit.Xtream.Stream.StreamManager;
import com.hooniegit.Xtream.Stream.DataClass.Sample;
import com.hooniegit.Xtream.Stream.Handlers.Handler01;
import com.hooniegit.Xtream.Stream.Handlers.Handler02;

/**
 * LMAX Disruptor 구성 클래스입니다.
 * 
 * 시스템 구성을 위해 이하의 작업을 수행해야 합니다.
 * - `Sample` 클래스를 이벤트가 사용할 데이터 클래스로 변경
 * - `streamBuilder` 메서드에 이벤트 핸들러 등록
 */

@Configuration
public class StreamConfiguration {
	
    @Value("${LMAX.stream.size}") // LMAX 이벤트 처리기 구성 시, Handler 성능 테스트를 통해 최적의 Stream Size를 찾아 적용해야 합니다.
    private int streamSize;

    /**
     * 구성한 핸들러를 기반으로 StreamBuilder 객체를 생성 후 Bean 환경에 등록합니다.
     * @return StreamBuilder 객체
     */
    @Bean
    public StreamBuilder<Sample> streamBuilder() {
        Handler<Sample> handler1 = new Handler01();
        Handler<Sample> handler2 = new Handler02();
        
        return new StreamBuilder<Sample>(List.of(handler1, handler2));
    }

    /**
     * Bean 환경에 입력한 StreamBuilder 객체를 기반으로 Stream 객체를 생성 후 Bean 환경에 등록합니다.
     * @param streamBuilder
     * @return List<Stream> 객체
     */
    @Bean
    public List<Stream<Sample>> streamList(StreamBuilder<Sample> streamBuilder) {
        List<Stream<Sample>> streamList = new ArrayList<>();
        for (int i = 0; i < streamSize; i++) {
            streamList.add(streamBuilder.build());
        }
        
        return streamList;
    }

    /**
     * Create StreamManager Bean
     * @param streamList
     * @return
     */
    @Bean
    public StreamManager<Sample> streamManager(List<Stream<Sample>> streamList) {
        return new StreamManager<Sample>(streamList);
    }

}
