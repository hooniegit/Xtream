package com.hooniegit.Xtream.Stream.Configuration;

import java.util.List;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.hooniegit.Xtream.Stream.Handler;
import com.hooniegit.Xtream.Stream.StreamAutoConfiguration;
import com.hooniegit.Xtream.Stream.Configuration.DataClass.Sample;
import com.hooniegit.Xtream.Stream.Configuration.Handlers.Handler01;
import com.hooniegit.Xtream.Stream.Configuration.Handlers.Handler02;

/**
 * 사용자가 별도로 구성해야 하는 구성 클래스입니다. 시스템 구축을 위해 이하의 작업을 수행해야 합니다.
 * - 이벤트가 처리해야 할 데이터 타입 및 핸들러 구성
 * - handlers() 메서드의 데이터 타입 수정 및 핸들러 리스트 추가
 */

@Configuration
public class CustomStreamConfiguration {

    /**
     * Sample 데이터 타입의 핸들러를 생성하여 Bean 환경에 등록합니다.
     *
     * @return 핸들러 리스트
     */
    @Bean
    public List<Handler<Sample>> handlers() {
        return List.of(new Handler01(), new Handler02());
    }

    /**
     * StreamAutoConfiguration을 구성하고 Bean 환경에 등록합니다.
     */
    @Bean
    public StreamAutoConfiguration<Sample> streamAutoConfiguration() {
        return new StreamAutoConfiguration<>();
    }

}

