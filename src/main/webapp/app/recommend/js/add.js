angular.module('recommend').controller('recommendAddCtrl',function ($q, title, $scope, $http,  $state, $stateParams, $sessionStorage) {
    title.setTitle('创建分销订单');
    $scope.user = $sessionStorage.currentUser
    $scope.member = {
        recommendName:$sessionStorage.currentUser.memberName,
        recommendId:$sessionStorage.currentUser.recommendId,
    };
    $scope.dictionary = [];
    $scope.onInit = function () {
        $http.get(ctx + '/member/getDictionary').success(function (resp) {
            if(resp.successful){
                $scope.dictionary = resp.data;
                if($scope.dictionary){
                    $scope.member.memberLevel = $scope.dictionary[0].dicCode;
                }
            }else{
                console.log(resp);
            }
        });
    };
    $scope.onInit();
    $scope.submitFlag = true;

    /**提交*/
    $scope.submit = function () {
        if($scope.submitFlag){
            $scope.submitFlag = false;
            if(!$scope.member.memberName||!$scope.member.memberName.trim()){
                 alert("请输入会员姓名。");
                $scope.submitFlag = true;
            }else if(!$scope.member.memberPhone||!$scope.member.memberPhone.trim()){
                 alert("请输入会员账号。");
                $scope.submitFlag = true;
            }else if(!(/^1[3|4|5|8][0-9]\d{4,8}$/.test($scope.member.memberPhone))){
                 alert("会员账号有误，会员账号为手机号码。");
                $scope.submitFlag = true;
            }
            else if(!$scope.member.loginPassword||!$scope.member.loginPassword.trim()){
                 alert("请输入登录密码。");
                $scope.submitFlag = true;
            }else if(!$scope.member.confirmLoginPassword||!$scope.member.confirmLoginPassword.trim()){
                 alert("请输入确认密码。");
                $scope.submitFlag = true;
            }else if($scope.member.loginPassword!=$scope.member.confirmLoginPassword){
                 alert("登录密码与确认密码不一致，请重新输入。");
                $scope.submitFlag = true;
            }
            /*else if(!$scope.member.nodeId||!$scope.member.nodeId.trim()){
                 alert("请输入会员节点。");
                $scope.submitFlag = true;
            }*/
            else if(!$scope.member.memberLevel){
                 alert("请选择会员等级。");
                $scope.submitFlag = true;
            }else if(!$scope.member.consignee||!$scope.member.consignee.trim()){
                 alert("请输入收货人姓名。");
                $scope.submitFlag = true;
            }else if(!$scope.member.expressAddress||!$scope.member.expressAddress.trim()){
                 alert("请输入收货地址。");
                $scope.submitFlag = true;
            }else{
                $http.post(ctx + "/member/insert",$scope.member).success(function (resp) {
                    if(resp.successful){
                        $state.go("app.recommend");
                    }else{
                         alert("会员账号已存在，请重新输入。")
                        $scope.submitFlag = true;
                    }
                }).error(function (resp) {
                    console.log(resp);
                    $scope.submitFlag = true;
                });
            }
        }
    }
 });