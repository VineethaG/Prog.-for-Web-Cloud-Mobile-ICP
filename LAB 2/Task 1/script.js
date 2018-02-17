
var app = angular.module('heads', []);
app.controller('newshead', function($scope, $http) {
    $http.get("https://newsapi.org/v2/top-headlines?sources=bbc-news&apiKey=f3ded1843275412bbde062504fd1aa87")
        .then(function (response) {$scope.headnews = response.data.articles;});

    $scope.getNews = function () {
        var x = $scope.search;
        var y = $scope.source;

        $http.get('https://newsapi.org/v2/everything?q='+x+'&sources='+y+'&apiKey=f3ded1843275412bbde062504fd1aa87').success(function (data) {
            $scope.currentnews = data.articles;})
        if ($('#divMainNews').length > 0)
            $('#divMainNews').css('display', 'none');
    }});
