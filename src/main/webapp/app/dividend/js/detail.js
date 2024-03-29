/**
 * Created by lijingx on 8/24/2017.
 */

angular.module('dividend').controller('dividendDetailCtrl',function ($q, title, $scope, $http,  $state, $stateParams, $sessionStorage) {
    title.setTitle('分红包明细');

    $scope.notData = false;
    $scope.myPage = {
        pageNo: 1,
        pageSize: 10,
        totalCount: 0,
        result: [],
        parameterMap: {
            memberId: $stateParams.memberId,
            orderNo: $stateParams.orderNo
        }
    };
    $scope.titleData = {
        pageNo: 1,
        pageSize: 10,
        totalCount: 0,
        result: [],
        parameterMap: {
            memberId: $stateParams.memberId,
            orderNo: $stateParams.orderNo
        }
    };
    $scope.search = function(){
        $http.post(ctx + '/dividend/detailsTitleData', $scope.titleData)
            .success(function (resp) {
                if (resp.successful) {
                    $scope.titleData = resp.data;
                } else {
                    console.log(resp.errorMessage);
                }

            }).error(function (error) {
            console.error(error);
        });

        $http.post(ctx + '/dividend/details', $scope.myPage)
            .success(function (resp) {
                if (resp.successful) {
                    $scope.myPage = resp.data;
                    $scope.notData = false;
                    if (!$scope.myPage.result || $scope.myPage.result.length == 0) $scope.notData = true;

                } else {
                    console.log(resp.errorMessage);
                }

            }).error(function (error) {
            console.error(error);
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

    $scope.returnList = function () {
        $state.go('app.dividend');
    }
});
