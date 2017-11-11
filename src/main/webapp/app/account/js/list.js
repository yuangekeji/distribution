angular.module('account').controller('accountListCtrl',
    function ($q, title, $scope, $http,  $state, $stateParams, $sessionStorage, $log, $uibModal, ConfirmModal,Notify) {
    title.setTitle('账户管理');

    $scope.currentUser = $sessionStorage.currentUser;

    $scope.myPage = {
        pageNo: 1,
        pageSize: 10,
        totalCount: 0,
        result: [],
        parameterMap: {
            memberName: '',
            receiveName: '',
            status: '',
            startTime: '',
            endTime: '',
            cancelStartTime: '',
            cancelEndTime: ''
        }
    };

    var e1 = $('.portlet');
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
        $http.post(ctx + '/transfer/getTransferList', $scope.myPage)
            .success(function (resp) {
                if (resp.successful) {
                    $scope.myPage = resp.data;
                    $scope.notData = false;
                    if (!$scope.myPage.result || $scope.myPage.result.length == 0) $scope.notData = true;
                } else {
                    Notify.error(resp);
                }
            }).error(function (error) {
            Notify.error(error);
        });

    };
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

    $scope.gotoTransferReorder = function () {
        $state.go("app.transferReorder");
    };
});
angular.module('account').filter("TransferTypeFilter",function () {
    return function (input) {
        if(input=='0'){return '转账成功'};
        if(input=='1'){return '已撤销'};
    }
});