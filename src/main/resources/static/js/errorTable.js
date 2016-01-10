$(function() {
    var errorUl = $("ul[carError]");
    var errors = [];
    async.series([
       function(cb) {
           $.get('/'+obuID+'/error',{},function(res) {
               if(!_.isArray(res) || !res.length) {
                   return cb(true);
               }
               errors = res;
               cb();
           });
       },
        function(cb) {
            errors = _.sortBy(errors,'timestamp');
            errors = errors.reverse();
            cb();
        }
    ], function(err) {
        if(!err) {
            var out = '';
            errors.forEach(function(error) {
               out+='<li><button class="icon small red"></button><div class="key">'+error.code+'</div><div class="value">'+moment(error.timestamp, "x").fromNow()+'</div></li>';
            });
            errorUl.html(out);
            errorUl.parent().slideDown(200);
        }
    });
});
