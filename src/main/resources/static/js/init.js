var obuID;
$(function() {
    var locs = location.pathname.split('/');
    if(locs.length>1) {
        obuID = locs[1];
    } else {
        location.href('/');
    }
});