var app = angular.module('friends', [])
    .controller('listCtrl', function($scope, $http) {
        $scope.getFriends = function () {
            var x = $scope.search;
            var friendsList = [];
            var url = 'https://api.twitter.com/1/friends/ids.json?cursor=-1&screen_name='+x;
            console.log(url);
            $http.get(url,{
                    headers: {
                        'Authorization': 'Bearer AAAAAAAAAAAAAAAAAAAAACT94gAAAAAA0LKd4PbcKFUcqiCDjpSsrTY%2BrCU%3DkAF9tjOBQmerwgs2umcjWMWPXVZ9fMwlegahRKt548fXjEQqv1'
                    }
            }
                ).success(function (data) {

                console.log(data.users);
                $scope.friendsList = data.users;

                })
        }});
