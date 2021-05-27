angular.module('app').controller('orderController', function ($scope, $http, $localStorage) {
    const contextPath = 'http://localhost:8189/market';



    $scope.isUserLoggedIn = function () {
        if ($localStorage.aprilMarketCurrentUser) {
            return true;
        } else {
            return false;
        }
    };

    $scope.createOrder = function () {
        $http({
            url: contextPath + '/api/v1/orders',
            method: 'POST'
        }).then(function (response) {
            $scope.loadCart();
        });
    };

    $scope.loadCart();
});