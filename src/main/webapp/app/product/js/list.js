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

    $scope.loadingFlag = true;
    $scope.notData = false;
    $scope.myPage = {
        pageNo: 1,
        pageSize: 8,
        totalCount: 0,
        result: [],
        parameterMap: {}
    };

    $scope.onInit = function () {
        $http.post(ctx + '/goods/list', $scope.myPage).success(function (resp) {
            if (resp.successful) {
                $scope.myPage = resp.data;
                $scope.loadingFlag = false;
                $scope.notData = false;
                if (!$scope.myPage.result || $scope.myPage.result.length == 0) $scope.notData = true;
            } else {
                console.log(resp.errorMessage);
            }
        });
    };
    $scope.onInit();

    $scope.detail = function () {
        $state.go('app.productDetail', {id: 1});
    };
});

