/**
 * 2019.06.18
 */

alert('1');
alert('2');
alert('3');

setInterval(function(){
	var now = new Date();
	document.write(now); /* body 사이에 출력 */
}, 1000); /* 1초에 한번씩 함수를 동작 */