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

         $scope.loadPageReview = function (page) {
                         $http({
                             url: contextPath + '/api/v1/reviews',
                             method: 'GET',
                             params: {
                                       p: page,
                                       productId: $routeParams.productIdParam
                                      }
                         }).then(function (response) {
                             $scope.reviewsPage = response.data;

                             let minPageIndex = page - 2;
                             if (minPageIndex < 1) {
                                 minPageIndex = 1;
                             }

                             let maxPageIndex = page + 2;
                             if (maxPageIndex > $scope.reviewsPage.totalPages) {
                                 maxPageIndex = $scope.reviewsPage.totalPages;
                             }

                             $scope.paginationArrayReview = $scope.generatePagesIndexes(minPageIndex, maxPageIndex);
                         });
                     };

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
    $scope.loadPageReview(1);
});

