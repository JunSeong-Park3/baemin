<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fm" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!-- 7월 22일 아래 store.js and storeDetail.js 주석 -->
<!-- <script src="/js/store/store.js"></script> -->
<!-- <script src="/js/store/storeDetail.js"></script> -->
<script src="/js/order/order.js"></script>
<script type="text/javascript"
	src="//dapi.kakao.com/v2/maps/sdk.js?appkey=e607a2b83382c52d4be9d3a1a9219506&libraries=services,clusterer,drawing"></script>
<!-- <script type="text/javascript" -->
<!-- 	src="//dapi.kakao.com/v2/maps/sdk.js?appkey=e607a2b83382c52d4be9d3a1a9219506"></script> -->

<div id="wrap">
	<nav>
		<c:set var="info" value="${store.storeInfo }" />
		<h1 id="store_name" data-store_name="${info.storeName }">${info.storeName }</h1>
		<div id="is_open" data-is_open="${store.storeInfo.isOpen }"></div>
		<div class="inf">
			<div>
				<%-- 
				<span class="score_box">
             		<c:forEach begin="0" end="4" var="i">
             			<c:choose>
	           				<c:when test="${Math.round(info.score) > i }">
		                   		<i class="far fas fa-star"></i>
		                   	</c:when>
		                   	<c:otherwise>
		                   		<i class="far fa-star"></i>
		                   	</c:otherwise>
             			</c:choose> 
             		</c:forEach>
                  		
                  	<span class="store_score" data-score="${info.score }">${info.score }</span>
                  	
				</span><br>
                 --%>

				<span><i class="fas fa-heart"></i> 찜 </span> <span
					class="likes_count" data-count=0>0</span>

			</div>
			<div>
				<span class="store_review_count" data-review_count="0"> 리뷰 0</span>
				<span>사장님 댓글 0</span>
			</div>

			<div id="min_delivery" data-min_delivery="${info.minDelivery }">
				최소주문금액
				<fm:formatNumber value="${info.minDelivery }" pattern="###,###" />
				원
			</div>
			<div>예상 배달시간 ${info.deliveryTime  }분</div>
			<div id="delivery_tip" data-delivery_tip="${info.deliveryTip }">
				배달팁
				<fm:formatNumber value="${info.deliveryTip }" pattern="###,###" />
				원
			</div>
		</div>
	</nav>


	<!-- 모바일 카트 -->
	<div class="m_cart_img">
		<div class="m_cart_img_box">
			<i class="fas fa-shopping-cart"></i> <span class="m_cart_count"></span>
		</div>
	</div>
	<!-- 모바일 카트 -->



	<!-- 장바구니 -->
	<aside id="cart">
		<div class="cart">
			<h2>장바구니</h2>
			<i class="far fa-trash-alt deliteAll"></i>

			<div class="cart_list">
				<ul>
					<!-- 
					<li>
						<h3>메뉴</h3>
  							<div>가격</div>
  							<div>수량 : 0 </div>
  							<div> 옵션  </div>
  							<div>합계 : 0원</div>
  							<button class="cancle_btn"> ｘ </button> 
		            </li>
  							-->
				</ul>
			</div>

			<div class="order_btn_box">
				<div class="total">장바구니가 비었습니다.</div>
				<button class="order_btn" disable>주문하기</button>

				<!-- order script 강제 주입	 -->

				<!-- order script 강제 주입	 끝-->


			</div>
		</div>

	</aside>
	<div class="alarm">장바구니에 담았습니다</div>
	<!-- 장바구니 -->


	<main>
		<div class="offset"></div>
		<ul class="tab ">
			<li class="select">메뉴</li>
			<li>정보</li>
			<li>리뷰</li>
		</ul>


		<!-- 메뉴 탭 -->
		<ul class="menu">
			//추가
			<c:forEach items="${store.foodList }" var="foodList">
				<li><c:if
						test="${adminPage && SPRING_SECURITY_CONTEXT.authentication.principal.user.role == 'ROLE_ADMIN' }">
						<label class="menu_delete_label"> <i
							class="fas fa-check-square"></i> <input type="checkbox"
							class="menu_delete_checkbox" name="deleteNumber"
							value="${foodList.id }">
						</label>
					</c:if>

					<div class="menu_box">
						<div>
							<h2>${foodList.foodName }</h2>

							<fm:formatNumber value="${foodList.foodPrice }" pattern="###,###" />
							원 <input type="hidden" value="${foodList.storeId }"
								name="storeId"> <input type="hidden"
								value="${foodList.id }" name="foodId" class="food_id"> <input
								type="hidden" value="${foodList.foodName }" name="foodName"
								class="food_name"> <input type="hidden"
								value="${foodList.foodPrice }" name="foodPrice"
								class="food_price"> <input type="hidden"
								value="${foodList.foodDec }" name="foodDec" class="food_dec">
							<input type="hidden" value="${foodList.foodImg }" name="foodImg"
								class="food_img"> <input type="hidden"
								value="${foodList.foodThumb }" name="foodThumb"
								class="food_thumb">
						</div>

						<div>
							<img src="${foodList.foodImg }" alt="이미지">
						</div>
					</div></li>
			</c:forEach>


		</ul>
		<!-- 메뉴 탭 -->


		<!-- 정보 탭 -->
		<ul class="info">
			<li>
				<div>
					<h2>찾아 오시는 길</h2>

					<div id="map_box">
						<div id="map"></div>

						<div id="position_box">
							<button class="storePosition">
								<i class="far fa-dot-circle"></i> 가게 위치로
							</button>
							<button class="userPosition">
								<i class="far fa-dot-circle"></i> 내 위치로
							</button>
						</div>
					</div>

					<h2>위치안내</h2>
					<div id="store_address" data-address="${info.storeAddress2 }">${info.storeAddress2 }
						${info.storeAddress3 }</div>
				</div>
			</li>

			<li>
				<div>
					<h2>가게 소개</h2>
					<div>${info.storeDes }</div>
				</div>
			</li>

			<li>
				<div>
					<h2>영업 정보</h2>

					<div class="info_detail_title">
						<div>상호명</div>
						<div>영업시간</div>
						<div>전화번호</div>

					</div>

					<div class="info_detail">
						<div>${info.storeName }</div>
						<div>
							<span><fm:formatNumber value="${info.openingTime }"
									minIntegerDigits="2" />시 ~</span> <span><fm:formatNumber
									value="${info.closingTime }" minIntegerDigits="2" />시 </span>
						</div>
						<div>${info.storePhone }</div>

					</div>
				</div>
			</li>

			<li>
				<div>
					<h2>가계 통계</h2>
					<div class="info_detail_title">
						<div>최근 주문수</div>
						<div>전체 리뷰 수</div>
						<div>찜</div>
					</div>

					<div class="info_detail">
						<%-- 
			            <div>${info.orderCount }</div>
			            <div>${info.reviewCount }</div>
			            <div>${info.likesCount }</div> 
			            --%>
					</div>
				</div>
			</li>



		</ul>
		<!-- 메뉴 탭 -->



		<!-- 리뷰 탭 -->
		<ul class="comment">




		</ul>
	</main>
</div>


<input type="hidden" value="${info.id }" id="store_id">
<input type="hidden" value="${info.category }" id="store_category">
<input type="hidden" value="${info.openingTime }"
	id="store_opening_time">
<input type="hidden" value="${info.closingTime }"
	id="store_closing_time">

<input type="hidden" value="${BMaddress.address2 }"
	id="delivery_address">
