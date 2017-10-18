/**
 * Created by jingxin on 2017/9/6.
 */
angular.module('product').controller('productCtrl',function ($q, title, $scope, $http,  $state, $stateParams, $sessionStorage) {
    title.setTitle('商品列表');

    /**loading*/
    var e1 = $('.full-view');
    $scope.startLoading=function () {
        App.blockUI({
            target: e1,
            animate: true,
            overlayColor: 'none'
        });
    };

    $scope.stopLoading=function () {
        App.unblockUI(e1);
    };

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
        $scope.startLoading();
        $http.post(ctx + '/goods/list', $scope.myPage).success(function (resp) {
            if (resp.successful) {
                $scope.myPage = resp.data;
                $scope.stopLoading();
                $scope.notData = false;
                if (!$scope.myPage.result || $scope.myPage.result.length == 0) $scope.notData = true;
            } else {
                $scope.stopLoading();

            }
        });
    };
    $scope.onInit();

    /**查询*/
    $scope.search = function () {
        $scope.myPage.pageNo = 1;
        $scope.myPage.totalCount = 0;
        $scope.onInit();
    };

    /**翻页*/
    $scope.pageChangeHandler = function(num) {
        $scope.myPage.pageNo = num;
        $scope.onInit();
    };


    $scope.detail = function (id) {
        $state.go('app.productDetail', {id: id});
    };
});

