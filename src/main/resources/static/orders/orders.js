angular.module('app').controller('orderController', function ($scope, $http, $localStorage, $location) {
    const contextPath = 'http://localhost:8189/market';

    $scope.isUserLoggedIn = function () {
        if ($localStorage.marketCurrentUser) {
            return true;
        } else {
            return false;
        }
    };

    if(!$scope.isUserLoggedIn()){
            $location.path('/')}

    $scope.showMyOrders = function () {
            $http({
                url: contextPath + '/api/v1/orders',
                method: 'GET'
            }).then(function (response) {
                $scope.myOrders = response.data;
            });
        };

    if ($scope.isUserLoggedIn()) {
            $scope.showMyOrders();
    }

});