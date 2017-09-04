angular.module('home').controller('homeCtrl',
    function ($scope, $http, title, $sessionStorage, $timeout, $state,$rootScope,ConfirmModal,settings ,$uibModal, $window) {
    title.setTitle('home');

    $scope.$on('$viewContentLoaded', function() {
        // initialize core components
        App.initAjax();

        // set default layout mode
        $rootScope.settings.layout.pageContentWhite = true;
        $rootScope.settings.layout.pageBodySolid = false;
        $rootScope.settings.layout.pageSidebarClosed = false;
    });
     $scope.queryPasswordConfirm = '';
     $scope.payPasswordConfirm = '';
    $scope.currentUser = $sessionStorage.currentUser;

    $scope.test =function () {
        ConfirmModal.show({
            text: '确定要转账给该用户3000.00吗？',
            isCancel:false //false alert ,true confirm
        }).then(function (sure) {
            if (!sure) {
                return;
            }
        })
    }
    /**
     * description 激活弹出窗
     * @author Bright
     * */
    $timeout(function(){
        if(!$scope.currentUser)
            $scope.currentUser = $sessionStorage.currentUser;
        if($scope.currentUser.moneyStatus=='Y' && $scope.currentUser.status=='N'){
            $http.get(ctx + "/member/getDictionary/bank_name").success(function (resp) {
                if(resp.successful){
                    $scope.dictionary = resp.data.list;
                    $scope.moreMember = resp.data.moreMember;
                    if($scope.dictionary){
                        $scope.currentUser.bankName = $scope.dictionary[0].dicCode;
                    }
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
            alert("请输入身份证号码。");
        }else if(!/^[1-9]\d{7}((0\d)|(1[0-2]))(([0|1|2]\d)|3[0-1])\d{3}$|^[1-9]\d{5}[1-9]\d{3}((0\d)|(1[0-2]))(([0|1|2]\d)|3[0-1])\d{3}([0-9]|X)$/.test($scope.currentUser.idNumber.trim())){
            alert("身份证号码格式有误，请重新输入。")
        }else if(!$scope.currentUser.bankName){
            alert("请选择开户行。")
        }else if(!$scope.currentUser.bankUserName||!$scope.currentUser.bankUserName.trim()){
            alert("请输入开户人姓名。");
        }else if(!$scope.currentUser.cardNumber||!$scope.currentUser.cardNumber.trim()){
            alert("请输入银行卡号。")
        }else if(!/^[0-9]*$/.test($scope.currentUser.cardNumber)){
            alert("银行卡号只能为纯数字，请重新输入。")
        }else if(!$scope.currentUser.queryPassword||!$scope.currentUser.queryPassword.trim()){
            alert("请输入二级密码。")
        }else if(!$scope.queryPasswordConfirm||!$scope.queryPasswordConfirm.trim()){
            alert("请确认二级密码。")
        }else if($scope.currentUser.queryPassword!=$scope.queryPasswordConfirm){
            alert("二级密码和二级密码确认不同，请重新输入。")
        }else if(!$scope.currentUser.payPassword||!$scope.currentUser.payPassword.trim()){
            alert("请输入三级密码。")
        }else if(!$scope.payPasswordConfirm||!$scope.payPasswordConfirm.trim()){
            alert("请确认三级密码。")
        }else if($scope.currentUser.payPassword!=$scope.payPasswordConfirm){
            alert("三级密码和三级密码确认不同,请重新输入。")
        }else{
            $scope.currentUser.status='Y';
            $http.post(ctx + "/member/activation",$scope.currentUser).success(function (resp) {
                if(resp.successful){
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
        $scope.addSlide = function()
        {
            /** @type {number} */
            var newWidth = 600 + values.length + 1;
            values.push(
                {
                    image: $scope.settings.layoutPath+"/img/bg-pc.jpg",
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
        var i = 0;
        for (; i < 4; i++)
        {
            $scope.addSlide();
        }

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