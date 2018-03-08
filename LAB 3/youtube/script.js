
var app = angular.module('search', [])
    .controller('searchEng', function($scope, $http) {
       $scope.getVideos = function () {
        var x = $scope.search;
        var currentVideos = [];
        $http.get('https://www.googleapis.com/youtube/v3/search?q='+x+'&part=snippet&type=video&key=AIzaSyCcrAQaKeQe6q7SYY2CqdqZUlSEXaMFIn0').success(function (data) {

           console.log(data.items);
            $scope.currentVideos = data.items;})
    }});
