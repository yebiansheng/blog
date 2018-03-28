/**
 * 
 */
function index() {
	var title = $("#keyboard").val();
	window.location.href = "index?page=1&&" + title;
}