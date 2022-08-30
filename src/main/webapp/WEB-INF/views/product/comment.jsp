<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <!-- link rel="stylesheet" href="css/bootstrap.min.css"> -->
    <style>
    #cCnt {
    	border-radius: 3px;
    	border: 1px solid #000;
    	padding: 5px;
    }
    
    #reply {
    	margin-top: 8px;
    	border-radius: 3px;
    	border: 1px solid #ccc;
    	padding: 5px;
    }
    table#rep_input td{
    	border: 0px dotted;
    }
    .btn {
    	border: 1px solid #ccc;
    	border-radius: 3px;
    	background: #f00;
    	color: #fff;
    	padding: 5px;
    	margin: 0 5px;
    }
    #commentList {
    	background-color: rgba(254, 206, 229, 0.3);
    	border-radius: 3px;
    }
    </style>
</head>
<body>
<div class="container">
    <form id="commentForm" name="commentForm" method="post">
    <br><br>
        <div>
            <div>
                <span><h3>상품평</h3></span> <span id="cCnt"></span>
            </div>
            <div id="reply">
                <table id="rep_input" style="width: 650px">                    
                    <tr>
                        <td style="width:80%;">
                            <textarea  rows="3" cols="75" id="content" name="content" placeholder="댓글을 입력하세요"></textarea>
                        </td>
                        <td style="width:10%;">
                            <a href='#' onClick="save_comment('${productVO.pseq }')" class="btn">등록</a>
                        </td>
                    </tr>
                </table>
            </div>
        </div>
        <input type="hidden" id="pseq" name="pseq" value="${productVO.pseq }" />        
    </form>
</div>
<div class="container">
    <form id="commentListForm" name="commentListForm" method="post">
        <div id="commentList">
        </div>
    </form>
	<!-- 페이지 처리 영역 -->
    <div>
		<ul id="pagination">
		</ul>
	</div>
</div>

<script type="text/javascript">

	$(document).ready(function() {
		// 상품상세정보 로딩 시에 상품평 목록을 조회하여 출력
		getCommentList();
	});
	
	// 상품평 목록 불러오기
	function getCommentList() {
		
		$.ajax({
			type: 'GET',
			url: 'comments/list',
			dataType: 'json',
			data:$("#commentForm").serialize(),
			contentType: 'application/x-www-form-urlencoded; charset=utf-8',
			success: function(data) {
				var pageMaker = data.pageInfo;
				var totalCount = data.total;
				var commentList = data.commentList;
				
				showHTML(pageMaker, commentList, totalCount);
			},
			error: function() {
				alert("상품평 목록을 조회하지 못했습니다.")
			}
		});
	}
	
	/*
	** 상품평 페이지별 목록 요청
	*/
	function getCommentPaging(pagenum, rowsperpage, pseq) {
		$.ajax({
			type: 'GET',
			url: 'comments/list',
			dataType: 'json',
			data:{"pageNum": pagenum,
				  "rowsPerPage": rowsperpage,
				  "pseq": pseq},
			contentType: 'application/x-www-form-urlencoded; charset=utf-8',
			success: function(data) {
				var pageMaker = data.pageInfo;
				var totalCount = data.total;
				var commentList = data.commentList;
				
				showHTML(pageMaker, commentList, totalCount);
			},
			error: function() {
				alert("상품평 목록을 조회하지 못했습니다.")
			}
		});
	}
	
	function showHTML(pageMaker, commentList, totalCount) {
		var html = "";
		var p_html = "";
		
		if (commentList.length > 0) {
			// 상품평의 각 항목별로 HTML 생성
			$.each(commentList, function(index, item){
				html += "<div>";
				html += "<div id=\"comment_item\"> <strong>작성자: " + item.writer + "</strong>";
				html += "<span id=\"write_date\">" + displayTime(item.regDate) + "</span><br>";
				html += item.content+"<br></div>";
				html += "</div>";
			});
			
			// 페이징 버튼 출력
			if (pageMaker.prev == true) {
				p_html += "<li class=\"paginate_button previous\">";
				p_html += "<a href='javascript:getCommentPaging("
					  +pageMaker.startPage-1+","+pageMaker.criteria.rowsPerPage+","+${productVO.pseq}+")'>[이전]</a></li>"
				/*
				p_html += "<a href=\"comments/list?pageNum="+pageMaker.startPage-1+
						  "&rowsPerPage="+pageMaker.cri.rowsPerPage+
						  "&pseq=${productVO.pseq}\">[이전]</a>";
				*/
			}
			
			for(var i=pageMaker.startPage; i<=pageMaker.endPage; i++){
				p_html += "<a href='javascript:getCommentPaging("
					  + i +","+pageMaker.criteria.rowsPerPage+","+${productVO.pseq}+")'>["+i+"]</a></li>"
				console.log(p_html);	  
				/*
				p_html += "<a href=\"comments/list?pageNum="+i+
						  "&rowsPerPage="+pageMaker.cri.rowsPerPage+
						  "&pseq=${productVO.pseq}\">[" + i + "]</a>";
				*/
			}
			
			if (pageMaker.next == true) {
				p_html += "<li class=\"paginate_button next\">";
				p_html += "<a href='javascript:getCommentPaging("
					  +(pageMaker.endPage+1)+","+pageMaker.criteria.rowsPerPage+","+${productVO.pseq}+")'>[다음]</a></li>"
				/*
				p_html += "<a href=\"comments/list?pageNum="+(pageMaker.endPage+1)+
						  "&rowsPerPage="+pageMaker.cri.rowsPerPage+
						  "&pseq=${productVO.pseq}\">[다음]</a>";
				*/
			}
			
		} else { // 조회된 상품평이 없을 경우
			html += "<div>";
			html += "<h5>등록된 상품평이 없습니다.</h5>";
			html += "</div>";
		}
		
		// 상품평 갯수 출력
		$("#cCnt").html("댓글 " + "<span style='color:#00f;'>" + totalCount+"</span>");
		// 상품평 목록 출력
		$("#commentList").html(html);
		// 페이징 버튼 출력
		$("#pagination").html(p_html);
		
	}
	
	/*
	** 입력 파라미터:
	**     timeValue - 상품평 등록 시간
	*/
	function displayTime(timeValue) {
		var today = new Date();
		
		// 현재시간에서 댓글등록시간을 빼줌
		var timeGap = today.getTime() - timeValue;
		
		// timeValue를 Date객체로 변환
		var dateObj = new Date(timeValue);
		
		// timeGap이 24시간 이내인지 판단
		if (timeGap < (1000 * 60 * 60 * 24)) {  // 시, 분, 초를 구함
			var hh = dateObj.getHours();
			var mi = dateObj.getMinutes();
			var ss = dateObj.getSeconds();
			
			//return hh + ':' + mi + ':' + ss;
			return [(hh>9 ? '':'0')+hh, ':', (mi>9 ? '':'0')+mi, ':', 
				    (ss>9 ? '':'0')+ss].join('');
		} else {
			var yy = dateObj.getFullYear();
			var mm = dateObj.getMonth() + 1;
			var dd = dateObj.getDate();
			
			//return yy + "-" + mm + "-" + dd;
			return [yy, '/', (mm>9 ? '':'0')+mm, '/', (dd>9 ? '':'0')+dd].join('');
		}
	}
	
	/*
	** 상품 등록
	*/
	function save_comment(pseq) {
		$.ajax({
			type:'POST',
			url:'comments/save',
			data:$("#commentForm").serialize(),
			success: function(data) {
				if (data=='success') {	// 상품평 등록 성공
					getCommentList(); 	// 상품평 목록 요청함수 호출
					$("#content").val("");
				} else if (data=='fail') {
					alert("상품평 등록이 실패하였습니다. 다시 시도해 주세요.");
				} else if (data=='not_logedin') {
					alert("상품평 등록은 로그인이 필요합니다.");
				}
			},
			error: function(request, status, error) {
				alert("error:" + error);
			}
		});
	}
</script>
</body>
</html>



