
function date2str(x,y) {
var z = {M:x.getMonth()+1,d:x.getDate(),h:x.getHours(),m:x.getMinutes(),s:x.getSeconds()};
y = y.replace(/(M+|d+|h+|m+|s+)/g,function(v) {return ((v.length>1?"0":"")+eval('z.'+v.slice(-1))).slice(-2)});
return y.replace(/(y+)/g,function(v) {return x.getFullYear().toString().slice(-v.length)});
}

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
	var t = minWidth;
	
	if(document.body.offsetWidth > t){
		t = document.body.offsetWidth-20;
	}
	
	return t;
}
//计算列表的高度
//计算列表的高度
/*function getHeight(id){
	var tt = $('#'+id).offset().top;
	var temp = windowHeight();
	var t =temp-tt-50;
	alert(t);
	if(t>0){
		return t;
	}else{
		return 350;//如果t为小于0，给一个默认值
	}
	
}

function windowHeight() {
    var de = document.documentElement;
    return self.innerHeight||(de && de.clientHeight)||document.body.clientHeight;
}*/
function getHeight(id){
	var tt = $('#'+id).offset().top;
	
	var temp = document.body.offsetHeight-200;
	//alert(temp);
	var t =temp-tt;
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
		size = 10;
	}else if(10<size && size<20){
		size = 20;
	}else if(20<size && size<30){
		size = 25;
	}else if(30<size && size<40){
		size = 30;
	}
	return size;
}


