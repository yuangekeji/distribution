/**
 * Created by lijingx on 8/24/2017.
 */
angular.module('admWarning').controller('platformHistoryCtrl',function ($q, title, $scope, $http,$uibModal, Notify, $state, $stateParams, $sessionStorage) {
    title.setTitle('平台沉淀资金明细');
    $scope.notData = false;
    $scope.myPage = {
        pageNo: 1,
        pageSize: 10,
        totalCount: 0,
        result: [],
        parameterMap: {
        }
    };

    var e1 = $('.full-view');

    $scope.startLoading=function () {
        App.blockUI({
            target: e1,
            animate: true,
            overlayColor: 'none'
        });
    }
    $scope.stopLoading=function () {
        App.unblockUI(e1);
    }

    $scope.search = function(){
        $scope.startLoading();
        $http.post(ctx + '/admWarning/platformHistoryList', $scope.myPage)
            .success(function (resp) {
                if (resp.successful) {
                    $scope.myPage = resp.data;
                    $scope.notData = false;
                    if (!$scope.myPage.result || $scope.myPage.result.length == 0) $scope.notData = true;
                    $scope.stopLoading();
                } else {
                    $scope.stopLoading();
                    Notify.error(resp.errorMessage);
                }

            }).error(function (error) {
            $scope.stopLoading();
            Notify.error(error);
        });
    }

    /**
     * 初始化
     */
    $scope.onInit = function () {
        $scope.search();
    };

    $scope.onInit();

    /**
     * 查询按钮触发
     */
    $scope.searchByParam =function () {
        $scope.myPage.pageNo = 1;
        $scope.myPage.totalCount = 0;
        $scope.search();

    }
    /**
     * 分页触发
     * @param num
     */
    $scope.pageChangeHandler = function(num) {
        $scope.myPage.pageNo = num;
        $scope.search();
    };

    $scope.goBack= function () {
        $state.go('app.admWarning');
    }
});

