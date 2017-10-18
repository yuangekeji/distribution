angular.module('admMemberCharge').controller('admMemberChargeListCtrl',function ($q, title, $scope, $http,  $state, $stateParams, $sessionStorage, $uibModal, ConfirmModal, Notify) {
    title.setTitle('充值管理');

    $scope.currentUser = $sessionStorage.currentUser;

    $scope.myPage = {
        pageNo: 1,
        pageSize: 10,
        totalCount: 0,
        result: [],
        parameterMap: {
            memberName: '',
            status: '',
            startTime: '',
            endTime: '',
            chargeStartTime: '',
            chargeEndTime: ''
        }
    };
    $scope.approvalRemark = {
        id: '',
        status: '',
        remarks: '',
        memberId: '',
        memberName: ''
    };
    $scope.memberChargeRemarks = {
        remarks : ''
    }
    $scope.memberChargeApplyInfo = {
        applyInfo: ''
    }
    var e1 = $('.portlet');
    $scope.startLoading=function () {
        App.blockUI({
            target: e1,
            animate: true,
            overlayColor: 'none'
        });
    }
    $scope.stopLoading=function () {
        App.unblockUI(e1);
    }
    $scope.search = function(){
        $http.post(ctx + '/admMemberCharge/list', $scope.myPage)
            .success(function (resp) {
                if (resp.successful) {
                    $scope.myPage = resp.data;
                    $scope.notData = false;
                    if (!$scope.myPage.result || $scope.myPage.result.length == 0) $scope.notData = true;
                } else {
                    Notify.error(resp);
                }
            }).error(function (error) {
                Notify.error(error);
            });

    };
    /**
     * excel download
     */
    $scope.excelDownload = function() {
        window.location.href=ctx + "/admMemberCharge/excelDownload?"
            +"memberName="+$scope.myPage.parameterMap.memberName
            +"&startTime="+$scope.myPage.parameterMap.startTime
            +"&endTime="+$scope.myPage.parameterMap.endTime
            +"&chargeStartTime="+$scope.myPage.parameterMap.chargeStartTime
            +"&chargeEndTime="+$scope.myPage.parameterMap.chargeEndTime
            +"&status="+$scope.myPage.parameterMap.status;
    }
    /**
     * 初始化
     */
    $scope.onInit = function () {
        $scope.search();
    };

    $scope.onInit();

    /**
     * 查询按钮触发
     */
    $scope.searchByParam =function () {
        $scope.myPage.pageNo = 1;
        $scope.myPage.totalCount = 0;

        $scope.search();

    }

    /**
     * 分页触发
     * @param num
     */
    $scope.pageChangeHandler = function(num) {
        $scope.myPage.pageNo = num;
        $scope.search();
    };

    /**批准*/
    $scope.confirmMemberCharge = function (id, memberId, memberName, status) {
        $scope.textMessage = "确定要批准该充值申请了吗？";
        ConfirmModal.show({
            text: $scope.textMessage,
            isCancel:true //false alert ,true confirm
        }).then(function (sure) {
            if (!sure) {
                return;
            }
            $scope.startLoading();
            $http.post(ctx + "/admMemberCharge/confirmMemberCharge",{id:id, memberId:memberId, memberName:memberName, status:status}).success(function (resp) {
                if(resp.successful){
                    Notify.success("充值申请审批完成。");
                    $scope.search();
                }else{
                    Notify.error(resp.error());
                }
                $scope.stopLoading();
            }).error(function (error) {
                Notify.error(error);
                $scope.stopLoading();
            });
        });
    };
    /**审批驳回*/
    $scope.rejectMemberCharge = function (id, memberId, memberName, status) {
        $scope.approvalRemark.id = id;
        $scope.approvalRemark.memberId = memberId;
        $scope.approvalRemark.memberName = memberName;
        $scope.approvalRemark.status = status;
        $scope.approvalRemark.remarks = '';
        $scope.openReject();

    };

    $scope.openReject = function(opt_attributes)
    {
        var out = $uibModal.open(
            {
                animation: true,
                backdrop: 'static',
                templateUrl: "admMemberChargeApproval.html",
                controller: "admMemberChargeApprovalCtrl",
                size: opt_attributes,
                resolve: {
                    getDatas: function()
                    {
                        return $scope.approvalRemark;
                    }
                }
            });
        out.result.then(function(value)
        {
            // console.info('确认');
        }, function()
        {
            // console.info('取消');
        });
    };

    /**充值申请驳回备注*/
    $scope.getRemarks = function (remarks) {
        $scope.memberChargeRemarks.remarks = remarks;
        $scope.open(remarks);
    };

    $scope.open = function(opt_attributes)
    {
        var out = $uibModal.open(
            {
                animation: true,
                backdrop: 'static',
                templateUrl: "memberChargeRemarks.html",
                controller: "memberChargeRemarksCtrl",
                size: opt_attributes,
                resolve: {
                    getDatas: function()
                    {
                        return $scope.memberChargeRemarks;
                    }
                }
            });
        out.result.then(function(value)
        {
            // console.info('确认');
        }, function()
        {
            // console.info('取消');
        });
    };
    /**充值申请备注详情*/
    $scope.getApplyInfo = function (applyInfo) {
        $scope.memberChargeApplyInfo.applyInfo = applyInfo;
        $scope.openApplyInfo(applyInfo);
    };

    $scope.openApplyInfo = function(opt_attributes)
    {
        var out = $uibModal.open(
            {
                animation: true,
                backdrop: 'static',
                templateUrl: "memberChargeApplyInfo.html",
                controller: "memberChargeApplyInfoCtrl",
                size: opt_attributes,
                resolve: {
                    getDatas: function()
                    {
                        return $scope.memberChargeApplyInfo;
                    }
                }
            });
        out.result.then(function(value)
        {
            // console.info('确认');
        }, function()
        {
            // console.info('取消');
        });
    };
    /**确认充值*/
    $scope.AddMemberCharge = function (id, memberId, memberName, chargeAmt, status) {
        $scope.textMessage = "确定要给申请人" + memberName + "充值吗？";
        ConfirmModal.show({
            text: $scope.textMessage,
            isCancel:true //false alert ,true confirm
        }).then(function (sure) {
            if (!sure) {
                return;
            }
            $scope.startLoading();
            $http.post(ctx + "/admMemberCharge/AddMemberCharge",{id:id, memberId:memberId, memberName:memberName, chargeAmt:chargeAmt, status:status}).success(function (resp) {
                if(resp.successful){
                    Notify.success("会员充值成功。");
                    $scope.search();
                }else{
                    Notify.error('会员充值失败。');
                }
                $scope.stopLoading();
            }).error(function (error) {
                Notify.error('会员充值失败。');
                console.error(error);
                $scope.stopLoading();
            });
        });
    };
});

angular.module('admMemberCharge').filter("MemberChargeStatusFilter",function () {
    return function (input) {
        if(input=='0'){return '审核中'};
        if(input=='1'){return '待充值'};
        if(input=='2'){return '审核驳回'};
        if(input=='3'){return '充值成功'};
    }
});

angular.module('admMemberCharge').controller('admMemberChargeApprovalCtrl', function ($q, title, $scope, $http,  $state, $stateParams, $sessionStorage, $uibModalInstance,getDatas, ConfirmModal, Notify) {

    var e1 = $('.portlet');
    $scope.startLoading=function () {
        App.blockUI({
            target: e1,
            animate: true,
            overlayColor: 'none'
        });
    }
    $scope.stopLoading=function () {
        App.unblockUI(e1);
    }

    $scope.datas = getDatas;

    $scope.close = function()
    {
        $uibModalInstance.close(true);
    };
    $scope.cancel = function()
    {
        $uibModalInstance.dismiss('cancel');
    };

    /**充值驳回*/
    $scope.reject = function (id, memberId, memberName, status, remarks) {
        $scope.startLoading();
        $http.post(ctx + "/admMemberCharge/confirmMemberCharge",{id:id,memberId:memberId, memberName:memberName, status:status, remarks:remarks}).success(function (resp) {
            if(resp.successful){
                Notify.success("充值申请驳回完成。");
                $uibModalInstance.close(true);
                $state.go("app.admMemberCharge", {}, {reload: true});
            }else{
                Notify.error(resp);
            }
            $scope.stopLoading();
        }).error(function (error) {
            Notify.error(error);
            $scope.stopLoading();
        })
    };
});
angular.module('admMemberCharge').controller('memberChargeRemarksCtrl', function ($scope, $uibModalInstance,getDatas) {
    $scope.datas = getDatas;

    $scope.close = function()
    {
        $uibModalInstance.close(true);
    };
    $scope.cancel = function()
    {
        $uibModalInstance.dismiss('cancel');
    };
});
angular.module('admMemberCharge').controller('memberChargeApplyInfoCtrl', function ($scope, $uibModalInstance,getDatas) {
    $scope.datas = getDatas;

    $scope.close = function()
    {
        $uibModalInstance.close(true);
    };
    $scope.cancel = function()
    {
        $uibModalInstance.dismiss('cancel');
    };
});
