/**
 * Created by jingxin on 2017/9/6.
 */
angular.module('product').controller('productCtrl',function ($q, title, $scope, $http,  $state, $stateParams, $sessionStorage) {
    title.setTitle('商品列表');


    $scope.initParams = function () {
        $http.get(ctx + "/goods/findGoodsTypes").success(function (resp) {
            if(resp.successful){
                $scope.goodsTypes = resp.data;
            }
        }).error(function (resp) {
            console.log(resp);
        });
    };

    $scope.initParams();

    $scope.detail = function () {
        $state.go('app.productDetail', {id: 1});
    };
});

