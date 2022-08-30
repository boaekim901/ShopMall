/**
 * 
 */
 /**
 * 숫자 데이터에서 3자리마다 , 찍어주기
 * 파라미터: t - 입력객체
 */
 function NumFormat(t){
	num = t.value;
	// 숫자 이외의 문자 제거
	num = num.replace(/\D/g,'');
	// 숫자 3자리마다 콤마
	len = num.length -3;
	while(len > 0){
		num = num.substr(0,len) + "," + num.substr(len);
		len -= 3;
	}
	t.value = num;
	
	return t;
}

 function go_wrt(){
	document.getElementById("prod_form").action = "admin_product_write_form"
	document.getElementById("prod_form").submit();
}
/*
**price3(수익) 필드 입력 : 판매가(price2) - 원가(price1) 로 계산
*/

function go_ab(){
	var price2 = document.getElementById("price2").value.replace(/,/g,'');
	var price1 = document.getElementById("price1").value.replace(/,/g,'');
	var ab = price2-price1;
	
	document.getElementById("price3").value=ab;
}

/*
** 상품 등록시, 입력 확인
*/
function go_save(){
	var kind = document.getElementById("kind");
	var name = document.getElementById("name");
	var price1 = document.getElementById("price1");
	var price2 = document.getElementById("price2");
	var price3 = document.getElementById("price3");
	var content = document.getElementById("content")
	var image = document.getElementById("product_image");
	var theForm = document.getElementById("write_form");
	
	if(kind.value == ""){
		alert("상품 종류를 입력하세요.");
		kind.focus();
		return false;
	} else if(name.value == ""){
		alert("상품명을 입력해주세요.")
		name.focus();
		return false;
	}else if(price1.value == ""){
		alert("상품원가를 입력해주세요.")
		price1.focus();
		return false;
	}else if(price2.value == ""){
		alert("상품판매가를 입력해주세요.")
		price2.focus();
		return false;
	}else if(content.value == ""){
		alert("상품설명을 입력해주세요.")
		content.focus();
		return false;
	}else if(image.value == ""){
		alert("상품이미지를 등록해주세요.")
		image.focus();
		return false;
	}else{
		price1.value = removeComma(price1);
		price2.value = removeComma(price2);
		price3.value = removeComma(price3);
		theForm.encoding = "multipart/form-data";
		theForm.action = "admin_product_write";
		theForm.submit();
	}
}

function removeComma(input){
	
	return input.value.replace(/,/g,'');
}
/*
** 상품상세보기 요청
*/
function go_detail(pageNum, rowsPerPage, pseq){
	document.getElementById("prod_form").action = 
	"admin_product_detail?pageNum="+pageNum+"&rowsPerPage="+rowsPerPage+ "&pseq="+pseq;
	document.getElementById("prod_form").submit();
}

/*
** 상품 수정 화면 요청
*/
function go_mod(pseq){
	document.getElementById("detail_form").action = "admin_product_update_form?pseq="+pseq;
	document.getElementById("detail_form").submit();
}
/*
**상품 수정 요청
*/
function go_mod_save(pseq){
	var kind = document.getElementById("kind");
	var name = document.getElementById("name");
	var price1 = document.getElementById("price1");
	var price2 = document.getElementById("price2");
	var price3 = document.getElementById("price3");
	var content = document.getElementById("content")
	var theForm = document.getElementById("update_form");
	var useyn = document.getElementById("useyn");
	var bestyn = document.getElementById("bestyn");
	
	if(kind.value == ""){
		alert("상품 종류를 입력하세요.");
		kind.focus();
		return false;
	} else if(name.value == ""){
		alert("상품명을 입력해주세요.")
		name.focus();
		return false;
	}else if(price1.value == ""){
		alert("상품원가를 입력해주세요.")
		price1.focus();
		return false;
	}else if(price2.value == ""){
		alert("상품판매가를 입력해주세요.")
		price2.focus();
		return false;
	}else if(content.value == ""){
		alert("상품설명을 입력해주세요.")
		content.focus();
		return false;
	}else{
		
		if(useyn.checked == true){
			useyn.value ="y";
		}else{
			useyn.value ="n";
		}
		if(bestyn.checked == true){
			bestyn.value ="y";
		}else{
			bestyn.value ="n";
		}
		
		price1.value = removeComma(price1);
		price2.value = removeComma(price2);
		price3.value = removeComma(price3);
		theForm.encoding = "multipart/form-data";
		theForm.action = "admin_product_update?pseq="+pseq;
		theForm.submit();
	}
}


function go_search(){
	document.getElementById("prod_form").action="admin_product_list";
	document.getElementById("prod_form").submit();
	
}

function go_list(pageNum,rowsPerPage){
	document.getElementById("detail_form").action="admin_product_list?pageNum="+pageNum+"&rowsPerPage="+rowsPerPage;
	document.getElementById("detail_form").submit();
}