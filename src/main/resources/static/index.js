angular.module('app', ['ngStorage']).controller('indexController', function ($scope, $http,$location, $localStorage) {
    const contextPath = 'http://localhost:8189/market';

    $scope.init = function () {
        $http.get(contextPath + '/api/v1/products')
            .then(function (response) {
                $scope.products = response.data;
            });
        $http.get(contextPath + '/api/v1/cart')
                        .then(function (response) {
                            $scope.cartDto = response.data;
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
        $scope.order = function () {
            $http({
                url: contextPath + '/api/v1/cart/order',
                method: 'GET',
                params: {
//                    id: productId,
                    temp: 'empty'
                }
            }).then(function (response) {
                $scope.init();
                console.log("OK");
            });
        }
    $scope.tryToAuth = function () {
           $http.post(contextPath + '/auth', $scope.user)
               .then(function successCallback(response) {
                   if (response.data.token) {
                       $http.defaults.headers.common.Authorization = 'Bearer ' + response.data.token;
                       $localStorage.aprilMarketCurrentUser = {username: $scope.user.username, token: response.data.token};

                       $scope.user.username = null;
                       $scope.user.password = null;

                       $scope.whoAmI();
                   }
               }, function errorCallback(response) {
               });
       };

    $scope.tryToLogout = function () {
            $scope.clearUser();
        };

        $scope.clearUser = function () {
                delete $localStorage.aprilMarketCurrentUser;
                $http.defaults.headers.common.Authorization = '';
            };


    $scope.isUserLoggedIn = function () {
        if ($localStorage.aprilMarketCurrentUser) {
            return true;
        } else {
            return false;
        }
        };
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

             $scope.whoAmI = function () {
                     $http({
                         url: contextPath + '/api/v1/users/me',
                         method: 'GET'
                     }).then(function (response) {
                         $scope.userDto=response.data;
                     });
                 };
//   if ($localStorage.aprilMarketCurrentUser) {
//                     $http.defaults.headers.common.Authorization = 'Bearer ' + $localStorage.aprilMarketCurrentUser.token;
//                 }
    $scope.init();
});