angular.module('admin').controller('adminAddCtrl',function ($q, title, $scope, $http,  $state, $stateParams, $sessionStorage, Notify) {
    title.setTitle('创建管理员');
    $scope.user = $sessionStorage.currentUser;
    $scope.admin = { };
    $scope.confirmLoginPassword = "";
    $scope.submitFlag = true;

    /**提交*/
    $scope.submit = function () {
        if($scope.submitFlag){
            $scope.submitFlag = false;
            if($scope.check()){
                $http.post(ctx + "/admin/insert",$scope.admin).success(function (resp) {
                if(resp.successful){
                    if(resp.data=='PHONE_NULL'){
                        Notify.warning("请输入手机号码(登录账号)。");
                        $scope.submitFlag = true;
                    }else if(resp.data=='PHONE_EXIST'){
                        Notify.warning("改手机号码已被注册，请重新输入。");
                        $scope.submitFlag = true;
                    }else if(resp.data=='SUCCESS'){
                        $state.go("app.admin");
                        $scope.submitFlag = true;
                    }
                }
                }).error(function (resp) {
                    console.log(resp);
                    $scope.submitFlag = true;
                });
            }
        }
    };

    /**校验*/
    $scope.check = function () {
        if(!$scope.admin.name||!$scope.admin.name.trim()){
            Notify.warning("请输入昵称。");
            $scope.submitFlag = true;
        }else if(!$scope.admin.mobile||!$scope.admin.mobile.trim()){
            Notify.warning("请输入手机号码(登录账号)。");
            $scope.submitFlag = true;
        }else if(!(/^1[3|4|5|8][0-9]\d{4,8}$/.test($scope.admin.mobile))){
            Notify.warning("手机号码(会员账号)有误，请重新输入。");
            $scope.submitFlag = true;
        }else if(!$scope.admin.roleId){
            Notify.warning("请选择角色。");
            $scope.submitFlag = true;
        }else if(!$scope.admin.password||!$scope.admin.password.trim()){
            Notify.warning("请输入登录密码。");
            $scope.submitFlag = true;
        }else if(!$scope.confirmLoginPassword||!$scope.confirmLoginPassword.trim()){
            Notify.warning("请输入确认密码。");
            $scope.submitFlag = true;
        }else if($scope.admin.password!=$scope.confirmLoginPassword){
            Notify.warning("登录密码与确认密码不一致，请重新输入。");
            $scope.submitFlag = true;
        }else{
            return true;
        }
    };
 });