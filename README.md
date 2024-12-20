# Xtream
Design Event Stream/Chain Architecture, Based On LMAX Disruptor. For Better Usage.　<br>
** README.md is written in KOREAN **

### License
@Apache 2.0 License

# Stream
![Stream Structure](https://github.com/user-attachments/assets/de306d23-c2a0-4596-8b7d-ef98f8579016) <br><br>

Stream 패키지는 LMAX Disruptor 아키텍처를 기반으로 '다양한 형태의 이벤트 체인을 보다 간단히 구성하기 위해' 구성한 단순 구조의 패키지입니다. 
Stream 패키지에서는 이벤트 체인을 보다 단순한 형태로 구현하기 위해 EventHandler 클래스를 상속받는 Handler 클래스를 정의하고, 
HashMap<String, Object> 형태로 데이터를 관리 및 갱신해 범용성을 확보합니다. <br><br>

```
- Version: 0.1.0
```

### Need To Do
1. Event 핸들러 정의
``` java
@Getter
public class Event01Handler extends Handler {
	
　　/**
　　* On Event Task
　　*/
　　@Override
    protected void process(Event event) {
　　　　System.out.println(">> Event 01 Started");
		
　　　　// Task Zone : 수행할 작업을 정의합니다.
　　　　System.out.println("[Received] " + (String) event.getData().get("id"));

　　　　// Share Zone : 이벤트를　갱신합니다．
　　　　HashMap<String, Object> share = new HashMap<>();
　　　　share.put("id", "HOONIEGIT");
　　　　share.put("email", (String) event.getData().get("email"));
　　　　event.setData(share);
　　}
}
```
<br>

2. Configuration 구성
``` java
@Configuration
public class StreamConfiguration {

    ...
	
    @Bean
    public StreamBuilder streamBuilder() {
    	// ** NEED TO DEFINE YOUR OWN HANDLER & ADD **
        Handler handler1 = new Event01Handler();
        Handler handler2 = new Event02Handler();
        
        return new StreamBuilder(List.of(handler1, handler2));
    }

    ...

}

```
<br>

3. Service 또는 외부 클래스에서 이벤트 발행

``` java
@Service
public class SampleService {

    private final StreamManager streamManager;

    @Autowired　// Bean 환경에 등록된 StreamManager 호출
    public SampleService(StreamManager streamManager) {
        this.streamManager = streamManager;
    }

    public void processEvent() {
        Stream stream = streamManager.getNextStream();

        // 1. Set Initial Map Data
        HashMap<String, Object> initialData = new HashMap<>();
        initialData.put("id", "hooniegit");
        initialData.put("email", "neivekim76@gmail.com");
        
        // 2. Publish Event
        stream.publishInitialEvent(initialData);
    }
}

```
