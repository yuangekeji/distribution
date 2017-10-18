/**
 * Created by jingxin on 2017/10/2.
 */
angular.module('admTreeMember').controller('admTreeMemberAddCtrl',function ($q, title, $scope, $http,  $state, $stateParams, $sessionStorage, Notify) {
    title.setTitle('创建分支');
    $scope.user = $sessionStorage.currentUser;
    $scope.member = {
        loginPassword:'111111',
        orderAmount:""
    };
    $scope.confirmLoginPassword = "111111";
    $scope.dictionary = [];
    $scope.orderAmounts = [];

    $scope.onInit = function () {

        var MIN = 600;
        var MAX = 30000;

        for(var i = 1 ; i<= MAX/MIN ; i++ ){
            $scope.orderAmounts.push({value: i * MIN , name :  i * MIN +".00 ( "+i+"单 )"});
        }
        $scope.orderAmounts.unshift({value:"",name:"请选择报单金额"});

        // $http.get(ctx + '/admTreeMember/getDictionary/member_level').success(function (resp) {
        //     if(resp.successful){
        //         $scope.dictionary = resp.data.list;
        //         if($scope.dictionary){
        //             $scope.member.memberLevel = $scope.dictionary[0].dicCode;
        //         }
        //     }else{
        //         console.log(resp);
        //     }
        // });
    };
    $scope.onInit();
    $scope.submitFlag = true;
    /**提交*/
    $scope.submit = function () {
        if($scope.submitFlag){
            $scope.submitFlag = false;
            if($scope.check()){
                $scope.startLoading();
                $http.post(ctx + "/admTreeMember/insert",$scope.member).success(function (resp) {
                    if(resp.successful){
                       if(resp.data=='PHONE_EXISTENCE'){
                            $scope.stopLoading();
                            Notify.warning("会员账号已存在，请重新输入。");
                            $scope.submitFlag = true;
                        }
                        else{
                            $scope.stopLoading();
                            Notify.success('添加成功，返回列表页面。');
                            $state.go("app.admTreeMember");
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
       if(!$scope.member.memberName||!$scope.member.memberName.trim()){
            Notify.warning("请输入会员姓名。");
            $scope.submitFlag = true;
        }else if(!$scope.member.memberPhone||!$scope.member.memberPhone.trim()){
            Notify.warning("请输入会员账号。");
            $scope.submitFlag = true;
        }else if(!(/^1[3|4|5|7|8][0-9]\d{4,8}$/.test($scope.member.memberPhone))){
            Notify.warning("会员账号有误，会员账号为手机号码。");
            $scope.submitFlag = true;
        }else if(!$scope.member.linkmanPhone||!$scope.member.linkmanPhone.trim()){
            Notify.warning("请输入联系人手机号。");
            $scope.submitFlag = true;
        }else if(!(/^1[3|4|5|7|8][0-9]\d{4,8}$/.test($scope.member.linkmanPhone))){
            Notify.warning("联系人手机号有误，请重新输入。");
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
        }else if(!$scope.member.orderAmount){
           Notify.warning("请选择报单金额。");
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