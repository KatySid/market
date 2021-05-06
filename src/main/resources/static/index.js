angular.module('app', []).controller('indexController', function ($scope, $http) {
    const contextPath = 'http://localhost:8189/market';

    $scope.init = function () {
        $http.get(contextPath + '/api/v1/products')
            .then(function (response) {
                $scope.products = response.data;
            });
        $http.get(contextPath + '/api/v1/cart')
                        .then(function (response) {
                            $scope.cart = response.data;
                        });
    };

    $scope.createNewProduct = function () {
        $http.post(contextPath + '/api/v1/products', $scope.newProduct)
            .then(function successCallback(response) {
                $scope.init();
                $scope.newProduct = null;
            }, function errorCallback(response) {
                console.log(response.data);
                alert('Error: ' + response.data.messages);
            });
    };

   $scope.addProductToCart = function (productId) {
            $http({
                url: contextPath + '/api/v1/cart/add',
                method: 'GET',
                params: {
                    id: productId,
                    temp: 'empty'
                }
            }).then(function (response) {
                $scope.init();
                console.log("OK");
            });
        }

    $scope.deleteProduct = function (productId) {
            $http({
                url: contextPath + '/api/v1/cart/delete/',
                method: 'GET',
                params: {
                    id: productId,
                    temp: 'empty'
                }
            }).then(function (response) {
                 $scope.init();
                console.log("OK");
                });
        }
     $scope.clearCart = function (){
      $http({
                 url: contextPath + '/api/v1/cart/clear',
                 method: 'GET',
                 params: {
                     temp: 'empty'
                 }
             }).then(function (response) {
                 $scope.init();
                 console.log("OK");
             });}
    $scope.init();
});