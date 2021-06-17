angular.module('app').controller('cartController', function ($scope, $http, $localStorage) {
    const contextPath = 'http://localhost:8189/market';

    $scope.loadCart = function () {
        $http({
            url: contextPath + '/api/v1/cart',
            method: 'GET',
            params: {
                     cartName: 'cart'
            }
        }).then(function (response) {
            $scope.cartDto = response.data;
        });
    }

    $scope.clearCart = function () {
        $http({
            url: contextPath + '/api/v1/cart/clear',
            method: 'GET',
            params: {
                            cartName: 'cart'
                        }
        }).then(function (response) {
            $scope.loadCart();
        });
    }

    $scope.isUserLoggedIn = function () {
        if ($localStorage.marketCurrentUser) {
            return true;
        } else {
            return false;
        }
    }

    $scope.addProductToCart = function (productId) {
            $http({
                url: contextPath + '/api/v1/cart/add/',
                method: 'GET',
                params: {
                         prodId: productId,
                         cartName: 'cart'
                }
            }).then(function (response) {
                $scope.loadCart();
            });
    }

    $scope.decProductFromCart = function (productId) {
            $http({
            url: contextPath + '/api/v1/cart/delete/',
            method: 'GET',
            params: {
                     prodId: productId,
                     cartName: 'cart'
                     }
            }).then(function (response) {
            $scope.loadCart();
            });
    }

    $scope.deleteProductFromCart = function (productId){
            $http({
                url: contextPath + '/api/v1/cart/deleteAll/',
                method: 'GET',
                params: {
                         prodId: productId,
                         cartName: 'cart'
                         }
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