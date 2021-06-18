angular.module('app').controller('productInfoController', function ($scope, $http, $localStorage, $routeParams) {
    const contextPath = 'http://localhost:8189/market';


    $scope.loadProduct = function () {
         $http({
                    url: contextPath + '/api/v1/products/' + $routeParams.productIdParam,
                    method: 'GET'
                }).then(function (response) {
                    $scope.prod = response.data;
                });

    $scope.showReviews = function() {
    $http({
           url: contextPath + '/api/v1/reviews/',
                        method: 'GET'
                    }).then(function (response) {
                        $scope.review = response.data;
                    });
    }
    }
    $scope.isUserLoggedIn = function () {
                    if ($localStorage.marketCurrentUser) {
                        return true;
                    } else {
                        return false;
                    }
                }

    $scope.saveComment = function () {
            $http.post(contextPath + '/api/v1/comments', $scope.newComment, $scope.userDto).then(function successCallback(response){
            console.log("Отзыв  сохранен")
            });
    }

    $scope.loadProduct();
    $scope.showReviews();
});

