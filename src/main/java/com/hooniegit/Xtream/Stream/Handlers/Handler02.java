package com.hooniegit.Xtream.Stream.Handlers;

import com.hooniegit.Xtream.Stream.Event;
import com.hooniegit.Xtream.Stream.Handler;
import com.hooniegit.Xtream.Stream.DataClass.Sample;

import lombok.Getter;

/**
 * 예제 커스텀 핸들러 클래스입니다.
 * 
 * 시스템 구성을 위해서 이하의 작업을 수행해야 합니다.
 * - `Sample` 클래스를 이벤트가 사용할 데이터 클래스로 변경
 * - `process` 메서드에 이벤트 처리기가 수행할 작업을 구성
 */

@Getter
public class Handler02 extends Handler<Sample> {
	
	/**
	 * On Event
	 */
	@Override
    protected void process(Event<Sample> event) {
		// System.out.println(">> Event 02 Started");
        // System.out.println("[Received] : " + event.getData().getValue());
    }

}
