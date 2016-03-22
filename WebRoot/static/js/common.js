/**
 * 获得url上的参数
 * create by hjx
 */
function getQueryString(name) {
    var reg = new RegExp("(^|&)" + name + "=([^&]*)(&|$)", "i");
    var r = window.location.search.substr(1).match(reg);
    if (r != null) return unescape(r[2]); return null;
    }
/**
 * 获得body宽度
 * 当有多个tabs 选择最宽的search的宽度
 * @param minWidth
 * create by hjx
 */
function getWidth(minWidth){
	var searchWidth = "";
	$(".search").each(function(){
		if(searchWidth==""){
			searchWidth = $(this).innerWidth();
		}else{
			if($(this).innerWidth()>searchWidth){
				searchWidth = $(this).innerWidth();
			}
		}
	});
	var t = searchWidth+2;
	// var t =document.body.clientWidth*0.9725;
	    if(minWidth>t){
	    	t=minWidth;
	    }
	    return t;
}
//计算列表的高度
function getHeight(id){
	var tt = $('#'+id).offset().top;
	var temp = windowHeight();
	var t =temp-tt-50;
	if(t>0){
		return t;
	}else{
		return 350;//如果t为小于0，给一个默认值
	}
	
}

function windowHeight() {
    var de = document.documentElement;
    return self.innerHeight||(de && de.clientHeight)||document.body.clientHeight;
}
//由列表的高度，计算列表展示的行数
function getPageSize(height){
	var size = parseInt(height / (400/12));//21
	
	if(eval(size)<5){
		size = 5;
	}else if(5<size && size<10){
		size = 5;
	}else if(10<size && size<15){
		size = 10;
	}else if(15<size && size<20){
		size = 15;
	}else if(20<size && size<25){
		size = 20;
	}else if(25<size && size<30){
		size = 25;
	}else if(30<size && size<35){
		size = 30;
	}else if(35<size && size<40){
		size = 35;
	}else if(40<size && size<45){
		size = 40;
	}else if(45<size && size<50){
		size = 45;
	}else if(size>50){
		size = 50;
	}
	return size;
}
