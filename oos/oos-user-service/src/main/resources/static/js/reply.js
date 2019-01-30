
var replyService = (function(){
	
	//댓글추가
	function add(reply, callback){
		$.ajax({
			type:'post',
			url:'/replies/new',
			data:JSON.stringify(reply),
			contentType:"application/json; charset=utf-8",
			success: function(result, status, xhr){
				if(callback){
					callback(result);
				}
			},
			error: function(xhr,status, er){
				if(error){
					error(er);
				}
			}	
		});
	}
	
	//댓글목록
	function getList(param, callback, error){
		
		var pno = param.pno;
		var page = param.page||1;
		var kind = param.kind;
		
		$.getJSON("/replies/pages/" +kind+ "/" +pno+ "/" +page,
				
		  function(data){
			if(callback){
				callback(data.replyCnt,data.parentCnt,data.list,data.scoreAverage);
			}
		}).fail(function(xhr,status,err){
			if(error){
				error();
			}
		});
		
	
	}
	
	//댓글삭제
	function remove(rno, callback, error){
		
		$.ajax({
			type: 'put',
			url: '/replies/delete/' + rno,
			success: function(deleteResult, staus, xhr){
				if(callback){
					callback(deleteResult);
				}
			},
			error:function(xhr,status,er){
				if(error){
					error(er);
				}
			}
			
		});
	}
	
	//댓글수정
	function update(reply, callback, error){
		
		$.ajax({
			type:'put',
			url:'/replies/' + reply.rno,
			data: JSON.stringify(reply),
			contentType: "application/json; charset=utf-8",
			success: function(result, status, xhr){
				if(callback){
					callback(result);
				}
			},
			error: function(xhr, status, er){
				if(error){
					error(er);
				}
			}
		});
		
		
	}
	
	//댓글조회
	function get(rno,callback,error){
		
		$.get("/replies/" +rno,function(result){
			if(callback){
				callback(result);
			}
		}).fail(function(xhr, status, err){
			if(error){
				error();
			}
		});		
	}
	
	//displayTime
	function displayTime(timeValue){
		
		var today = new Date();
		var gap = today.getTime() - timeValue;
		var dateObj = new Date(timeValue);
		var str = "";
		
		if(gap < (1000 * 60 * 60 * 24)){
			
			var hh = dateObj.getHours();
			var mi = dateObj.getMinutes();
			var ss = dateObj.getSeconds();
			
			return [(hh > 9? '':'0')+hh,':',(mi > 9? '':'0') + mi,':',(ss > 9? '':'0')+ss].join('');
		}else{
			
			var yy = dateObj.getFullYear();
			var mm = dateObj.getMonth() + 1;
			var dd = dateObj.getDate();
			
			return [yy, '/', (mm > 9? '':'0') +mm, '/', (dd > 9? '':'0')+dd].join('');
		}
	}//end
	
	return {
			add:add,
			getList:getList,
			remove:remove,
			update:update,
			get:get,
			displayTime:displayTime
	};
})();