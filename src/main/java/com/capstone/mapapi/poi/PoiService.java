package com.capstone.mapapi.poi;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.capstone.mapapi.start.Start; // Start 엔티티 임포트
import com.capstone.mapapi.start.StartService; // StartService 임포트
import com.capstone.mapapi.destination.Destination; // Destination 엔티티 임포트
import com.capstone.mapapi.destination.DestinationService; // DestinationService 임포트

@Service
public class PoiService {

    private final OkHttpClient client = new OkHttpClient();
    private final ObjectMapper objectMapper = new ObjectMapper();

    // StartService와 DestinationService 주입
    @Autowired
    private StartService startService;

    @Autowired
    private DestinationService destinationService;

    // TMap API로 장소 검색 및 저장
    public Poi searchFirstPoi(String keyword, boolean isStart) {
        Poi selectedPoi = null;

        // TMap API 호출 URL 구성
        String url = "https://apis.openapi.sk.com/tmap/pois?version=1&searchKeyword="
                + keyword
                + "&searchType=all&searchtypCd=A&reqCoordType=WGS84GEO&resCoordType=WGS84GEO&page=1&count=20&multiPoint=N&poiGroupYn=N";

        Request request = new Request.Builder()
                .url(url)
                .addHeader("Accept", "application/json")
                .addHeader("appKey", "EHDhTt6iqk7HwqS2AirSY71g65xVG8Rp3LtZaIIx")
                .build();

        try (Response response = client.newCall(request).execute()) {
            if (response.isSuccessful()) {
                System.out.println("TMap API call successful!"); // 성공 로그
                JsonNode rootNode = objectMapper.readTree(response.body().string());
                JsonNode poisNode = rootNode.path("searchPoiInfo").path("pois").path("poi");
                System.out.println("Response: " + rootNode.toString()); // API 응답 출력

                if (poisNode.isArray() && poisNode.size() > 0) {
                    // 첫 번째 장소 선택
                    JsonNode firstPoi = poisNode.get(0); // 첫 번째 결과 가져오기
                    selectedPoi = new Poi();
                    selectedPoi.setId(firstPoi.path("id").asText(null));
                    selectedPoi.setName(firstPoi.path("name").asText(null));
                    selectedPoi.setFrontLat(firstPoi.path("frontLat").asText(null));
                    selectedPoi.setFrontLon(firstPoi.path("frontLon").asText(null));
                    System.out.println("Selected first POI: " + selectedPoi.getName() + " " + selectedPoi.getFrontLat()
                            + " " + selectedPoi.getFrontLon()); // 선택된 장소 로그

                    // 출발지이면 Start 테이블에 저장, 목적지이면 Destination 테이블에 저장
                    if (isStart) {
                        Start start = new Start(selectedPoi.getName(), selectedPoi.getFrontLat(), selectedPoi.getFrontLon());
                        startService.saveStart(start); // StartService를 통해 저장
                    } else {
                        Destination destination = new Destination(selectedPoi.getName(), selectedPoi.getFrontLat(), selectedPoi.getFrontLon());
                        destinationService.saveDestination(destination); // DestinationService를 통해 저장
                    }
                } else {
                    System.out.println("No POIs found.");
                }
            } else {
                System.out.println("TMap API call failed: " + response.code()); // 실패 로그
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return selectedPoi; // 선택된 첫 번째 장소 반환
    }
}
