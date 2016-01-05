
$(function() {
	var currentLocationContainer = $("#currentLocation");
	/*var currentLocation = new Maplace({
	    locations: [{
	    	lat: 46.5605926,
        lon: 15.6325071,
        zoom: 18
	    }],
	    map_div: '#currentLocation',
	    controls_on_map: false
	});
	currentLocation.Load();*/
	var clPath = {};
	var clName = '';
	async.series([
		function(cb) {
			$.get('/'+obuID+'/location',{},function(res) {
				clPath = res;
				cb();
			});
		},
		function(cb) {
			$.get('http://maps.googleapis.com/maps/api/geocode/json?latlng='+clPath.lat+','+clPath.lon+'&sensor=false',{},function(res) {
				if(res.results.length) {
					clName = res.results[0].formatted_address;
				}
				cb();
			});
		}
	],function(err) {
		currentLocationContainer.html('');
		new Maplace({
			locations: [{
				lat: clPath.lat,
				lon: clPath.lon,
				zoom: 18
			}],
			map_div: '#currentLocation',
			controls_on_map: false
		}).Load();
		if(clName.length) {
			var htmlNameFrom = encodeURI(clName);
			var htmlNameTo = encodeURI('Ljubaljana, Slovenia');
			$(".card.iframe").slideDown(200,function() {
				$(this).append('<iframe src="http://www.brianfolts.com/driver/#origin='+htmlNameFrom+'&amp;destination='+htmlNameTo+'&amp;fps=1&amp;travelmode=BICYCLING&amp;rn=" scrolling="no" id="driving"></iframe>');
			});
		}
	});

	$(".header",$(".card.iframe")).click(function() {
		$(this).parent().toggleClass('min');
	});
})
