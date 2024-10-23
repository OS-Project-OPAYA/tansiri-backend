let startCoordinates = null;
let endCoordinates = null;
let map;
let startMarker = null; // startMarker 초기화

$(document).ready(function () {

    // 지도 초기화
    function initializeMap() {
        // 최근의 출발지 좌표를 가져오기 위한 GET 요청
        $.get("/api/getRoute", function (coordinates) {
            const startX = coordinates.startX;
            const startY = coordinates.startY;

            // 지도 초기화
            map = new Tmapv3.Map("map_div", {
                center: new Tmapv3.LatLng(startY, startX),
                width: "100%",  // 지도의 넓이
                height: "500px",  // 지도의 높이
                zoom: 15
            });



        }).fail(function () {
            console.error("Failed to get the latest start coordinates.");
            // 기본 좌표로 지도 초기화
            map = new Tmapv3.Map("map_div", {
                center: new Tmapv3.LatLng(37.5666103, 126.9783882),  // 기본 서울 좌표
                width: "100%",
                height: "500px",
                zoom: 15
            });
        });
    }

    initializeMap();

    // POI 검색 - 출발지
    $("#startSearch").click(function () {
        const keyword = $("#startLocation").val();
        if (!keyword) return;

        // 출발지 검색 API 호출
        $.post("/startSearch", keyword, function (poi) {
            startCoordinates = { lat: poi.frontLat, lon: poi.frontLon };

            // 입력 필드에 선택한 장소 표시
            $("#startLocation").val(poi.name);
            $("#startPoiList").empty(); // 검색 결과 초기화


        });
    });

    // POI 검색 - 목적지
    $("#endSearch").click(function () {
        const keyword = $("#endLocation").val();
        if (!keyword) return;

        // 목적지 검색 API 호출
        $.post("/endSearch", keyword, function (poi) {
            endCoordinates = { lat: poi.frontLat, lon: poi.frontLon };

            // 입력 필드에 선택한 장소 표시
            $("#endLocation").val(poi.name);
            $("#endPoiList").empty(); // 검색 결과 초기화


        });
    });

    // 경로 찾기 버튼 클릭
    $("#findRoute").click(function () {
        if (startCoordinates && endCoordinates) {
            $.ajax({
                url: "/api/route",
                type: "POST",
                contentType: "application/json", // JSON 형식으로 설정
                data: JSON.stringify({
                    startX: startCoordinates.lon,
                    startY: startCoordinates.lat,
                    endX: endCoordinates.lon,
                    endY: endCoordinates.lat
                }),
                success: function (routeData) {
                    drawRoute(routeData);
                },
                error: function (xhr, status, error) {
                    console.error("Error in route finding: ", error);
                }
            });
        } else {
            alert("출발지와 목적지를 선택해주세요.");
        }
    });

    // 네비 버튼 클릭
    $("#navigate").click(function () {
        $.get("/api/getRoute", function (response) {
            const features = response.features;

            // 모든 Point feature에 대해 마커 추가
            features.forEach(function (feature) {
                if (feature.geometry.type === "Point") {
                    const [lon, lat] = feature.geometry.coordinates;

                }
            });
        });
    });

});
