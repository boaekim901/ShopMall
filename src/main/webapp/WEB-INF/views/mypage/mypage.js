/**
 *  상품을 장바구니에 담기위한 요청 전송
 */
 function go_cart(){
	
	var qty = document.getElementById("quantity").value;
	
	if(qty ==""){
		alert("수량을 입력해 주세요.")
		document.getElementById("quantity").focus();
		return false;
	}else if(qty >50){
		alert("수량이 너무 큽니다.")
		document.getElementById("quantity").focus();
		return false;
	}else{
		document.getElementById("theform").action="cart_insert"; //장바구니 저장 요청 url
		document.getElementById("theform").submit();
	}
	
}
/*
** 장바구니 상품 삭제하기
** (1) 삭제할 항목이 선택 되어 있는지 확인
** (2) Controller로 삭제요청 URL 전송
*/
function go_cart_delete(){

	var count =0; // 체크된 라디오버튼 수
	
	//삭제할 항목이 하나인 경우
	if(document.formm.cseq.length == undefined){
		if(document.formm.cseq.checked == true){
			count++;
		}
	}
	
		//삭제할 항목이 2개 이상인 경우
		
		for(var i =0; i<document.formm.cseq.length; i++){
			if(document.formm.cseq[i].checked == true){
				count++;
			}
		}

	if(count==0){
		alert("삭제할 항목을 선택해주세요.")
	}else{
		document.getElementById("theform").action = "cart_delete";
		document.getElementById("theform").submit();
	}
}

function go_order_insert(){
	
	document.getElementById("theform").action="order_insert";
	document.getElementById("theform").submit();
	
}