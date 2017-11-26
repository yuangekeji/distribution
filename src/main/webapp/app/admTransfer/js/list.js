angular.module('admTransfer').controller('admTransferListCtrl',function ($q, title, $scope, $http,  $state, $stateParams, $sessionStorage, $uibModal, ConfirmModal, Notify) {
    title.setTitle('转账管理');

    $scope.currentUser = $sessionStorage.currentUser;

    $scope.myPage = {
        pageNo: 1,
        pageSize: 10,
        totalCount: 0,
        result: [],
        parameterMap: {
            memberName: '',
            receiveName: '',
            status: '',
            startTime: '',
            endTime: '',
            cancelStartTime: '',
            cancelEndTime: ''
        }
    };

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
        $http.post(ctx + '/admTransfer/getTransferList', $scope.myPage)
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
    /**
     * excel download
     */
    $scope.excelDownload = function() {
        window.location.href=ctx + "/admTransfer/excelDownload?"
            +"memberName="+$scope.myPage.parameterMap.memberName
            +"&startTime="+$scope.myPage.parameterMap.startTime
            +"&endTime="+$scope.myPage.parameterMap.endTime
            +"&cancelStartTime="+$scope.myPage.parameterMap.cancelStartTime
            +"&cancelEndTime="+$scope.myPage.parameterMap.cancelEndTime
            +"&status="+$scope.myPage.parameterMap.status;
    }

    /**转账撤销*/
    $scope.CancelTransfer = function (id, memberId, memberName, receiveId, receiveName, transferAmt, status) {
        $scope.textMessage = "确定要撤销" + memberName + "给收款人" + receiveName + "的转账吗？";
        ConfirmModal.show({
            text: $scope.textMessage,
            isCancel:true //false alert ,true confirm
        }).then(function (sure) {
            if (!sure) {
                return;
            }
            $scope.startLoading();
            $http.post(ctx + "/admTransfer/CancelTransfer",{id:id, memberId:memberId, memberName:memberName, receiveId:receiveId, receiveName:receiveName, transferAmt:transferAmt, status:status}).success(function (resp) {
                if(resp.successful){
                    if(resp.data.result == "transferFail") {
                        Notify.error("收款人账户奖金币余额不足，转账撤销失败。");
                    }else if(resp.data.result == "transferWarning") {
                        Notify.warning("该操作已被完成，转账撤销已完成。");
                    }else {
                        Notify.success("转账撤销成功。");
                    }
                    $scope.search();
                }else{
                    Notify.error('转账撤销失败。');
                }
                $scope.stopLoading();
            }).error(function (error) {
                Notify.error('转账撤销失败。');
                console.error(error);
                $scope.stopLoading();
            });
        });
    };
});

angular.module('admTransfer').filter("TransferTypeFilter",function () {
    return function (input) {
        if(input=='0'){return '转账成功'};
        if(input=='1'){return '已撤销'};
    }
});

