angular.module('recommend').controller('recommendAddCtrl',function ($q, title, $scope, $http,  $state, $stateParams, $sessionStorage) {
    title.setTitle('创建分销订单');
    $scope.user = $sessionStorage.currentUser;
    $scope.member = {
        recommendPhone:$sessionStorage.currentUser.memberPhone
    };
    $scope.dictionary = [];
    $scope.onInit = function () {
        $http.get(ctx + '/member/getDictionary/member_level').success(function (resp) {
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
            if($scope.check()){
                $http.post(ctx + "/member/insert",$scope.member).success(function (resp) {
                    if(resp.successful){
                        if(resp.data=='NO_RECOMMENDER'){
                            window.wxc.xcConfirm("推荐人不存在，请重新输入。", window.wxc.xcConfirm.typeEnum.info);
                            $scope.submitFlag = true;
                        }else if(resp.data=='PHONE_EXISTENCE'){
                            window.wxc.xcConfirm("会员账号已存在，请重新输入。", window.wxc.xcConfirm.typeEnum.info);
                            $scope.submitFlag = true;
                        }else if(resp.data=='NO_NODE_MEMBER'){
                            window.wxc.xcConfirm("节点不存在，请重新输入。", window.wxc.xcConfirm.typeEnum.info);
                            $scope.submitFlag = true;
                        }else if(resp.data=='NOTE_FULL'){
                            window.wxc.xcConfirm("该节点该区已满，请从新输入。", window.wxc.xcConfirm.typeEnum.info);
                            $scope.submitFlag = true;
                        }else{
                            $state.go("app.recommend");
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
        if(!$scope.member.recommendPhone||!$scope.member.recommendPhone.trim()){
            window.wxc.xcConfirm("请输入推荐人手机号码", window.wxc.xcConfirm.typeEnum.info);
            $scope.submitFlag = true;
        }else if(!(/^1[3|4|5|8][0-9]\d{4,8}$/.test($scope.member.recommendPhone))){
            window.wxc.xcConfirm("推荐人手机号有误，请重新输入。", window.wxc.xcConfirm.typeEnum.info);
            $scope.submitFlag = true;
        }else if(!$scope.member.memberName||!$scope.member.memberName.trim()){
            window.wxc.xcConfirm("请输入会员姓名。", window.wxc.xcConfirm.typeEnum.info);
            $scope.submitFlag = true;
        }else if(!$scope.member.memberPhone||!$scope.member.memberPhone.trim()){
            window.wxc.xcConfirm("请输入会员账号。", window.wxc.xcConfirm.typeEnum.info);
            $scope.submitFlag = true;
        }else if(!(/^1[3|4|5|8][0-9]\d{4,8}$/.test($scope.member.memberPhone))){
            window.wxc.xcConfirm("会员账号有误，会员账号为手机号码。", window.wxc.xcConfirm.typeEnum.info);
            $scope.submitFlag = true;
        }else if(!$scope.member.loginPassword||!$scope.member.loginPassword.trim()){
            window.wxc.xcConfirm("请输入登录密码。", window.wxc.xcConfirm.typeEnum.info);
            $scope.submitFlag = true;
        }else if(!$scope.member.confirmLoginPassword||!$scope.member.confirmLoginPassword.trim()){
            window.wxc.xcConfirm("请输入确认密码。", window.wxc.xcConfirm.typeEnum.info);
            $scope.submitFlag = true;
        }else if($scope.member.loginPassword!=$scope.member.confirmLoginPassword){
            window.wxc.xcConfirm("登录密码与确认密码不一致，请重新输入。", window.wxc.xcConfirm.typeEnum.info);
            $scope.submitFlag = true;
        }else if(!$scope.member.notePhone||!$scope.member.notePhone.trim()){
            window.wxc.xcConfirm("请输入节点手机号码。", window.wxc.xcConfirm.typeEnum.info);
            $scope.submitFlag = true;
        }else if(!(/^1[3|4|5|8][0-9]\d{4,8}$/.test($scope.member.notePhone))){
            window.wxc.xcConfirm("节点手机号有误，请重新输入。", window.wxc.xcConfirm.typeEnum.info);
            $scope.submitFlag = true;
        }else if(!$scope.member.memberLevel){
            window.wxc.xcConfirm("请选择会员等级。", window.wxc.xcConfirm.typeEnum.info);
            $scope.submitFlag = true;
        }else if(!$scope.member.consignee||!$scope.member.consignee.trim()){
            window.wxc.xcConfirm("请输入收货人姓名。", window.wxc.xcConfirm.typeEnum.info);
            $scope.submitFlag = true;
        }else if(!$scope.member.expressAddress||!$scope.member.expressAddress.trim()){
            window.wxc.xcConfirm("请输入收货地址。", window.wxc.xcConfirm.typeEnum.info);
            $scope.submitFlag = true;
        }else{
            return true;
        }
    };
 });