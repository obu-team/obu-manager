$(function() {
    var historyTable = $(".table#drivenPathTable");
    var drivenPath = new Maplace({
        map_div: '#drivenPath',
        generate_controls: false,
        show_markers: false,
        type: 'directions',
        draggable: true
    });
    drivenPath.Load();

    var pathContent = '';
    var historyRes;
    var historyPaths = [];
    var historyInited = false;

    async.series([
        function(cb) {
            $.get('/'+obuID+'/driveHistory',{},function(res) {
                if(!_.isArray(res) || !res.length) {
                    return cb(true);
                }
                historyRes = res;
                cb();
            });
        },
        function(cb) {
            async.forEach(historyRes,function(path,clb) {
                if(path.trackPoints.length<2) {
                    return clb();
                }
                var historyPath = {};
                var sortedLocs = _.sortBy(path.trackPoints,'timestamp');
                var startLoc = _.first(sortedLocs);
                var endLoc = _.last(sortedLocs);
                historyPath.id = path.id;
                historyPath.date = startLoc.timestamp;
                historyPath.start = {
                    lat: startLoc.location.lat,
                    lon: startLoc.location.lon
                };
                historyPath.end = {
                    lat: endLoc.location.lat,
                    lon: endLoc.location.lon
                };
                historyPath.startName = 'n/a';
                historyPath.endName = 'n/a';
                async.parallel([
                   function(clbk) {
                       $.get('http://maps.googleapis.com/maps/api/geocode/json?latlng='+startLoc.location.lat+','+startLoc.location.lon+'&sensor=false',{},function(res) {
                           if(res.results.length) {
                               historyPath.startName = res.results[0].formatted_address;
                           }
                           clbk();
                       });
                   },
                    function(clbk) {
                        $.get('http://maps.googleapis.com/maps/api/geocode/json?latlng='+endLoc.location.lat+','+endLoc.location.lon+'&sensor=false',{},function(res) {
                            if(res.results.length) {
                                historyPath.endName = res.results[0].formatted_address;
                            }
                            clbk();
                        });
                    }
                ],function(err) {
                    historyPaths.push(historyPath);
                    var date = moment(historyPath.date, "x").fromNow();
                    pathContent+= "<div class='row' pathID='"+historyPath.id+"'>";
                    pathContent+="<div class='col'>"+date+"</div>";
                    pathContent+="<div class='col'>"+historyPath.startName+"</div>";
                    pathContent+="<div class='col'>"+historyPath.endName+"</div>";
                    pathContent+="<div class='col center'><button changePath='"+historyPath.id+"'><i class='icon-compass'></i></button></div>";
                    pathContent+="</div>";
                    clb();
                });
            },function(err) {
                cb();
            });
        }
    ],function(err) {
        $(".dot-spinner",historyTable).parent().parent().remove();
        if(err || !pathContent.length) {
            pathContent = "<div class='row'><div class='col'><i class='icon-close'></i> Ni rezultatov</div></div>";
        } else {
            historyPaths = _.sortBy(historyPaths,'date');
        }
        historyTable.append(pathContent);
        resizeTable();
        historyInited = true;
    });

    $(document).on('click','button[changePath]',function() {
        var pathID = $(this).attr('changePath');
        var path = _.findWhere(historyPaths,{id:pathID});
        drivenPath.RemoveLocations([0,1],false);
        drivenPath.AddLocation(path.start,0,false);
        drivenPath.AddLocation(path.end,1,true);
    });

    $("input[name=pathSearch]").on('input',function() {
        if(historyInited) {
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
        }
    });
});
