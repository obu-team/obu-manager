function resizeTable() {
	$(".table .row").each(function() {
		var cols = $('.col',$(this)).length;
		var colSize = 100/cols;
		$('.col',$(this)).css('width',colSize+'%');
	});
}

$(function() {
	$(".table .row").each(function() {
		var cols = $('.col',$(this)).length;
		var colSize = 100/cols;
		$('.col',$(this)).css('width',colSize+'%');
	});
});

$(window).resize(function() {
	$(".table .row").each(function() {
		var cols = $('.col',$(this)).length;
		var colSize = 100/cols;
		$('.col',$(this)).css('width',colSize+'%');
	});
});
