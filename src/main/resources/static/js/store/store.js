
$(document).ready(function(){
	
	var storeAddress = $("#store_address").data("address");
    
	var storeName = $("#store_name").data("store_name");
	
	var mapContainer = document.getElementById('map'), // 지도를 표시할 div 
	
	mapOption = {
//	    center: new kakao.maps.LatLng(33.25110701, 126.570667), // 지도의 중심좌표
	    center: new kakao.maps.LatLng(33.4520, 126.5689), // 지도의 중심좌표
	    level: 3 // 지도의 확대 레벨
	};  
	
	// 지도를 생성합니다    
	var map = new kakao.maps.Map(mapContainer, mapOption); 
	
	// 주소-좌표 변환 객체를 생성합니다	
	var geocoder = new kakao.maps.services.Geocoder();
	
	// 주소로 좌표를 검색합니다
	geocoder.addressSearch(storeAddress, function(result, status) {
		
	    // 정상적으로 검색이 완료됐으면 
	     if (status === kakao.maps.services.Status.OK) {
	
	        var coords = new kakao.maps.LatLng(result[0].y, result[0].x);
	        
	        // 결과값으로 받은 위치를 마커로 표시합니다
	        var marker = new kakao.maps.Marker({
	            map: map,
	            position: coords
	        });
	
	        // 인포윈도우로 장소에 대한 설명을 표시합니다
	        var infowindow = new kakao.maps.InfoWindow({
	            content: '<div style="width:150px;text-align:center;padding:3px 0;">' + storeName + '</div>'
	        });
	        infowindow.open(map, marker);
	
	        // 지도의 중심을 결과값으로 받은 위치로 이동시킵니다
	        map.setCenter(coords);
	        
	        
	        $(".storePosition").click(function(){
	        	map.panTo(coords);  
	        })
	               
	    } 
			 
	});    
	
	
	var userAddress = $("#delivery_address").val();
	
	if(userAddress != "" ) {
		$(".userPosition").css("display" , "inline");
		
	  // 주소로 좌표를 검색합니다
	 	geocoder.addressSearch(userAddress, function(result, status) {
	 		
	 	    // 정상적으로 검색이 완료됐으면 
	 	     if (status === kakao.maps.services.Status.OK) {
	 	
	 	        coords = new kakao.maps.LatLng(result[0].y, result[0].x);
	 	        
	 	        // 결과값으로 받은 위치를 마커로 표시합니다
	 	        var marker = new kakao.maps.Marker({
	 	            map: map,
	 	            position: coords
	 	        });
	 	        
	 	        // 인포윈도우로 장소에 대한 설명을 표시합니다
	 	        var infowindow = new kakao.maps.InfoWindow({
	 	            content: '<div style="width:150px;text-align:center;padding:3px 0;">' + "배달받을위치" + '</div>'
	 	        });
	 	        infowindow.open(map, marker);
	 	        
	 	      	$(".userPosition").click(function(){
		        	map.panTo(coords);  
		        })
	 	    } 
	 	}); 
		 
	}
	
 })
 
 $(document).ready(function() {
	// css로 display none시 카카오 맵 깨짐
	$("main ul.info").hide();
	// 탭 눌렀을때 색변경 콘텐츠 변경
	$("ul.tab > li").click(function() {
	
		const index = $(this).index() + 1;
	
		$("ul.tab > li").removeClass("select");
		$(this).addClass("select");
	
		$("main  ul").eq(1).hide();
		$("main  ul").eq(2).hide();
		$("main  ul").eq(3).hide();
		$("main  ul").eq(index).show();
	
		const offset = $(".offset").offset();
		const scrollPosition = $(document).scrollTop();
	
		if (offset["top"] < scrollPosition) {
			$("html").animate({ scrollTop: offset.top }, 100);
		}
	
	});
});