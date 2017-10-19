angular.module('home').controller('homeCtrl',
    function ($scope, $http, title, $sessionStorage, $timeout, $state,$rootScope,ConfirmModal,settings ,$uibModal, $window,Notify) {
    title.setTitle('主页');

    $scope.$on('$viewContentLoaded', function() {
        // initialize core components
        App.initAjax();
        // set default layout mode
        $rootScope.settings.layout.pageContentWhite = true;
        $rootScope.settings.layout.pageBodySolid = false;
        $rootScope.settings.layout.pageSidebarClosed = false;
    });
     $scope.queryPasswordConfirm = '222222';
     $scope.payPasswordConfirm = '333333';

     /**
      * description 激活弹出窗
      * @author Bright
      * */
        $timeout(function(){

              $scope.currentUser = $sessionStorage.currentUser;

             //当登录用户为会员的时候，并且激活状态为N，到款状态为Y
             if($scope.currentUser && $scope.currentUser.roleId == '1' && $scope.currentUser.status=='N' && $scope.currentUser.moneyStatus=='Y'){

                    $scope.currentUser.sendbypostyn= "1" ;//默认自提
                    $scope.currentUser.receiveName = $scope.currentUser.consignee;
                    $scope.currentUser.receviveAddress =  $scope.currentUser.expressAddress;
                    $scope.currentUser.recevivePhone =  $scope.currentUser.linkmanPhone;

                    $http.get(ctx + "/member/getDictionary/bank_name").success(function (resp) {
                        if(resp.successful){
                            $scope.dictionary = resp.data.list;
                            $scope.dictionary.unshift({dicCode:"",dicName:"请选择"});
                            $scope.moreMember = resp.data.moreMember;
                            if($scope.dictionary){
                                $scope.currentUser.bankName = $scope.dictionary[0].dicCode;
                            }
                            $scope.currentUser.queryPassword = "222222";
                            $scope.currentUser.payPassword = "333333";
                        }else{
                            console.log(resp);
                        }
                    });
                    $("#add").modal("show");
                }

        },1500);


    /**
     * description 激活账户
     * @author Bright
     * */
    $scope.activation = function () {
        if(!$scope.currentUser.idNumber||!$scope.currentUser.idNumber.trim()){
            Notify.warning("请输入身份证号码。");
        }
        // else if(!/^[1-9]\d{7}((0\d)|(1[0-2]))(([0|1|2]\d)|3[0-1])\d{3}$|^[1-9]\d{5}[1-9]\d{3}((0\d)|(1[0-2]))(([0|1|2]\d)|3[0-1])\d{3}([0-9]|X)$/.test($scope.currentUser.idNumber.trim())){
        //     Notify.warning("身份证号码格式有误，请重新输入。");
        // }
        /*else if(!$scope.currentUser.bankName){
            Notify.warning("请选择开户行。");
        }else if(!$scope.currentUser.bankUserName||!$scope.currentUser.bankUserName.trim()){
            Notify.warning("请输入开户人姓名。");
        }else if(!$scope.currentUser.cardNumber||!$scope.currentUser.cardNumber.trim()){
            Notify.warning("请输入银行卡号。");
        }else if(!/^[0-9]*$/.test($scope.currentUser.cardNumber)){
            Notify.warning("银行卡号只能为纯数字，请重新输入。");
        }*/
        else if(!$scope.currentUser.queryPassword||!$scope.currentUser.queryPassword.trim()){
            Notify.warning("请输入二级密码。");
        }else if(!$scope.queryPasswordConfirm||!$scope.queryPasswordConfirm.trim()){
            Notify.warning("请确认二级密码。");
        }else if($scope.currentUser.queryPassword!=$scope.queryPasswordConfirm){
            Notify.warning("二级密码和二级密码确认不同，请重新输入。");
        }else if(!$scope.currentUser.payPassword||!$scope.currentUser.payPassword.trim()){
            Notify.warning("请输入三级密码。");
        }else if(!$scope.payPasswordConfirm||!$scope.payPasswordConfirm.trim()){
            Notify.warning("请确认三级密码。");
        }else if($scope.currentUser.payPassword!=$scope.payPasswordConfirm){
            Notify.warning("三级密码和三级密码确认不同,请重新输入。");
        }else if($scope.currentUser.sendbypostyn == '2' && (!$scope.currentUser.receiveName||!$scope.currentUser.receiveName.trim())){
            Notify.warning("请输入收货人姓名。");
        }else if($scope.currentUser.sendbypostyn == '2' && (!$scope.currentUser.receviveAddress||!$scope.currentUser.receviveAddress.trim())){
            Notify.warning("请输入收货地址。");
        }else if($scope.currentUser.sendbypostyn == '2' && (!$scope.currentUser.recevivePhone||!$scope.currentUser.recevivePhone.trim())){
            Notify.warning("请输入收货人电话。");
        }else{

            $scope.startLoading();
            $scope.currentUser.status='Y';
            $http.post(ctx + "/member/activation",$scope.currentUser).success(function (resp) {
                if(resp.successful){
                    $scope.stopLoading();
                    Notify.warning("激活成功。");
                    $("#add").modal("hide");
                    $window.location.reload();
                }
            }).error(function (resp) {
                console.log(resp);
            });
        }
    };

        /**
         * 轮播
         * @param args
         */
        function render(args)
        {
            var i = 0;
            var valuesLen = values.length;
            for (; i < valuesLen; i++)
            {
                values[i].id = args.pop();
            }
        }

        function compiler()
        {
            var e = [];
            var n = 0;
            for (; n < l; ++n)
            {
                e[n] = n;
            }
            return next(e);
        }

        function next(result)
        {
            var value;
            var key;
            var index = result.length;
            if (index)
            {
                for (; --index;)
                {
                    key = Math.floor(Math.random() * (index + 1));
                    value = result[key];
                    result[key] = result[index];
                    result[index] = value;
                }
            }
            return result;
        }
        /** @type {number} */
        $scope.myInterval = 3E3;
        /** @type {boolean} */
        $scope.noWrapSlides = false;
        /** @type {number} */
        $scope.active = 0;
        /** @type {Array} */
        var values = $scope.slides = [];
        /** @type {number} */
        var l = 0;
        /**
         * @return {undefined}
         */
        $scope.addSlide = function(imgName)
        {
            /** @type {number} */
            var newWidth = 600 + values.length + 1;
            values.push(
                {
                    image: "static/metronic/layouts/layout2/img/"+imgName,
                    text: ["Nice image", "Awesome photograph", "That is so cool", "I love that"][values.length % 4],
                    id: l++
                });
        };
        /**
         * @return {undefined}
         */
        $scope.randomize = function()
        {
            var typePattern = compiler();
            render(typePattern);
        };
        /** @type {number} */
        $scope.imgNames =['p1.png','p2.png','p3.png','p4.png'];

        for ( var i = 0; i < $scope.imgNames.length; i++)
        {
            $scope.addSlide($scope.imgNames[i]);
        }

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
angular.module('home').controller('ModalInstanceCtrl', function ($scope, $uibModalInstance, getMsg,getType) {
    $scope.msg = getMsg;
    $scope.type = getType;

    $scope.ok = function()
    {
        $uibModalInstance.close(true);
    };
    $scope.cancel = function()
    {
        $uibModalInstance.dismiss('cancel');
    };
});