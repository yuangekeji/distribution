angular.module('admMember').controller('admMemberCtrl',function ($q, title, $scope, $http,  $state, $stateParams, $sessionStorage,ConfirmModal,Notify) {
    title.setTitle('会员管理');
    $scope.loadingFlag = true;
    $scope.notData = false;
    $scope.posts = [];
    $scope.levels = [];
    $scope.currentUser =  $sessionStorage.currentUser
    $scope.myPage = {
        pageNo: 1,
        pageSize: 10,
        totalCount: 0,
        result: [],
        parameterMap: {}
    };
    $scope.param = {
        chargeAmt: "",
        memberId: ""
    };
    $scope.flag = true;
    var e1 = $('.full-view');
    $scope.initDic = function () {
        $http.get(ctx + "/admMember/init").success(function (resp) {
            if(resp.successful){
                $scope.posts = resp.data.post;
                $scope.levels = resp.data.level;
            }else{
                console.log(resp);
            }
        }).error(function (resp) {
            console.log(resp);
        })
    };
    $scope.initDic();

    $scope.onInit = function () {
        $http.post(ctx + '/admMember/list', $scope.myPage).success(function (resp) {
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
        $http.post(ctx + '/admMember/list', $scope.myPage).success(function (resp) {
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

    /**
     * 点击充值按钮
     * @author Bright
     * */
    $scope.showTab = function(member){
        $scope.param.memberId = member.id;
        $scope.chargeMember  = member;
        $scope.param.chargeAmt = "";
        $("#add").modal("show");
    };

    /**
     * 确认充值
     * @author Bright
     * */
    $scope.ok = function () {
        if($scope.flag) {
            $scope.flag = false;

            if (!/^\+?[1-9][0-9]*$/.test($scope.param.chargeAmt)) {
                Notify.warning('充值金额只能为正整数，请重新输入。');
                $scope.flag = true;
            } else if($scope.param.chargeAmt>60000){
                Notify.warning('充值金额不能大于60000，请重新输入。');
                $scope.flag = true;
            } else {
                App.blockUI({
                    target: e1,
                    animate: true,
                    overlayColor: 'none'

                });
                $http.post(ctx + "/admMember/addAccount?memberName="+$scope.chargeMember.memberName, $scope.param).success(function (resp) {
                    $scope.flag = true;
                    App.unblockUI(e1);
                    if (resp.successful) {
                        $("#add").modal("hide");
                        $scope.param.chargeAmt = "";
                        $scope.param.memberId = "";
                        Notify.success('充值成功。');
                    } else {
                        Notify.error('充值失败。');
                        console.log(resp);
                    }
                }).error(function (error) {
                    $scope.flag = true;
                    App.unblockUI(e1);
                    Notify.error('充值失败。');
                    console.error(error);
                });
            }
        }
    };

    /**
     * 会员密码初始化功能操作
     * @author sijeong
     * */
    $scope.initMemberPassword = function (id, memberName) {
        ConfirmModal.show({
            text: '确定要初始化会员' + memberName +'的密码吗？',
            isCancel:true //false alert ,true confirm
        }).then(function (sure) {
            if (!sure) {
                return;
            }
            $http.post(ctx + "/admMember/initMemberPassword",{id: id, memberName: memberName }).success(function (resp) {
                if(resp.successful){
                    Notify.success("密码初始化操作成功。");
                    $scope.search();
                }else{
                    Notify.warning("密码初始化操作失败。");
                }
            })
        });
    }
    //会员详情页面跳转
    $scope.gotoAdmMemberInfo = function (memberId) {
        $state.go("app.admMemberInfo", {memberId: memberId});
    }
    /**
     * 会员禁用功能操作
     * @author sijeong
     * */
    $scope.deleteMember = function (id, memberName, deleteFlag) {
        var deleteName = "";
        if (deleteFlag == "Y") {
            deleteName = "禁用";
        }else {
            deleteName = "启用";
        }
        ConfirmModal.show({
            text: '确定要'+ deleteName + memberName +'账号吗？',
            isCancel:true //false alert ,true confirm
        }).then(function (sure) {
            if (!sure) {
                return;
            }
            $http.post(ctx + "/admMember/deleteMember",{id: id, memberName: memberName, deleteFlag: deleteFlag }).success(function (resp) {
                if(resp.successful){
                    Notify.success(deleteName + "操作成功。");
                    $scope.search();
                }else{
                    Notify.warning(deleteName + "操作失败。");
                }
            })
        });
    }
    
    $scope.removeMemberNode = function (member) {
        $http.post(ctx + "/admMember/checkMemberChild/"+member.id).success(function (resp) {
            if(resp.successful){
                ConfirmModal.show({
                    text: '确定要删除'+ member.memberName +'账号吗？',
                    isCancel:true //false alert ,true confirm
                }).then(function (sure) {
                    if (!sure) {
                        return;
                    }
                    $http.post(ctx + "/admMember/deleteMemberNode/"+member.id).success(function (resp) {
                        if(resp.successful){
                            Notify.success( "操作成功。");
                            $scope.search();
                        }else{
                            Notify.warning( "操作失败。");
                        }
                    })
                });


            }else{
                Notify.warning("该节点下已有点位不能删除。");
            }
        });
    }
});

// angular.module('admMember').filter("MemberLevelFilter",function () {
//     return function (input) {
//         if(input=='member_level1'){return '普卡'};
//         if(input=='member_level2'){return '铜卡'};
//         if(input=='member_level3'){return '银卡'};
//         if(input=='member_level4'){return '金卡'};
//         if(input=='member_level5'){return '白金卡'};
//         if(input=='member_level6'){return '黑金卡'};
//     }
// });
//
