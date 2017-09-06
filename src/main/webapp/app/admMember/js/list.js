angular.module('admMember').controller('admMemberCtrl',function ($q, title, $scope, $http,  $state, $stateParams, $sessionStorage,ConfirmModal,Notify) {
    title.setTitle('会员管理');
    $scope.loadingFlag = true;
    $scope.notData = false;
    $scope.posts = [];
    $scope.levels = [];
    $scope.myPage = {
        pageNo: 1,
        pageSize: 10,
        totalCount: 0,
        result: [],
        parameterMap: {}
    };
    $scope.param = {
        chargeAmt : "",
        memberId:""
    };
    $scope.flag = true;
    var e1 = $('.portlet');
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
            if (!/^(([1-9][0-9]*)|(([0]\.\d{1,2}|[1-9][0-9]*\.\d{1,2})))$/.test($scope.param.chargeAmt)) {
                Notify.warning('请输入充值金额。');
                $scope.flag = true;
            } else {
                App.blockUI({
                    target: e1,
                    animate: true,
                    overlayColor: 'none'

                });
                $http.post(ctx + "/admMember/addAccount", $scope.param).success(function (resp) {
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

});

angular.module('admMember').filter("MemberLevelFilter",function () {
    return function (input) {
        if(input=='member_level1'){return '普卡'};
        if(input=='member_level2'){return '铜卡'};
        if(input=='member_level3'){return '银卡'};
        if(input=='member_level4'){return '金卡'};
        if(input=='member_level5'){return '白金卡'};
        if(input=='member_level6'){return '黑金卡'};
    }
});

angular.module('admMember').filter("PostLevelFilter",function () {
    return function (input) {
        if(input=='post_level1'){return '普通会员'};
        if(input=='post_level2'){return '主任'};
        if(input=='post_level3'){return '经理'};
        if(input=='post_level4'){return '总监'};
        if(input=='post_level5'){return '董事'};
        if(input=='post_level6'){return '全国董事'};
    }
});

angular.module('admMember').filter("StatusFilter",function () {
    return function (input) {
        if(input=='Y'){return '已激活'};
        if(input=='N'){return '未激活'};
    }
});