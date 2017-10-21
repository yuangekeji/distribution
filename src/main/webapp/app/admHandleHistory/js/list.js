angular.module('admHandleHistory').controller('admHandleHistoryCtrl',function ($q, title, $scope, $http,  $state, Notify, $stateParams, $sessionStorage) {
    title.setTitle('管理员操作记录');
    $scope.notData = false;
    $scope.currentUser = $sessionStorage.currentUser;

    $scope.myPage = {
        pageNo: 1,
        pageSize: 10,
        totalCount: 0,
        result: [],
        parameterMap: {
            adminName: '',
            handleType: '',
            adminRoleId: '',
            startTime: '',
            endTime: ''
        }
    };
    $scope.search = function(){

        $http.post(ctx + '/admHandleHistory/list', $scope.myPage)
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
});

angular.module('admHandleHistory').filter("RoleFilter",function () {
    return function (input) {
        if(input=='2'){return '系统管理员'};
        if(input=='3'){return '财务'};
    }
});

angular.module('admHandleHistory').filter("HandleTypeFilter",function () {
    return function (input) {
        if(input=='0'){return '会员管理'};
        if(input=='1'){return '运营中心管理'};
        if(input=='2'){return '订单管理'};
        if(input=='3'){return '提现管理'};
        if(input=='4'){return '商品管理'};
        if(input=='5'){return '基本配置'};
        if(input=='6'){return '权限管理'};
        if(input=='7'){return 'job奖金发放管理'};
        if(input=='8'){return '充值管理'};
        if(input=='9'){return '公告管理'};
        if(input=='10'){return '分销管理'};
        if(input=='11'){return '分红包管理'};
    }
});
