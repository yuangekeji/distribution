angular.module('admOperator').controller('admOperatorPerformanceCtrl',function ($q, title, $scope, $http,  $state, Notify, $sessionStorage, ConfirmModal) {
    title.setTitle('运营中心业绩查询');
    $scope.loadingFlag = true;
    $scope.notData = false;
    $scope.myPage = {
        pageNo: 1,
        pageSize: 10,
        totalCount: 0,
        result: [],
        parameterMap: {}
    };

    $scope.onInit = function () {
        $http.post(ctx + '/admOperator/list', $scope.myPage).success(function (resp) {
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

    /**查询*/
    $scope.search = function () {
        $scope.myPage.pageNo = 1;
        $scope.myPage.totalCount = 0;
        $http.post(ctx + '/admOperator/list', $scope.myPage).success(function (resp) {
            if (resp.successful) {
                $scope.myPage = resp.data;
            } else {
                console.log(resp.errorMessage);
            }
        });
    };

    /**翻页*/
    $scope.pageChangeHandler = function(num) {
        $scope.myPage.pageNo = num;
        $scope.onInit();
    };


});
