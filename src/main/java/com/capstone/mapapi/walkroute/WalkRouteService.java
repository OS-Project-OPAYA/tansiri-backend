package com.capstone.mapapi.walkroute;

import com.capstone.mapapi.start.Start;
import com.capstone.mapapi.start.StartService;
import com.capstone.mapapi.destination.Destination;
import com.capstone.mapapi.destination.DestinationService;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.MediaType;
import okhttp3.RequestBody;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;

@Service
public class WalkRouteService {

    private final OkHttpClient client = new OkHttpClient();

    @Autowired
    private StartService startService;

    @Autowired
    private DestinationService destinationService;

    @Autowired
    private WalkRouteRepository walkRouteRepository;

    public WalkRoute getLatestWalkRoute() {
        return walkRouteRepository.findTopByOrderByIdDesc(); // 가장 최근의 WalkRoute 데이터 반환
    }

    // 경로 안내 API 호출 메서드
    public void findRoute() {
        // Start 테이블에서 출발지 정보 가져오기
        Start start = startService.getLatestStart();
        // Destination 테이블에서 목적지 정보 가져오기
        Destination destination = destinationService.getLatestDestination();

        if (start != null && destination != null) {
            // 출발지 및 목적지의 좌표와 이름을 가져오기
            String startX = start.getStartLon(); // 출발지 경도
            String startY = start.getStartLat(); // 출발지 위도
            String destinationX = destination.getDestinationLon(); // 목적지 경도
            String destinationY = destination.getDestinationLat(); // 목적지 위도

            // 이름을 URL 인코딩
            String startName = URLEncoder.encode(start.getStartName(), StandardCharsets.UTF_8);
            String endName = URLEncoder.encode(destination.getDestinationName(), StandardCharsets.UTF_8);

            // API 요청을 위한 JSON 형식의 문자열 생성
            MediaType mediaType = MediaType.parse("application/json");
            String jsonBody = String.format("{\"startX\":%s,\"startY\":%s,\"endX\":%s,\"endY\":%s,\"startName\":\"%s\",\"endName\":\"%s\"}",
                    startX, startY, destinationX, destinationY, startName, endName);

            RequestBody body = RequestBody.create(jsonBody, mediaType);
            Request request = new Request.Builder()
                    .url("https://apis.openapi.sk.com/tmap/routes/pedestrian?version=1&callback=function")
                    .post(body)
                    .addHeader("accept", "application/json")
                    .addHeader("content-type", "application/json")
                    .addHeader("appKey", "EHDhTt6iqk7HwqS2AirSY71g65xVG8Rp3LtZaIIx")
                    .build();

            try (Response response = client.newCall(request).execute()) {
                if (response.isSuccessful()) {
                    // 성공적인 응답 처리
                    String responseBody = response.body().string();
                    System.out.println("Route API call successful!");
                    System.out.println("Response: " + responseBody);

                    // WalkRoute 객체에 API 응답 데이터 저장
                    WalkRoute walkRoute = new WalkRoute(
                            startX, startY,
                            destinationX, destinationY,
                            start.getStartName(), destination.getDestinationName(), responseBody
                    );
                    // 데이터베이스에 저장
                    walkRouteRepository.save(walkRoute);

                } else {
                    // 실패한 경우 로그 출력
                    System.out.println("Route API call failed: " + response.code());
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else {
            System.out.println("출발지 또는 목적지가 존재하지 않습니다.");
        }
    }



}
