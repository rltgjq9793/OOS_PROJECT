var token = $("meta[name='_csrf']").attr("content");
var header = $("meta[name='_csrf_header']").attr("content");
var csrfName = $("meta[name='_csrf_name']").attr("content");
var mid = $("#userMid").val();
var actionForm = $("#actionForm");

$("#logout").click(function(){
       actionForm.append("<input type='hidden' name='"+csrfName+"' value='"+token+"'>")
       .attr("method","post").attr("action","/logout").submit();
   
});