angular.module('admin').controller('adminInfoCtrl',function ($q, title, $scope, $http,  $state, $stateParams, $sessionStorage, Notify,ConfirmModal) {
    title.setTitle('个人信息');
    $scope.user = $sessionStorage.currentUser;

    $scope.param = {
        loginPasswordConfirm: ''
    };

    $scope.pwdAdmin ={
        id:$sessionStorage.currentUser.id,
        oldLoginPwd: '',
        password: ''
    };

    $scope.initParam = function () {
        $scope.param = {
            loginPasswordConfirm: ''
        };
        $scope.pwdAdmin ={
            id: $sessionStorage.currentUser.id
        };
    }
    $scope.saveFlag = true;

    /**修改密码*/
    $scope.savePwd = function () {
        if($scope.saveFlag) {
            $scope.saveFlag = false;
            if (!$scope.pwdAdmin.oldLoginPwd){
                Notify.warning("请输入原登录密码。");
                $scope.saveFlag = true;
            }else if (!$scope.pwdAdmin.password){
                Notify.warning("请输入新登录密码。");
                $scope.saveFlag = true;
            }else if ($scope.pwdAdmin.oldLoginPwd==$scope.pwdAdmin.password){
                Notify.warning("原始登录密码与新登录密码不能相同，请重新输入。");
                $scope.saveFlag = true;
            }else if (!$scope.param.loginPasswordConfirm){
                Notify.warning("请输入确认密码。");
                $scope.saveFlag = true;
            }else if ($scope.pwdAdmin.password!=$scope.param.loginPasswordConfirm){
                Notify.warning("确认密码与新密码不一致，请重新输入。");
                $scope.saveFlag = true;
            }else{
                ConfirmModal.show({
                    text: '您确定要修改登录密码吗？',
                    isCancel:true //false alert ,true confirm
                }).then(function (sure) {
                    if (!sure) {
                        $scope.saveFlag = true;
                        return;
                    }
                    $scope.startLoading();
                    $http.post(ctx + "/admin/updatePwd",$scope.pwdAdmin).success(function (resp) {
                        if(resp.successful){
                            $scope.saveFlag = true;
                            $scope.stopLoading();
                            if("SUCCESS"==resp.data){
                                Notify.success("登录密码修改成功。");
                                $scope.initParam();
                                $state.go("app.home");
                            }
                            if("OLD_PWD_ERROR"==resp.data){
                                Notify.warning("原登录密码错误，修改失败。");
                            }
                        }else{
                            $scope.stopLoading();
                            $scope.saveFlag = true;
                            Notify.warning("登录密码修改失败。");
                        }
                    }).error(function (resp) {
                        $scope.stopLoading();
                        $scope.saveFlag = true;
                        console.log(resp);
                    })
                });
            }
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