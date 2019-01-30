var actionForm = $("#actionForm");

var token = $("meta[name='_csrf']").attr("content");
var header = $("meta[name='_csrf_header']").attr("content");
var csrfName = $("meta[name='_csrf_name']").attr("content");
var mid = $("#userMid").val();

$(document).ajaxSend(function(e,xhr,options){
    xhr.setRequestHeader(header, token);
 });

function slideUp(){
	$(".MultipleSelectBox").slideUp();
	$(".layoutSelectBox").removeClass("open");
	$("#searchOptionSelect").addClass("open");
}

function slideDown(){
	$(".MultipleSelectBox").slideDown();
	$(".layoutSelectBox").addClass("open");
	$("#searchOptionSelect").removeClass("open");
}

$(".goSearch").click(function(){
	
	var keyword = $("#searchText").val();
	
	if(keyword.length != 0){
		actionForm.append("<input type='hidden' id='filter' name='filter' value='"+$("#category_").val()+"'>");
		actionForm.append("<input type='hidden' id='keyword' name='keyword' value='"+keyword+"'>");
		actionForm.attr("action","/user/list").submit();
	}else{
		alert("검색어를 입력해주세요");
	}
});

$("#logout").click(function(){
	if(confirm("정말 로그아웃하시겠습니까 ?") == true){
       actionForm.append("<input type='hidden' name='"+csrfName+"' value='"+token+"'>")
       .attr("method","post").attr("action","/logout").submit();
    }else{
        return ;
    }
});

$("#login").click(function(){
	window.location.href="/oos/login"
});

function myPageMove(num){
	
	if(num == 1){
		actionForm.attr("action","/user/mypage/modify").submit();
	}else if(num == 2){
		actionForm.attr("action","/user/mypage/orderList").submit();
	}else if(num == 4){
		actionForm.append("<input type='hidden' name='kind' value='q'>");
		actionForm.attr("action","/user/mypage/qna").submit();
	}else if(num == 3){
		actionForm.append("<input type='hidden' name='kind' value='r'>")
					.attr("action","/user/mypage/review").submit();
	}
	
}

$(".selectSearchLi").click(function(){
	$("#category_").val($(this).attr("id"))
	$("#searchOptionSelect").html($(this).html());
	slideUp();
});

function goShoppingBasket(){
	$("#amount").val("6");
	actionForm.attr("action", "/user/cart").submit();
}

$(".fixedZzim").click(function(){
	
	actionForm.attr("action", "/user/myStoreList").submit();
});


function onlyNumber(event){
    event = event || window.event;
    var keyID = (event.which) ? event.which : event.keyCode;
    if ( (keyID >= 48 && keyID <= 57) || (keyID >= 96 && keyID <= 105) || keyID == 8 || keyID == 46 || keyID == 37 || keyID == 39 ) 
        return;
    else
        return false;
}
 
function removeChar(event) {
    event = event || window.event;
    var keyID = (event.which) ? event.which : event.keyCode;
    if ( keyID == 8 || keyID == 46 || keyID == 37 || keyID == 39 ) 
        return;
    else
        event.target.value = event.target.value.replace(/[^0-9]/g, "");
}

