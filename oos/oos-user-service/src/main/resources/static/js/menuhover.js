

$("#searchOptionSelect").click(function(){
	if($(".layoutSelectBox").hasClass("open")){
		slideUp();
	}else{
		slideDown();
	}
});    


$(".bigCategory, .TopLi").click(function(){
	actionForm.html("");
	actionForm.attr("action","/user/list")
			.append("<input type='hidden' name='category' value='"+$(this).data("cat")+"'>")
			.submit();
});

$(".AllCategory").click(function(){
	actionForm.html("");
	actionForm.attr("action","/user/list")
			.append("<input type='hidden' name='keyword'>")
			.append("<input type='hidden' name='filter' value='select2'>")
			.submit();
});


function categoryMenuShow(num){

    if(num == 1){
        $(".Top_Menu").show();
    }else if(num == 2){
        $(".Outer_Menu").show();
    }else if(num == 3){
        $(".Bottom_Menu").show();
    }else if(num == 4){
        $(".Etc_Menu").show();
    }

}
  