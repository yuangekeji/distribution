angular.module('admNotice').controller('admAddNoticeCtrl',function ($q, title, $scope, $http,  $state, $stateParams, $sessionStorage, Notify) {
    title.setTitle('创建公告');
    $scope.notice = {level:'1'};
    $scope.submitFlag = true;
    $scope.id = $stateParams.id;
    if($scope.id){
        $http.get(ctx + "/admNotice/getNoticeById/"+$scope.id).success(function (resp) {
            if(resp.successful){
                $scope.notice = resp.data;
            }
        }).error(function (resp) {
            console.error(resp);
        })
    }

    /**提交*/
    $scope.submit = function () {
        if($scope.submitFlag){
            $scope.submitFlag = false;
            if($scope.check()){
                $scope.startLoading();
                $http.post(ctx + "/admNotice/insertOrUpdate",$scope.notice).success(function (resp) {
                    if(resp.successful){
                        $scope.stopLoading();
                        Notify.success('添加成功，返回列表页面。');
                        $state.go("app.admNotice");
                        $scope.submitFlag = true;
                    }else{
                        $scope.stopLoading();
                        Notify.error('添加失败，请重新尝试。');
                    }
                }).error(function (resp) {
                    console.log(resp);
                    $scope.stopLoading();
                    $scope.submitFlag = true;
                });
            }
        }
    };

    /**校验*/
    $scope.check = function () {
        if(!$scope.notice.title||!$scope.notice.title.trim()){
            Notify.warning("请输入公告标题");
            $scope.submitFlag = true;
        }else if(!$scope.notice.content||!$scope.notice.content.trim()){
            Notify.warning("请输入公告内容");
            $scope.submitFlag = true;
        }else{
            return true;
        }
    };

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
});