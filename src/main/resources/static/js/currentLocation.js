
$(function() {
	var currentLocationContainer = $("#currentLocation");
	var clPath = {};
	var clName = '';
	var toPath = null;
	var toPathName = '';
	async.series([
		function(cb) {
			$.get('/'+obuID+'/location',{},function(res) {
				clPath = res;
				cb();
			});
		},
		function(cb) {
			$.get('/'+obuID+'/destination',{},function(res) {
				if(res && res.lat && res.lon) {
					toPath = res;
				}
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
		},
		function(cb) {
			if(toPath) {
				$.get('http://maps.googleapis.com/maps/api/geocode/json?latlng='+toPath.lat+','+toPath.lon+'&sensor=false',{},function(res) {
					if(res.results.length) {
						toPathName = res.results[0].formatted_address;
					}
					cb();
				});
			} else {
				cb();
			}
		}
	],function(err) {
		currentLocationContainer.html('');
		var currentLocationMatOpts = {
			locations: [{
				lat: clPath.lat,
				lon: clPath.lon
			}],
			map_div: '#currentLocation'
		};

		if(toPath) {
			currentLocationMatOpts.generate_controls = false;
			currentLocationMatOpts.show_markers = false;
			currentLocationMatOpts.type = 'directions';
			currentLocationMatOpts.draggable = true;
			currentLocationMatOpts.locations.push({
				lat: toPath.lat,
				lon: toPath.lon
			});
		} else {
			currentLocationMatOpts.locations[0].zoom = 18;
			currentLocationMatOpts.controls_on_map = false;
		}

		new Maplace(currentLocationMatOpts).Load();
		if(clName.length && toPathName.length) {
			var htmlNameFrom = encodeURI(clName);
			var htmlNameTo = encodeURI(toPathName);
			$(".card.iframe").slideDown(200,function() {
				$(this).append('<iframe src="http://www.brianfolts.com/driver/#origin='+htmlNameFrom+'&amp;destination='+htmlNameTo+'&amp;fps=1&amp;travelmode=BICYCLING&amp;rn=" scrolling="no" id="driving"></iframe>');
			});
		}
	});

	$(".header",$(".card.iframe")).click(function() {
		$(this).parent().toggleClass('min');
	});
})
