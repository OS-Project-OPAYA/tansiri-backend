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

    @Autowired
    private StartService startService;

    @Autowired
    private DestinationService destinationService;

    public Poi searchFirstPoi(String keyword, boolean isStart, String userID) {
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
                JsonNode rootNode = objectMapper.readTree(response.body().string());
                JsonNode poisNode = rootNode.path("searchPoiInfo").path("pois").path("poi");

                if (poisNode.isArray() && poisNode.size() > 0) {
                    // 첫 번째 장소 선택
                    JsonNode firstPoi = poisNode.get(0);
                    selectedPoi = new Poi();
                    selectedPoi.setId(firstPoi.path("id").asText(null));
                    selectedPoi.setName(firstPoi.path("name").asText(null));
                    selectedPoi.setFrontLat(firstPoi.path("frontLat").asText(null));
                    selectedPoi.setFrontLon(firstPoi.path("frontLon").asText(null));

                    // 출발지이면 Start 테이블에 저장, 목적지이면 Destination 테이블에 저장
                    if (isStart) {
                        Start start = new Start(selectedPoi.getName(), selectedPoi.getFrontLat(), selectedPoi.getFrontLon(), userID);
                        startService.saveStart(start); // StartService를 통해 저장
                    } else {
                        Destination destination = new Destination(selectedPoi.getName(), selectedPoi.getFrontLat(), selectedPoi.getFrontLon(), userID);
                        destinationService.saveDestination(destination); // DestinationService를 통해 저장
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return selectedPoi; // 선택된 첫 번째 장소 반환
    }
}
