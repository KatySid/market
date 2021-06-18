angular.module('app').controller('productInfoController', function ($scope, $http, $localStorage, $routeParams) {
    const contextPath = 'http://localhost:8189/market';


    $scope.loadProduct = function () {
         $http({
                    url: contextPath + '/api/v1/products/' + $routeParams.productIdParam,
                    method: 'GET'
                }).then(function (response) {
                    $scope.prod = response.data;
                });
         }
    $scope.loadReviews = function () {
            $http({
                url: contextPath + '/api/v1/reviews/',
                method: 'GET',
                params: {
                productId: $routeParams.productIdParam
                }
            }).then(function (response) {
                $scope.reviews = response.data;
            });
        }

    $scope.showReviews = function() {
    $http({
           url: contextPath + '/api/v1/reviews/'+ $routeParams.productIdParam,
                        method: 'GET'
                    }).then(function (response) {
                        $scope.review = response.data;

                    });
    }

    $scope.isUserLoggedIn = function () {
                    if ($localStorage.marketCurrentUser) {
                        return true;
                    } else {
                        return false;
                    }
                }

    $scope.saveComment = function () {
             $http({
                            url: contextPath + '/api/v1/reviews',
                            method: 'POST',
                            params: {
                            productId: $routeParams.productIdParam,
                            comment: $scope.comment
                            }
            });
    }

    $scope.loadProduct();
    $scope.loadReviews();
});

