
function tologin(){

		jQuery('div#Login').fadeOut(300,function(d){jQuery('div#Signup').fadeIn(300);});

}
function tosign(){

		jQuery('div#Signup').fadeOut(300,function(d){jQuery('div#Login').fadeIn(300);});

	}



function Signsuccess(){
	//notice user sign up success!
	 alert("Sign up Success!");
	//refresh window, let user login again!
	window.location.reload();

}

function photoscroll(){
	var array = ['img/Chrysanthemum.jpg', 'img/Desert.jpg', 'img/Hydrangeas.jpg', 'img/Jellyfish.jpg', 'img/Koala.jpg'];//把图片预先放到数组中
			var picDOM = document.getElementById('pic');

			var cycle = function() {//实现图片的的更换
				picDOM.src = array[i];
				i++;
				if (i >= array.length) {
					i = 0;
				}
			};

			// 图片滚动
			var i = 0;
			var intervalID = setInterval(cycle, 1000);//启动时钟控件

			// 鼠标移上去时候，滚动停止
			picDOM.onmouseover = function () {
				clearInterval(intervalID);//时钟停止事件
			};

			// 鼠标离开的时候，滚动再次开始
			picDOM.onmouseout = function () {
				intervalID = setInterval(cycle, 1000);//启动时钟控件
			};

}
