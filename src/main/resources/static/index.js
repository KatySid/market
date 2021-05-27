(function ($localStorage) {
    'use strict';

    angular
        .module('app', ['ngRoute', 'ngStorage'])
        .config(config)
        .run(run);

    function config($routeProvider, $httpProvider) {
        $routeProvider
            .when('/', {
                templateUrl: 'home/home.html',
                controller: 'homeController'
            })
            .when('/products', {
                templateUrl: 'products/products.html',
                controller: 'productsController'
            })
            .when('/cart', {
                templateUrl: 'cart/cart.html',
                controller: 'cartController'
            })
            .when('/orders', {
                            templateUrl: 'orders/orders.html',
                            controller: 'orderController'
                        })
            .otherwise({
                redirectTo: '/'
            });
    }

    function run($rootScope, $http, $localStorage) {
        if ($localStorage.marketCurrentUser) {
            $http.defaults.headers.common.Authorization = 'Bearer ' + $localStorage.marketCurrentUser.token;
        }
    }
})();

angular.module('app').controller('indexController', function ($scope, $http, $localStorage, $location) {
    const contextPath = 'http://localhost:8189/market';
          $scope.whoAmI = function () {
                              $http({
                                  url: contextPath + '/api/v1/users/me',
                                  method: 'GET'
                              }).then(function (response) {
                                  $scope.userDto=response.data;
                              });
                          };

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
    $scope.registrationUser = function(){
                    $http.post(contextPath + '/api/v1/users', $scope.newUser)
                    .then(function successCallback(response) {
                    $scope.newUser = null;
                    }, function errorCallback(response) {
                    console.log(response.data);
                    alert('Error: ' + response.data.messages);
                 });
         };
});