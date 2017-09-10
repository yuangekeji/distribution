angular.module('recommend').controller('recommendAddCtrl',function ($q, title, $scope, $http,  $state, $stateParams, $sessionStorage, Notify) {
    title.setTitle('创建分销订单');
    $scope.user = $sessionStorage.currentUser;
    $scope.member = {
        recommendPhone:$sessionStorage.currentUser.memberPhone,
        notePhone:$stateParams.mobile ? $stateParams.mobile : '',
        loginPassword:'111111'
    };
    $scope.confirmLoginPassword = "111111";
    $scope.dictionary = [];
    $scope.onInit = function () {
        $http.get(ctx + '/member/getDictionary/member_level').success(function (resp) {
            if(resp.successful){
                $scope.dictionary = resp.data.list;
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
                $scope.startLoading();
                $http.post(ctx + "/member/insert",$scope.member).success(function (resp) {
                if(resp.successful){
                    if(resp.data=='NO_RECOMMENDER'){
                        $scope.stopLoading();
                        Notify.warning("推荐人不存在，请重新输入。");
                        $scope.submitFlag = true;
                    }else if(resp.data=='PHONE_EXISTENCE'){
                        $scope.stopLoading();
                        Notify.warning("会员账号已存在，请重新输入。");
                        $scope.submitFlag = true;
                    }else if(resp.data=='NO_NODE_MEMBER'){
                        $scope.stopLoading();
                        Notify.warning("节点不存在，请重新输入。");
                        $scope.submitFlag = true;
                    }else if(resp.data=='LEFT_NOTE_FULL'){
                        $scope.stopLoading();
                        Notify.warning("该节点左区已存在，请从新选择节点区域。");
                        $scope.submitFlag = true;
                    }else if(resp.data=='RIGHT_NOTE_FULL'){
                        $scope.stopLoading();
                        Notify.warning("该节点右区已存在，请从新选择节点区域。");
                        $scope.submitFlag = true;
                    }else{
                        $scope.stopLoading();
                        Notify.success('添加成功，返回列表页面。');
                        $state.go("app.recommend");
                        $scope.submitFlag = true;
                    }
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
        if(!$scope.member.recommendPhone||!$scope.member.recommendPhone.trim()){
            Notify.warning("请输入推荐人手机号码");
            $scope.submitFlag = true;
        }else if(!(/^1[3|4|5|8][0-9]\d{4,8}$/.test($scope.member.recommendPhone))){
            Notify.warning("推荐人手机号有误，请重新输入。");
            $scope.submitFlag = true;
        }else if(!$scope.member.memberName||!$scope.member.memberName.trim()){
            Notify.warning("请输入会员姓名。");
            $scope.submitFlag = true;
        }else if(!$scope.member.memberPhone||!$scope.member.memberPhone.trim()){
            Notify.warning("请输入会员账号。");
            $scope.submitFlag = true;
        }else if(!(/^1[3|4|5|8][0-9]\d{4,8}$/.test($scope.member.memberPhone))){
            Notify.warning("会员账号有误，会员账号为手机号码。");
            $scope.submitFlag = true;
        }else if(!$scope.member.loginPassword||!$scope.member.loginPassword.trim()){
            Notify.warning("请输入登录密码。");
            $scope.submitFlag = true;
        }else if(!$scope.confirmLoginPassword||!$scope.confirmLoginPassword.trim()){
            Notify.warning("请输入确认密码。");
            $scope.submitFlag = true;
        }else if($scope.member.loginPassword!=$scope.confirmLoginPassword){
            Notify.warning("登录密码与确认密码不一致，请重新输入。");
            $scope.submitFlag = true;
        }else if(!$scope.member.notePhone||!$scope.member.notePhone.trim()){
            Notify.warning("请输入节点手机号码。");
            $scope.submitFlag = true;
        }else if(!$scope.member.area){
            Notify.warning("请选择节点区域。");
            $scope.submitFlag = true;
        }else if(!(/^1[3|4|5|8][0-9]\d{4,8}$/.test($scope.member.notePhone))){
            Notify.warning("节点手机号有误，请重新输入。");
            $scope.submitFlag = true;
        }else if(!$scope.member.memberLevel){
            Notify.warning("请选择会员等级。");
            $scope.submitFlag = true;
        }
        /*else if(!$scope.member.consignee||!$scope.member.consignee.trim()){
            Notify.warning("请输入收货人姓名。");
            $scope.submitFlag = true;
        }else if(!$scope.member.expressAddress||!$scope.member.expressAddress.trim()){
            Notify.warning("请输入收货地址。");
            $scope.submitFlag = true;
        }*/
        else{
            return true;
        }
    };

    /**loading*/
    var e1 = $('.portlet');
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