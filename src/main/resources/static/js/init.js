var obuID;

moment.locale('sl');

var user = {
    name: '',
    initials: ''
};

var names = ['Sebastjan', 'Grega', 'Borut', 'Matej', 'Marko', 'Mitja', 'Miha', 'Nejc', 'Luka', 'Marino'];
var lastNames = ['Stojnšek', 'Vrbančič', 'Kitak', 'Hahn', 'Novak', 'Gorza', 'Podlesek', 'Borko', 'Pernoušek', 'Lukovič'];

$(function() {
    var locs = location.pathname.split('/');
    if(locs.length>1) {
        obuID = locs[1];
    } else {
        location.href('/');
    }
    var nameIndex = _.random((names.length-1));
    var lastNameIndex = _.random((lastNames.length-1));
    var generatedName = names[nameIndex];
    var generatedLastName = lastNames[lastNameIndex];
    user.name = generatedName+" "+generatedLastName;
    user.initials = generatedName.charAt(0).toUpperCase()+''+generatedLastName.charAt(0).toUpperCase();
    $("[userName]").html(user.name);
    $("[userInitials]").html(user.initials);
});