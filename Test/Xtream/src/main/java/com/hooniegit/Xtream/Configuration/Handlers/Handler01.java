package com.hooniegit.Xtream.Configuration.Handlers;

import java.util.ArrayList;
import java.util.List;

import com.hooniegit.Xtream.Tools.Event;
import com.hooniegit.Xtream.Tools.Handler;
import com.hooniegit.Xtream.Configuration.DataClass.Sample;

import lombok.Getter;

/**
 * 예제 커스텀 핸들러 클래스입니다.
 * 
 * 시스템 구성을 위해서 이하의 작업을 수행해야 합니다.
 * - `Sample` 클래스를 이벤트가 사용할 데이터 클래스로 변경
 * - `process` 메서드에 이벤트 처리기가 수행할 작업을 구성
 */

@Getter
public class Handler01 extends Handler<Sample> {
	
	/**
	 * On Event
	 */
	@Override
    protected void process(Event<Sample> event) {
		List<Integer> list = new ArrayList<>();
		for (int i=0; i<20000; i++) {
			list.add(i);
		}
    }

}
