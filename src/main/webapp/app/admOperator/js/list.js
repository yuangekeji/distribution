angular.module('admOperator').controller('admOperatorCtrl',function ($q, title, $scope, $http,  $state, Notify, $sessionStorage, ConfirmModal) {
    title.setTitle('运营中心管理');
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
        $http.post(ctx + '/admOperator/list', $scope.myPage).success(function (resp) {
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
        $http.post(ctx + '/admOperator/list', $scope.myPage).success(function (resp) {
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

    $scope.approval = function (id,memberId,memberName,status) {
        $scope.textMessage = "";
        if (status == "pass") {
            $scope.textMessage = "确定审核通过会员" + memberName + "成为运营中心？";
        } else {
            $scope.textMessage = "确定审核驳回会员" + memberName + "成为运营中心？";
        }
        ConfirmModal.show({
            text: $scope.textMessage,
            isCancel:true //false alert ,true confirm
        }).then(function (sure) {
            if (!sure) {
                return;
            }
            $http.post(ctx + "/admOperator/approval?memberName="+memberName,{id:id,memberId:memberId,status:status}).success(function (resp) {
                if(resp.successful){
                    Notify.warning('操作完成。');
                    $scope.search();
                }else{
                    console.log(resp);
                }
            }).error(function (resp) {
                console.log(resp);
            })
        });
    }
});

angular.module('admOperator').filter("MemberLevelFilter",function () {
    return function (input) {
        if(input=='member_level1'){return '普卡'};
        if(input=='member_level2'){return '铜卡'};
        if(input=='member_level3'){return '银卡'};
        if(input=='member_level4'){return '金卡'};
        if(input=='member_level5'){return '白金卡'};
        if(input=='member_level6'){return '黑金卡'};
    }
});

angular.module('admOperator').filter("PostLevelFilter",function () {
    return function (input) {
        if(input=='post_level1'){return '普通会员'};
        if(input=='post_level2'){return '主任'};
        if(input=='post_level3'){return '经理'};
        if(input=='post_level4'){return '总监'};
        if(input=='post_level5'){return '董事'};
        if(input=='post_level6'){return '全国董事'};
    }
});

angular.module('admOperator').filter("StatusFilter",function () {
    return function (input) {
        if(input=='wait'){return '待审核'};
        if(input=='pass'){return '审核通过'};
        if(input=='refuse'){return '审核驳回'};
    }
});