moment.locale('sl');
$(function() {
	var currentLocation = new Maplace({
	    locations: [{
	    	lat: 46.5605926,
        lon: 15.6325071,
        zoom: 18
	    }],
	    map_div: '#currentLocation',
	    controls_on_map: false
	});
	currentLocation.Load();

	var drivenPath = new Maplace({
    map_div: '#drivenPath',
    generate_controls: false,
    show_markers: false,
    type: 'directions',
    draggable: true
	});
	drivenPath.Load();

	var paths = [{
		id: 1,
		start: {
			lat: 45.4654,
			lon: 9.1866
		},
		end: {
			lat: 47.36854,
			lon: 8.53910
		},
		date: '2016-1-4 11:12:00'
	},{
		id: 2,
		start: {
			lat: 45.9,
			lon: 10.9
		},
		end: {
			lat: 44.8,
			lon: 1.7
		},
		date: '2015-12-24 13:52:00'
	},{
		id: 3,
		start: {
			lat: 48.892,
			lon: 2.359
		},
		end: {
			lat: 48.13654,
			lon: 11.57706
		},
		date: '2015-8-1 12:32:00'
	}];

	async.forEach(paths,function(path,cb) {
		var _start = 'n/a';
		var _end = 'n/a';
		async.parallel([
			function(clb) {
				$.get('http://maps.googleapis.com/maps/api/geocode/json?latlng='+path.start.lat+','+path.start.lon+'&sensor=false',{},function(res) {
					if(res.results.length) {
						_start = res.results[0].formatted_address;
					}
					clb();
				});
			},
			function(clb) {
				$.get('http://maps.googleapis.com/maps/api/geocode/json?latlng='+path.end.lat+','+path.end.lon+'&sensor=false',{},function(res) {
					if(res.results.length) {
						_end = res.results[0].formatted_address;
					}
					clb();
				});
			}
		],function(err) {
			var col = "<div class='row' pathID='"+path.id+"'>";
			var date = moment(path.date, "YYYY-MM-DD HH:mm:ss").fromNow();
			col+="<div class='col'>"+date+"</div>";
			col+="<div class='col'>"+_start+"</div>";
			col+="<div class='col'>"+_end+"</div>";
			col+="<div class='col center'><button changePath='"+path.id+"'><i class='icon-compass'></i></button></div>";
			col+="</div>";
			$(".table#drivenPathTable").append(col);
			cb();
		});
	},function(err) {
		resizeTable();
	});

	$(document).on('click','button[changePath]',function() {
		var pathID = $(this).attr('changePath')*1;
		var path = _.findWhere(paths,{id:pathID});
		drivenPath.RemoveLocations([0,1],false);
		drivenPath.AddLocation(path.start,0,false);
		drivenPath.AddLocation(path.end,1,true);
	});

	$("input[name=pathSearch]").on('input',function() {
		var src = $(this).val();
		if($.trim(src).length) {
			var srcPaths = $('.row:contains("'+src+'")','.table#drivenPathTable');
			var not = '[class=th]';
			srcPaths.each(function() {
				$(this).show();
				not+=',[pathID='+$(this).attr('pathID')+']';
			});
			$('.row[pathID]:not('+not+')','.table#drivenPathTable').each(function() {
				$(this).hide();
			});
		} else {
			$('.row','.table#drivenPathTable').show();
		}
	});
})
