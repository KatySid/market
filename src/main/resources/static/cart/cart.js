angular.module('app').controller('cartController', function ($scope, $http, $localStorage) {
    const contextPath = 'http://localhost:8189/market';

    $scope.loadCart = function () {
        $http({
            url: contextPath + '/api/v1/cart',
            method: 'GET'
        }).then(function (response) {
            $scope.cartDto = response.data;
        });
    };

    $scope.clearCart = function () {
        $http({
            url: contextPath + '/api/v1/cart/clear',
            method: 'GET'
        }).then(function (response) {
            $scope.loadCart();
        });
    };

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

        $scope.deleteFromCart = function (productId) {
            $http({
            url: contextPath + '/api/v1/cart/delete/'+ productId,
            method: 'GET',
            }).then(function (response) {
            $scope.loadCart();
            });
        }


//    $scope.decProductQuantity = function(productId){
//    $http({
//                url: contextPath + '/api/v1/cart/decrement',
//                method: 'GET',
//                params: {id: productId,
//                           temp: 'empty'}
//            }).then(function (response) {
//                $scope.loadCart();
//            });
//    };



//    $scope.deleteFromCart = function (productId) {
//            $http({
//                url: contextPath + '/api/v1/cart/delete',
//                method: 'GET',
//                params: {
//                    id: productId
//                }
//            }).then(function (response) {
//                $scope.loadCart();
//            });
//        }

//    $scope.incProductQuantity = function(productId){
//    $http({
//                url: contextPath + '/api/v1/cart/increment',
//                method: 'GET',
//                params: { id: productId,
//                          temp: 'empty'}
//            }).then(function (response) {
//                $scope.loadCart();
//            });
//    };

    $scope.saveOrder = function () {
                         $http.post(contextPath + '/api/v1/orders', $scope.newOrder).then(function successCallback(response){
                         console.log("Заказ сохранен")
                         $scope.clearCart();
                                });
                            };

    $scope.loadCart();
});