<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ page import="java.sql.*"%>
<html>
<head>

<jsp:include page="reference.jsp" />
<title>Mini</title>
<style type="text/css">	
	.banner {position: relative; width: 1100px; height: 450px; top: 10px;  margin:0 auto; padding:0; overflow: hidden;}
	.banner ul {position: absolute; margin: 0px; padding:0; list-style: none; }
	.banner ul li {float: left; width: 1100px; height: 450px; margin:0; padding:0;}

</style>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>

<script type="text/javascript">

	$(document).ready(function() {
		var $banner = $(".banner").find("ul");

		var $bannerWidth = $banner.children().outerWidth();//이미지의 폭
		var $bannerHeight = $banner.children().outerHeight(); // 높이
		var $length = $banner.children().length;//이미지의 갯수
		var rollingId;

		//정해진 초마다 함수 실행
		rollingId = setInterval(function() { rollingStart(); }, 4000);//다음 이미지로 롤링 애니메이션 할 시간차
    
		function rollingStart() {
			$banner.css("width", $bannerWidth * $length + "px");
			$banner.css("height", $bannerHeight + "px");
			//alert(bannerHeight);
			//배너의 좌측 위치를 옮겨 준다.
			$banner.animate({left: - $bannerWidth + "px"}, 1500, function() { //숫자는 롤링 진행되는 시간이다.
				//첫번째 이미지를 마지막 끝에 복사(이동이 아니라 복사)해서 추가한다.
				$(this).append("<li>" + $(this).find("li:first").html() + "</li>");
				//뒤로 복사된 첫번재 이미지는 필요 없으니 삭제한다.
				$(this).find("li:first").remove();
				//다음 움직임을 위해서 배너 좌측의 위치값을 초기화 한다.
				$(this).css("left", 0);
				//이 과정을 반복하면서 계속 롤링하는 배너를 만들 수 있다.
			});
		}
	}); 

</script>
</head>
<body class="bg-light">
	<jsp:include page="menu.jsp" />
	<div class="jumbotron bg-light">
		<div class="container">
			<div class="text-center">
				<div class="contents">
					<div class="banner" style="height:450px; width:1100px; margin-bottom:-80px;margin-top:-100px;">
						<ul>
							<li><img
								src="http://jogunshop.img18.kr/web/upload/main/main_visual_event_ssak3_200727.jpg"
								width="1100px" height="450px"></li>
							<li><img
								src="http://jogunshop.img18.kr/web/upload/main/main_visual_weekly_color_rainbow_tee.jpg"
								width="1100px" height="450px"></li>
							<li><img
								src="http://jogunshop.img18.kr/web/upload/main/main_visual_hazel_open_collar.jpg"
								width="1100px" height="450px"></li>
							<li><img
								src="http://jogunshop.img18.kr/web/upload/main/main_visual_cooling_cotton_banding_slacks.jpg"
								width="1100px" height="450px"></li>
							<li><img
								src="http://jogunshop.img18.kr/web/upload/main/main_visual_cooling_collar_shirt.jpg"
								width="1100px" height="450px"></li>
						</ul>
					</div>
				</div>
			</div>
		</div>
	</div>
	<div class="container" style="margin-bottom:30px">
		<nav class="navbar navbar-expand-lg navbar-light bg-light">
			<span class="navbar-brand" style="padding-right: 70px">CATEGORY</span>
			<div class="collapse navbar-collapse" id="navbarSupportedContent">
				<ul class="navbar-nav mr-auto">
					<!-- Home 메뉴 -->
					<li class="nav-item active" style="padding-right: 50px"><a
						class="nav-link" href="index.jsp">All <span class="sr-only">(current)</span>
					</a></li>
					<li class="nav-item active" style="padding-right: 50px"><a
						class="nav-link" href="index.jsp?category=Top">Top <span
							class="sr-only">(current)</span>
					</a></li>
					<li class="nav-item active" style="padding-right: 50px"><a
						class="nav-link" href="index.jsp?category=Outer">Outer <span
							class="sr-only">(current)</span>
					</a></li>
					<li class="nav-item active" style="padding-right: 50px"><a
						class="nav-link" href="index.jsp?category=Pants">Pants <span
							class="sr-only">(current)</span>
					</a></li>
					<li class="nav-item active" style="padding-right: 50px"><a
						class="nav-link" href="index.jsp?category=Shoes">Shoes <span
							class="sr-only">(current)</span>
					</a></li>
				</ul>
				<!-- 검색 메뉴 -->
				<form class="form-inline my-2 my-lg-0" action="index.jsp">
					<input class="form-control mr-sm-2" name="search" type="search"
						placeholder="Search" aria-label="의류 검색">
					<button class="btn btn-warning" type="submit">검색</button>
				</form>
			</div>
		</nav>
	</div>
	<jsp:include page="clothes.jsp" />
	<jsp:include page="footer.jsp" />
</body>
</html>