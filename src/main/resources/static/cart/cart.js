angular.module('app').controller('cartController', function ($scope, $http, $localStorage) {
    const contextPath = 'http://localhost:8189/market';

    $scope.loadCart = function () {
        $http({
            url: contextPath + '/api/v1/cart',
            method: 'GET'
        }).then(function (response) {
            $scope.cartDto = response.data;
        });
    }

    $scope.clearCart = function () {
        $http({
            url: contextPath + '/api/v1/cart/clear',
            method: 'GET'
        }).then(function (response) {
            $scope.loadCart();
        });
    }

    $scope.isUserLoggedIn = function () {
        if ($localStorage.aprilMarketCurrentUser) {
            return true;
        } else {
            return false;
        }
    }

    $scope.addProductToCart = function (productId) {
            $http({
                url: contextPath + '/api/v1/cart/add/' + productId,
                method: 'GET'
            }).then(function (response) {
                $scope.loadCart();
            });
    }

    $scope.decProductFromCart = function (productId) {
            $http({
            url: contextPath + '/api/v1/cart/delete/'+ productId,
            method: 'GET',
            }).then(function (response) {
            $scope.loadCart();
            });
    }

    $scope.deleteProductFromCart = function (productId){
            $http({
                url: contextPath + '/api/v1/cart/deleteAll/'+ productId,
                method: 'GET',
                }).then(function (response) {
                $scope.loadCart();
                });
    }

    $scope.saveOrder = function () {
            $http.post(contextPath + '/api/v1/orders', $scope.newOrder).then(function successCallback(response){
            console.log("Заказ сохранен")
            $scope.clearCart();
            });
    }

    $scope.loadCart();
});