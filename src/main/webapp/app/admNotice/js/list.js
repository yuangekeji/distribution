angular.module('admNotice').controller('admNoticeCtrl',function ($q, title, $scope, $http,  $state, Notify, $sessionStorage, ConfirmModal) {
    title.setTitle('公告管理');
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
        $http.post(ctx + '/admNotice/list', $scope.myPage).success(function (resp) {
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
        $http.post(ctx + '/admNotice/list', $scope.myPage).success(function (resp) {
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

    /**跳转到添加页*/
    $scope.gotoAddPage = function (id) {
        $state.go("app.admAddNotice",{id:id});
    };

    /**发布*/
    $scope.publish = function (id) {
        $http.post(ctx + "/admNotice/insertOrUpdate",{id:id,publishingState:'Y',noticeTime:new Date(),updateTime:new Date()}).success(function (resp) {
            if(resp.successful){
                Notify.warning("发布成功");
                $scope.search();
            }else{
                Notify.warning("发布失败");
            }
        }).error(function (resp) {
            console.error(resp);
        })
    };

    /**删除*/
    $scope.delete = function (id) {
        $http.post(ctx + "/admNotice/insertOrUpdate",{id:id,deleteFlag:'Y',updateTime:new Date()}).success(function (resp) {
            if(resp.successful){
                Notify.warning("删除成功");
                $scope.search();
            }else{
                Notify.warning("删除失败");
            }
        }).error(function (resp) {
            console.error(resp);
        })
    };



    /**取消*/
    $scope.cancel = function (id) {
        $http.post(ctx + "/admNotice/insertOrUpdate",{id:id,publishingState:'N',noticeTime:null}).success(function (resp) {
            if(resp.successful){
                Notify.warning("取消成功");
                $scope.search();
            }else{
                Notify.warning("取消失败");
            }
        }).error(function (resp) {
            console.error(resp);
        })
    };
});

angular.module('admNotice').filter("NoticeLevelFilter",function () {
    return function (input) {
        if(input=='1'){return '一般'};
        if(input=='2'){return '紧急'};
    }
});angular.module('admNotice').filter("PublishFilter",function () {
    return function (input) {
        if(input=='Y'){return '已发布'};
        if(input=='N'){return '未发布'};
    }
});