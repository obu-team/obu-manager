function resizeTable() {
	$(".table .row").each(function() {
		var cols = $('.col',$(this)).length;
		var colSize = 100/cols;
		$('.col',$(this)).css('width',colSize+'%');
	});
}

$(function() {
	resizeTable();
});

$(window).resize(function() {
	resizeTable();
});
