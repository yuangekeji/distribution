angular.module('recommend').controller('recommendAddCtrl',function ($q, title, $scope, $http,  $state, $stateParams, $sessionStorage, ConfirmModal) {
    title.setTitle('创建分销订单');
    $scope.user = $sessionStorage.currentUser;
    $scope.member = {
        recommendPhone:$sessionStorage.currentUser.memberPhone,
        notePhone:$stateParams.mobile ? $stateParams.mobile : ''
    };
    $scope.confirmLoginPassword = "";
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
                $http.post(ctx + "/member/insert",$scope.member).success(function (resp) {
                if(resp.successful){
                    if(resp.data=='NO_RECOMMENDER'){
                        ConfirmModal.show({text: "推荐人不存在，请重新输入。", isCancel:false });
                        $scope.submitFlag = true;
                    }else if(resp.data=='PHONE_EXISTENCE'){
                        ConfirmModal.show({text: "会员账号已存在，请重新输入。", isCancel:false });
                        $scope.submitFlag = true;
                    }else if(resp.data=='NO_NODE_MEMBER'){
                        ConfirmModal.show({text: "节点不存在，请重新输入。", isCancel:false });
                        $scope.submitFlag = true;
                    }else if(resp.data=='LEFT_NOTE_FULL'){
                        ConfirmModal.show({text: "该节点左区已存在，请从新选择节点区域。", isCancel:false });
                        $scope.submitFlag = true;
                    }else if(resp.data=='RIGHT_NOTE_FULL'){
                        ConfirmModal.show({text: "该节点右区已存在，请从新选择节点区域。", isCancel:false });
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
            ConfirmModal.show({text: "请输入推荐人手机号码", isCancel:false });
            $scope.submitFlag = true;
        }else if(!(/^1[3|4|5|8][0-9]\d{4,8}$/.test($scope.member.recommendPhone))){
            ConfirmModal.show({text: "推荐人手机号有误，请重新输入。", isCancel:false });
            $scope.submitFlag = true;
        }else if(!$scope.member.memberName||!$scope.member.memberName.trim()){
            ConfirmModal.show({text: "请输入会员姓名。", isCancel:false });
            $scope.submitFlag = true;
        }else if(!$scope.member.memberPhone||!$scope.member.memberPhone.trim()){
            ConfirmModal.show({text: "请输入会员账号。", isCancel:false });
            $scope.submitFlag = true;
        }else if(!(/^1[3|4|5|8][0-9]\d{4,8}$/.test($scope.member.memberPhone))){
            ConfirmModal.show({text: "会员账号有误，会员账号为手机号码。", isCancel:false });
            $scope.submitFlag = true;
        }else if(!$scope.member.loginPassword||!$scope.member.loginPassword.trim()){
            ConfirmModal.show({text: "请输入登录密码。", isCancel:false });
            $scope.submitFlag = true;
        }else if(!$scope.confirmLoginPassword||!$scope.confirmLoginPassword.trim()){
            ConfirmModal.show({text: "请输入确认密码。", isCancel:false });
            $scope.submitFlag = true;
        }else if($scope.member.loginPassword!=$scope.confirmLoginPassword){
            ConfirmModal.show({text: "登录密码与确认密码不一致，请重新输入。", isCancel:false });
            $scope.submitFlag = true;
        }else if(!$scope.member.notePhone||!$scope.member.notePhone.trim()){
            ConfirmModal.show({text: "请输入节点手机号码。", isCancel:false });
            $scope.submitFlag = true;
        }else if(!$scope.member.area){
            ConfirmModal.show({text: "请选择节点区域。", isCancel:false });
            $scope.submitFlag = true;
        }else if(!(/^1[3|4|5|8][0-9]\d{4,8}$/.test($scope.member.notePhone))){
            ConfirmModal.show({text: "节点手机号有误，请重新输入。", isCancel:false });
            $scope.submitFlag = true;
        }else if(!$scope.member.memberLevel){
            ConfirmModal.show({text: "请选择会员等级。", isCancel:false });
            $scope.submitFlag = true;
        }else if(!$scope.member.consignee||!$scope.member.consignee.trim()){
            ConfirmModal.show({text: "请输入收货人姓名。", isCancel:false });
            $scope.submitFlag = true;
        }else if(!$scope.member.expressAddress||!$scope.member.expressAddress.trim()){
            ConfirmModal.show({text: "请输入收货地址。", isCancel:false });
            $scope.submitFlag = true;
        }else{
            return true;
        }
    };
 });